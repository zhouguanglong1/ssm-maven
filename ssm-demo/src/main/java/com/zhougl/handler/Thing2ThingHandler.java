package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * 2018/12/5
 **/
public class Thing2ThingHandler {

    public static void main(String[] args) throws SQLException {
        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        long start = System.currentTimeMillis();
        String sql = "select SERIALNO,SPSXBH,SPSXMC,SQRZJHM,SQRMC from A_APPLICATION1 limit 1000,2000";
        List<Map<String,Object>> list = runner.query(sql,new MapListHandler());
        System.out.println(sql);
        list.forEach(map -> {
            String serialNo = (String) map.get("SERIALNO");
            String sql1 = "select BJSJ,BLJGMS from A_COMPLETE where SERIALNO = ?";
            Map<String,Object> result = null;
            try {
                result = runner.query(sql1, new MapHandler(),serialNo);
                System.out.println(sql1);
                if(result != null && "办结".equals(result.get("BLJGMS"))){
                    String insertSql = "insert into A_APPLICATION_COMPLETE (SERIALNO,SPSXBH,SPSXMC,SQRZJHM,SQRMC,BJSJ,BLJGMS) value (?,?,?,?,?,?,?)";
                    int update = runner.update(insertSql, serialNo, map.get("SPSXBH"), map.get("SPSXMC"), map.get("SQRZJHM"), map.get("SQRMC"), result.get("BJSJ"), result.get("BLJGMS"));
                    System.out.println(insertSql);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("处理一千条数据花费" + (end - start) +"ms");
    }
}
