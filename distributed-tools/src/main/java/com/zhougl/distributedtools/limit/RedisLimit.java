package com.zhougl.distributedtools.limit;

import com.zhougl.distributedtools.constant.RedisToolsConstant;
import com.zhougl.distributedtools.utils.ScriptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.Collections;

/**
 * @author zhougl
 * 2019/3/17
 **/
public class RedisLimit {
    private static Logger logger = LoggerFactory.getLogger(RedisLimit.class);


    private JedisConnectionFactory jedisConnectionFactory;
    private int type ;
    private int limit = 200;

    private static final int FAIL_CODE = 0;

    /**
     * lua script
     */
    private String script;

    private RedisLimit(Builder builder) {
        this.jedisConnectionFactory = builder.jedisConnectionFactory;
        this.type = builder.type;
        this.limit = builder.limit;
        buildScript();
    }

    private void buildScript() {
        script = ScriptUtil.getScript("limit.lua");
    }

    public boolean limit(){
        Object connection = getConnection();
        Object result = limitRequest(connection);
        if (FAIL_CODE != (Long) result) {
            return true;
        } else {
            return false;
        }
    }

    private Object limitRequest(Object connection){
        Object result;
        String key = String.valueOf(System.currentTimeMillis() / 1000);
        if(connection instanceof Jedis){
            result = ((Jedis)connection).eval(script, Collections.singletonList(key),Collections.singletonList(String.valueOf(limit)));
        }else{
            result = ((JedisCluster) connection).eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
            try {
                ((JedisCluster) connection).close();
            } catch (IOException e) {
                logger.error("IOException",e);
            }
        }
        return result;
    }

    private Object getConnection(){
        Object connection;
        if(type == RedisToolsConstant.SINGLE){
            RedisConnection connection1 = jedisConnectionFactory.getConnection();
            connection = connection1.getNativeConnection();
        }else{
            RedisClusterConnection clusterConnection = jedisConnectionFactory.getClusterConnection();
            connection = clusterConnection.getNativeConnection();
        }
        return connection;
    }

    public static class Builder{
        private JedisConnectionFactory jedisConnectionFactory = null ;

        private int limit = 200;
        private int type ;

        public Builder(JedisConnectionFactory jedisConnectionFactory, int type) {
            this.jedisConnectionFactory = jedisConnectionFactory;
            this.type = type;
        }

        public Builder limit(int limit){
            this.limit = limit ;
            return this;
        }

        public RedisLimit build(){
            return new RedisLimit(this) ;
        }
    }
}
