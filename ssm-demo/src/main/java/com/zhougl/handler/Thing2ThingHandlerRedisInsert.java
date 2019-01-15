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
public class Thing2ThingHandlerRedisInsert {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandlerRedisInsert.class);
        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        QueryRunner runnerB = QueryRunnerUtils.getRunner("webdb");
        long start = System.currentTimeMillis();
//        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION1_PERSON";
//        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION1_ENTERPRISE";
        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION3";
        // 保存翻译信息
        Map<String,Map<String,Object>> infoMap = new HashMap<>();
        runner.query(sql,rs -> {
           while (rs.next()){
               Map<String, Object> map = toMap(rs);
               String sxbh = (String) map.get("SPSXBH");
//               sxbh = sxbh.replaceAll(" ","").replaceAll("，","").replaceAll("'","");
               if (!infoMap.containsKey(sxbh)) {
                   infoMap.put(sxbh, map);
               }
           }
            return null;
        });
        logger.info(sql);
        System.out.println(infoMap);

        Jedis jedis = RedisUtils.getJedis();
        // 获取所有事项的key
        Set<String> allItem = jedis.keys("item_*");
        String item2ItemSql = "insert into jzts_item_item_new (itemtype,area,itemcode,itemname,afterarea,afteritemcode,afteritemname) value (2,?,?,?,?,?,?)";
        allItem.forEach(s -> {
            logger.info("事项 {} ",s.substring(5));
            Map<String, String> map = jedis.hgetAll(s);
            map.forEach((key,value) -> {
                // 遍历所有的value，取出最大的value并且key不等于s
                jedis.zadd("range_rank",Double.parseDouble(value),key);
            });
            // sorted set实现排行榜，查询前三个
            Set<String> rangeSet = jedis.zrevrange("range_rank", 0, 2);
            List<String> list1 = new ArrayList<>();
            list1.addAll(rangeSet);
            int size = list1.size();
            // 截取掉item_
            s = s.substring(5);
            String key = "";
            if(size == 1){
                if(!s.equals(list1.get(0)) && !"无".equals(list1.get(0))){
                    // insert into item_to_item
                    key = list1.get(0);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else{
                    key = "无";
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }
            }else if(size == 2){
                if(!list1.get(0).equals(s) && !"无".equals(list1.get(0))){
                    key = list1.get(0);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else if(!list1.get(1).equals(s) && !"无".equals(list1.get(1))){
                    key = list1.get(1);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else{
                    key = "无";
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }
            }else if(size ==3){
                if(!list1.get(0).equals(s) && !"无".equals(list1.get(0))){
                    key = list1.get(0);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else if(!list1.get(1).equals(s) && !"无".equals(list1.get(1))){
                    key = list1.get(1);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else if(!list1.get(2).equals(s) && !"无".equals(list1.get(2))){
                    key = list1.get(2);
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }else{
                    key = "无";
                    logger.info("事项 {} 的后置事项为 {}",s,key);
                }
            }
            try {
                runnerB.update(item2ItemSql,
                        infoMap.get(s).get("XZQHDM"),
                        s,
                        infoMap.get(s).get("SPSXMC"),
                        "无".equals(key)?"无":infoMap.get(key).get("XZQHDM"),
                        key,
                        "无".equals(key)?"无":infoMap.get(key).get("SPSXMC")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 删除sorted set 的key
            jedis.del("range_rank");
        });
        // 清空所有key
        jedis.flushDB();
        RedisUtils.returnResource(jedis);
        long end = System.currentTimeMillis();
        logger.info("共处理 {} 事项，耗时 {} ms",allItem.size(),(end-start));


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
