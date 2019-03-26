package com.zhougl.distributedtools.lock;

import com.zhougl.distributedtools.constant.RedisToolsConstant;
import com.zhougl.distributedtools.utils.ScriptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;

/**
 * @author zhougl
 * 2019/3/18
 **/
public class RedisLock {
    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static final String LOCK_MSG = "OK";

    private static final Long UNLOCK_MSG = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private String lockPrefix;

    private int sleepTime;

    private JedisConnectionFactory jedisConnectionFactory;
    private int type;

    /**
     * time millisecond
     */
    private static final int TIME = 1000;
    /**
     * lua script
     */
    private String script;

    private RedisLock(Builder builder) {
        this.lockPrefix = builder.lockPrefix;
        this.sleepTime = builder.sleepTime;
        this.jedisConnectionFactory = builder.jedisConnectionFactory;
        this.type = builder.type;
        buildScript();
    }


    private void buildScript() {
        script = ScriptUtil.getScript("lock.lua");
    }

    /**
     * get Redis connection
     * @return
     */
    private Object getConnection() {
        Object connection ;
        if (type == RedisToolsConstant.SINGLE){
            RedisConnection redisConnection = jedisConnectionFactory.getConnection();
            connection = redisConnection.getNativeConnection();
        }else {
            RedisClusterConnection clusterConnection = jedisConnectionFactory.getClusterConnection();
            connection = clusterConnection.getNativeConnection() ;
        }
        return connection;
    }

    /**
     * 非阻塞锁
     * @param key
     * @param request
     * @return
     */
    public boolean trylock(String key,String request){
        Object connection = getConnection();
        String result;
        if(connection instanceof Jedis){
            result = ((Jedis) connection).set(lockPrefix+key,request,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,10*TIME);
            ((Jedis) connection).close();
        }else{
            result = ((JedisCluster) connection).set(lockPrefix + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
        }
        return LOCK_MSG.equals(result);
    }


    /**
     * 非阻塞锁,自定义超时时间
     * @param key
     * @param request
     * @param expireTime
     * @return
     */
    public boolean trylock(String key,String request,int expireTime){
        Object connection = getConnection();
        String result;
        if(connection instanceof Jedis){
            result = ((Jedis) connection).set(lockPrefix+key,request,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,expireTime);
            ((Jedis) connection).close();
        }else{
            result = ((JedisCluster) connection).set(lockPrefix + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        }
        return LOCK_MSG.equals(result);
    }

    /**
     * 阻塞锁
     * @param key
     * @param request
     * @throws InterruptedException
     */
    public void lock(String key, String request) throws InterruptedException {
        Object connection = getConnection();
        String result;
        for(;;){
            if(connection instanceof Jedis){
                result = ((Jedis) connection).set(lockPrefix+key,request,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,10*TIME);
                if (LOCK_MSG.equals(result)) ((Jedis) connection).close();
            }else{
                result = ((JedisCluster) connection).set(lockPrefix + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
            }
            if(LOCK_MSG.equals(result)) break;
            Thread.sleep(sleepTime);
        }
    }

    /**
     * 阻塞锁，自定义超时时间
     * @param key
     * @param request
     * @throws InterruptedException
     */
    public boolean lock(String key, String request,int blockTime) throws InterruptedException {
        Object connection = getConnection();
        String result;
        while (blockTime>=0){
            if(connection instanceof Jedis){
                result = ((Jedis) connection).set(lockPrefix+key,request,SET_IF_NOT_EXIST,SET_WITH_EXPIRE_TIME,10*TIME);
                if (LOCK_MSG.equals(result)) ((Jedis) connection).close();
            }else{
                result = ((JedisCluster) connection).set(lockPrefix + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
            }
            if(LOCK_MSG.equals(result)) return true;
            blockTime -= sleepTime;
            Thread.sleep(sleepTime);
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param request
     * @return
     */
    public boolean unlock(String key, String request) {
        Object connection = getConnection();
        Object result = null;
        if (connection instanceof Jedis) {
            result = ((Jedis) connection).eval(script, Collections.singletonList(lockPrefix + key),Collections.singletonList(request));
        } else if(connection instanceof JedisCluster){
            result = ((JedisCluster) connection).eval(script, Collections.singletonList(lockPrefix + key),Collections.singletonList(request));
        } else{
            return false;
        }
        return UNLOCK_MSG.equals(result);
    }

    public static class Builder {
        private static final String DEFAULT_LOCK_PREFIX = "lock_";
        /**
         * default sleep time
         */
        private static final int DEFAULT_SLEEP_TIME = 100;

        private JedisConnectionFactory jedisConnectionFactory = null;

        private int type;

        private String lockPrefix = DEFAULT_LOCK_PREFIX;
        private int sleepTime = DEFAULT_SLEEP_TIME;

        public Builder(JedisConnectionFactory jedisConnectionFactory, int type) {
            this.jedisConnectionFactory = jedisConnectionFactory;
            this.type = type;
        }

        public Builder lockPrefix(String lockPrefix) {
            this.lockPrefix = lockPrefix;
            return this;
        }

        public Builder sleepTime(int sleepTime) {
            this.sleepTime = sleepTime;
            return this;
        }

        public RedisLock build() {
            return new RedisLock(this);
        }
    }
}
