package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @author zhougl
 * 2018/12/13
 **/
public class Thing2ThingHandlerRedis {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler4.class);


        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        long start = System.currentTimeMillis();

        String orderBySql = "SELECT SQRZJHM,SBSJ,SPSXBH FROM A_APPLICATION3 WHERE SQRLX = '2' ORDER BY SQRZJHM ASC,SBSJ ASC";
        // 人与人的事项关系
//        String orderBySql = "SELECT SQRZJHM,SBSJ,SPSXBH FROM A_APPLICATION1_PERSON ORDER BY SQRZJHM ASC,SBSJ ASC";
        // 企业与企业的事项关系
//        String orderBySql = "SELECT SQRZJHM,SBSJ,SPSXBH FROM A_APPLICATION1_ENTERPRISE ORDER BY SQRZJHM ASC,SBSJ ASC";

        // 使用ResultSet方式遍历不占用内存

        Jedis jedis = RedisUtils.getJedis();
        int insertSize = runner.query(orderBySql, rs -> {
            int allSize = 0;
            int orderSize = 0;
            int hold = 10000;
            int insertTime = 0;
            Map<String ,Object> last = new HashMap<>();
            while (rs.next()){
                allSize ++;
                orderSize ++;
                Map<String , Object> rowdata = toMap(rs);
                if(!last.isEmpty()) {
                    if (rowdata.get("SQRZJHM").equals(last.get("SQRZJHM"))) {
                        last.put("HZSXBH", rowdata.get("SPSXBH"));
                    } else {
                        last.put("HZSXBH", "无");
                    }
                    jedis.hincrBy("item_" + last.get("SPSXBH"), (String) last.get("HZSXBH"),1);
                    /*if(!jedis.exists("item_"+last.get("SPSXBH"))){
                        jedis.hset("item_" + last.get("SPSXBH"), (String) last.get("HZSXBH"),"1");
                    }else{
                        String hget = jedis.hget("item_" + last.get("SPSXBH"), (String) last.get("HZSXBH"));
                        if(hget == null){
                            jedis.hset("item_" + last.get("SPSXBH"), (String) last.get("HZSXBH"),"1");
                        } else {
                            // 取出当前的value，对value++
                            int i = Integer.parseInt(hget);
                            i ++;
                            jedis.hset("item_" + last.get("SPSXBH"), (String) last.get("HZSXBH"),String.valueOf(i));
                        }*/
                        /*Map<String, String> spsxbh = jedis.hgetAll("item_" + last.get("SPSXBH"));
                        if(spsxbh.containsKey(last.get("HZSXBH"))){
                            String hzsxbh = spsxbh.get(last.get("HZSXBH"));
                            Integer time = Integer.parseInt(hzsxbh);
                            time = time + 1;
                            spsxbh.put((String) last.get("HZSXBH"),String.valueOf(time));
                        }else{
                            spsxbh.put((String) last.get("HZSXBH"),"1");
                        }
                        jedis.hmset("item_" + last.get("SPSXBH"),spsxbh);*/
//                    }
                    if(orderSize == hold){
                        long end1 = System.currentTimeMillis();
                        logger.info("已处理{}w条数据,花费 {} ms", ++insertTime, (end1 - start));
                        // 清空计数器
                        orderSize = 0;
                    }
                }
                last = rowdata;
            }

            return allSize;
        });

        RedisUtils.returnResource(jedis);
        long end = System.currentTimeMillis();
        logger.info("共处理 {} 条数据，耗时 {} ms",insertSize,(end-start));
    }

    private static Map<String, Object> toMap(ResultSet rs) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; ++i) {
            String columnName = rsmd.getColumnLabel(i);
            if ((null == columnName) || (0 == columnName.length())) {
                columnName = rsmd.getColumnName(i);
            }
            result.put(columnName, rs.getObject(i));
        }

        return result;
    }
}
