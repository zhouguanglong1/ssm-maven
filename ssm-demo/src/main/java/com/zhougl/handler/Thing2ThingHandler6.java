package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分别统计400+个事项表，得出各自的最大权重的后置事项
 * @author zhougl
 * 2018/12/10
 **/
public class Thing2ThingHandler6 {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler6.class);
        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        QueryRunner runnerB = QueryRunnerUtils.getRunner("webdb");
        QueryRunner runnerC = QueryRunnerUtils.getRunner("thing2thing");
        long start = System.currentTimeMillis();
//        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION1";
        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION2";
        List<Map<String,Object>> list = runner.query(sql,new MapListHandler());
        logger.info(sql);
        // 保存翻译信息
        Map<String,Map<String,Object>> infoMap = new HashMap<>();
        list.forEach(map -> {
            String sxbh = (String) map.get("SPSXBH");
            sxbh = sxbh.replaceAll(" ","");
            if (!infoMap.containsKey(sxbh)) {
                infoMap.put(sxbh, map);
            }
        });

        Set<String> keySet = infoMap.keySet();
        keySet.forEach(key -> {
            String statSql = "select HZSXBH,COUNT(HZSXBH) from item_"+key.replaceAll("-","_") + " group by HZSXBH ORDER BY COUNT(HZSXBH) DESC";
            try {
                List<Map<String, Object>> query = runnerC.query(statSql, new MapListHandler());
                logger.info(statSql);
                String item2ItemSql = "insert into jzts_item_item (area,itemcode,itemname,afterarea,afteritemcode,afteritemname) value (?,?,?,?,?,?)";
                if(!query.isEmpty() && query.get(0)!=null && !"无".equals(query.get(0).get("HZSXBH")) && !key.equals(query.get(0).get("HZSXBH")) ){
                    runner.update(item2ItemSql,
                            infoMap.get(key).get("XZQHDM"),
                            key,
                            infoMap.get(key).get("SPSXMC"),
                            "无".equals(query.get(0).get("HZSXBH"))?"无":infoMap.get(query.get(0).get("HZSXBH")).get("XZQHDM"),
                            query.get(0).get("HZSXBH"),
                            "无".equals(query.get(0).get("HZSXBH"))?"无":infoMap.get(query.get(0).get("HZSXBH")).get("SPSXMC")
                    );
                }else if(!query.isEmpty() && query.get(0)!=null && key.equals(query.get(0).get("HZSXBH")) && query.size() > 1 && !"无".equals(query.get(1).get("HZSXBH"))){
                    runner.update(item2ItemSql,
                            infoMap.get(key).get("XZQHDM"),
                            key,
                            infoMap.get(key).get("SPSXMC"),
                            "无".equals(query.get(1).get("HZSXBH"))?"无":infoMap.get(query.get(1).get("HZSXBH")).get("XZQHDM"),
                            query.get(1).get("HZSXBH"),
                            "无".equals(query.get(1).get("HZSXBH"))?"无":infoMap.get(query.get(1).get("HZSXBH")).get("SPSXMC")
                    );
                }else{
                    runner.update(item2ItemSql,
                            infoMap.get(key).get("XZQHDM"),
                            key,
                            infoMap.get(key).get("SPSXMC"),
                            "无",
                            "无",
                            "无");
                }
                logger.info(item2ItemSql);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        });

        long end = System.currentTimeMillis();
        logger.info("处理10w条数据花费 {} ms",(end - start));
    }
}
