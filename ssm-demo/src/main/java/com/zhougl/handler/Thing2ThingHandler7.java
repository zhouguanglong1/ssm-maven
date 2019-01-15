package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * 2018/12/10
 **/
public class Thing2ThingHandler7 {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler2.class);

        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        QueryRunner runnerB = QueryRunnerUtils.getRunner("webdb");
        long start = System.currentTimeMillis();
//        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION1";
        String sql = "select XZQHDM,SPSXBH,SPSXMC from A_APPLICATION2";
        List<Map<String,Object>> list = runner.query(sql,new MapListHandler());
        logger.info(sql);
    }
}
