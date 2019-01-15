package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * 把临时表的数据拆分成事件表（580+个表）
 * @author zhougl
 * 2018/12/10
 **/
public class Thing2ThingHandler5 {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler5.class);
        long start = System.currentTimeMillis();
        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        QueryRunner runnerB = QueryRunnerUtils.getRunner("thing2thing");
        String sql = "select DISTINCT SPSXBH from jzts_item_temp";
        Set<String> set = new HashSet<>(1000);
        runner.query(sql,rs -> {
            while (rs.next()){
                set.add(rs.getString("SPSXBH"));
            }
            return null;
        });

        logger.info("所有事项为:{}",set);

//        set.forEach(s -> {
        for (String s : set) {

            if(!"1100236002".equals(s)){
                continue;
            }
            s = s.replaceAll(" ","");
            StringBuilder checkSql = new StringBuilder();
            checkSql.append("DROP TABLE IF EXISTS ").append("item_").append(s.replaceAll("-","_")).append(";");
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE TABLE ").append("item_").append(s.replaceAll("-","_")).append(" (");
            builder.append("`id` int(11) NOT NULL AUTO_INCREMENT,");
            builder.append("`SPSXBH` varchar(50) DEFAULT NULL COMMENT '事项编号',");
            builder.append("`HZSXBH` varchar(1000) DEFAULT NULL COMMENT '后置事项编号',");
            builder.append("PRIMARY KEY (`id`)");
            builder.append(") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");

            try {
                runnerB.update(checkSql.toString());
                runnerB.update(builder.toString());
                logger.info(checkSql.toString());
                logger.info(builder.toString());
            } catch (SQLException e) {
                logger.error("发生异常的事件为{}",s);
                e.printStackTrace();
            }
            String insertSql = "INSERT INTO item_" + s.replaceAll("-","_") + " (SPSXBH,HZSXBH) SELECT SPSXBH,HZSXBH FROM jzpush.jzts_item_temp where SPSXBH = ? ";
            try {
                runnerB.update(insertSql,s);
                logger.info(insertSql);
            } catch (SQLException e) {
                logger.error("发生异常的事件为{}",s);
                e.printStackTrace();
            }
//        });
        }
        long end = System.currentTimeMillis();
        logger.info("处理1000w条数据花费 {} ms",(end - start));
    }
}
