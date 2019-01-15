package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * 2018/12/12
 **/
public class Thing2ThingHandlerUseMemory {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler4.class);

        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        long start = System.currentTimeMillis();


        String orderBySql = "SELECT SQRZJHM,SBSJ,SPSXBH FROM A_APPLICATION2 ORDER BY SQRZJHM ASC,SBSJ ASC";
        List<Map<String,Object>> groupByList = runner.query(orderBySql,new MapListHandler());
        Map<String ,Object> last = new HashMap<>();
        int allSize = groupByList.size();
        for (int i = 0; i < allSize; i++) {
            Map<String, Object> map = groupByList.get(i);
            if(!last.isEmpty() && map.get("SQRZJHM").equals(last.get("SQRZJHM"))){
                groupByList.get(i-1).put("HZSXBH",map.get("SPSXBH"));
            }else if(!last.isEmpty() && !map.get("SQRZJHM").equals(last.get("SQRZJHM"))){
                groupByList.get(i-1).put("HZSXBH","无");
            }
            if(i == allSize -1){
                map.put("HZSXBH","无");
            }
            last = map;
        }


        // 设置1w条拼接一次
        int size = groupByList.size();
        int threadHold = 10000;

        int time = size/threadHold;//提交次数
        if(size%threadHold > 0) time ++;
        // 开启事务
        String sql111 = "START TRANSACTION;";
        runner.execute(sql111);
        for (int i = 1; i <= time; i++) {
            StringBuilder insertSql1 = new StringBuilder("insert into jzts_item_temp (SPSXBH,HZSXBH,SBSJ,SQRZJHM) values ");
            for (int j = (i-1)*threadHold; j < i * threadHold; j++) {
                insertSql1.append("(");
                insertSql1.append("'").append(groupByList.get(j).get("SPSXBH")).append("'");
                insertSql1.append(",");
                insertSql1.append("'").append(groupByList.get(j).get("HZSXBH")).append("'");
                insertSql1.append(",");
                insertSql1.append("'").append(groupByList.get(j).get("SBSJ")).append("'");
                insertSql1.append(",");
                insertSql1.append("'").append(groupByList.get(j).get("SQRZJHM")).append("'");
                insertSql1.append(")");
                if(j != i * threadHold -1){
                    insertSql1.append(",");
                }
            }
            runner.update(insertSql1.toString());
            long end1 = System.currentTimeMillis();
            logger.info("已处理{}w条数据,花费 {} ms", i, (end1 - start));
        }
        // 提交事务
        String commitSql = "commit;";
        runner.execute(commitSql);


        long end = System.currentTimeMillis();
        logger.info("处理1300w条数据花费 {} ms",(end - start));
    }
}
