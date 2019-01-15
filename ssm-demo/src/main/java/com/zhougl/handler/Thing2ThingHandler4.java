package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 把申办表的数据按照SQRZJHM，SPSXBH，SBSJ三个字段分组得到结果，并分别设置每个事项的后置事项，保存到临时表中jzts_item_temp
 * @author zhougl
 * 2018/12/8
 **/
public class Thing2ThingHandler4 {
    public static void main(String[] args) throws SQLException {
        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler4.class);

        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
        long start = System.currentTimeMillis();

        String orderBySql = "SELECT SQRZJHM,SBSJ,SPSXBH FROM A_APPLICATION1 ORDER BY SQRZJHM ASC,SBSJ ASC";

        // 使用ResultSet方式遍历不占用内存
        int insertSize = runner.query(orderBySql, rs -> {
            Map<String ,Object> last1 = new HashMap<>();
            StringBuilder insertSql1 = new StringBuilder();
            int orderSize = 0;
            int allSize = 0;
            int insertTime = 0;
            int hold = 10000;
            while (rs.next()){
                orderSize ++;
                allSize ++;
                Map<String , Object> rowdata = toMap(rs);
                if(orderSize == 1) {
                    insertSql1.append("insert into jzts_item_temp (SPSXBH,HZSXBH,SBSJ,SQRZJHM) values ");
                    // 开启事务
                    String sql111 = "START TRANSACTION;";
                    runner.execute(sql111);
                }
                if(!last1.isEmpty()){
                    if(rowdata.get("SQRZJHM").equals(last1.get("SQRZJHM"))){
                        last1.put("HZSXBH",rowdata.get("SPSXBH"));
                    }else{
                        last1.put("HZSXBH","无");
                    }
                    insertSql1.append("(");
                    insertSql1.append("'").append(last1.get("SPSXBH")).append("'");
                    insertSql1.append(",");
                    insertSql1.append("'").append(last1.get("HZSXBH")).append("'");
                    insertSql1.append(",");
                    insertSql1.append("'").append(last1.get("SBSJ")).append("'");
                    insertSql1.append(",");
                    insertSql1.append("'").append(((String)last1.get("SQRZJHM")).replaceAll(" ","").replaceAll("'","").replaceAll("，","")).append("'");
                    insertSql1.append(")");
                    if(orderSize != hold){
                        insertSql1.append(",");
                    }
                    // 达到一万条，插一次
                    if(orderSize == hold){
                        runner.update(insertSql1.toString());
                        long end1 = System.currentTimeMillis();
                        logger.info("已处理{}w条数据,花费 {} ms", ++insertTime, (end1 - start));
                        String commitSql = "commit;";
                        runner.execute(commitSql);
                        // 清空计数器
                        orderSize = 0;
                        // 清空sql的builder
                        insertSql1.delete(0,insertSql1.length());
                    }
                }
                last1 = rowdata;
            }
            return allSize;
        });
        // 提交事务
//        String commitSql = "commit;";
//        runner.execute(commitSql);
        // 记录结束时间
        long end2 = System.currentTimeMillis();
        logger.info("数据已经全部跑完，共处理 {} 条数据，共耗时 {} ms",insertSize,(end2 - start));


        // 打印jvm信息
        testGetJvmInfo();
        //////////////////////////////////////
        /*String sql = "START TRANSACTION;";
        runner.execute(sql);
        groupByList.forEach(map -> {
            String insertSql = "insert into jzts_item_temp (SPSXBH,HZSXBH,SBSJ,SQRZJHM) value (?,?,?,?)";
            try {
                runner.update(insertSql,map.get("SPSXBH"),map.get("HZSXBH"),map.get("SBSJ"),map.get("SQRZJHM"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            logger.info(insertSql);
        });
        // 提交事务
        String sql1 = "commit;";
        runner.execute(sql1);*/
        ////////////////////////////////////
        // 关闭默认自动提交
        /*String sql = "START TRANSACTION;";
        runner.execute(sql);
        String insertSql = "insert into jzts_item_temp (SPSXBH,HZSXBH,SBSJ,SQRZJHM) values (?,?,?,?)";
        Object[][] params = new Object[groupByList.size()][];
        for (int i = 0; i < groupByList.size(); i++) {
            Object[] objects = {groupByList.get(i).get("SPSXBH"),groupByList.get(i).get("HZSXBH"),groupByList.get(i).get("SBSJ"),groupByList.get(i).get("SQRZJHM")};
            params[i] = new Object[objects.length];
            for (int j = 0; j < objects.length; j++) {
                params[i][j] = objects[j];
            }
//            System.arraycopy(objects, 0, params[i], 0, objects.length);
        }
        logger.info(insertSql);
        runner.batch(insertSql,params);
        // 提交事务
        String sql1 = "commit;";
        runner.execute(sql1);*/


        /*String rollSql = "SET AUTOCOMMIT = 0;";
        runner.execute(rollSql);
        StringBuilder insertSql1 = new StringBuilder("insert into jzts_item_temp (SPSXBH,HZSXBH,SBSJ,SQRZJHM) values ");
        int size = groupByList.size();
        for (int i = 0; i < size; i++) {
            insertSql1.append("(");
            insertSql1.append(groupByList.get(i).get("SPSXBH"));
            insertSql1.append(",");
            insertSql1.append(groupByList.get(i).get("HZSXBH"));
            insertSql1.append(",");
            insertSql1.append(groupByList.get(i).get("SBSJ"));
            insertSql1.append(",");
            insertSql1.append(groupByList.get(i).get("SQRZJHM"));
            insertSql1.append(")");
            if(i != size-1){
                insertSql1.append(",");
            }
        }
        logger.info(insertSql1.toString());
        try {
            runner.execute(insertSql1.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        // 提交事务
        String commitSql = "commit;";
        runner.execute(commitSql);*/
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

    private static void testGetJvmInfo() {

        Runtime lRuntime = Runtime.getRuntime();
        System.out.println("*** BEGIN MEMORY STATISTICS ***");
        System.out.println("Free  Memory: " + lRuntime.freeMemory()/1024/1024 + "M");
        System.out.println("Max   Memory: " + lRuntime.maxMemory()/1024/1024 + "M");
        System.out.println("Total Memory: " + lRuntime.totalMemory()/1024/1024 + "M");
        System.out.println("Available Processors : " + lRuntime.availableProcessors());
        System.out.println("*** END MEMORY STATISTICS ***");

        System.out.println();

        java.lang.management.RuntimeMXBean bean = java.lang.management.ManagementFactory.getRuntimeMXBean();
        long starttime = bean.getStartTime();
        java.util.Date d = new java.util.Date(starttime);

        System.out.println("*** OTHER INFO ***");
        System.out.println("starttime: " + d);
        System.out.println("has runed: " + (System.currentTimeMillis() - starttime)/60000 + " minutes.");
        System.out.println("pid and hostname: " + bean.getName());

        System.out.println();
        System.out.println("------Memory MXBean-----");

        System.out.println();
        System.out.println("Heap Memory Usage");
        System.out.println(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());

        System.out.println();
        System.out.println("Non-Heap Memory Usage");
        System.out.println(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());


        System.out.println();
        System.out.println("------Memory Pool MXBeans-----");
        Iterator<?> iter = ManagementFactory.getMemoryPoolMXBeans().iterator();
        while (iter.hasNext()) {
            MemoryPoolMXBean item = (MemoryPoolMXBean) iter.next();

            System.out.println(item.getName());

            System.out.println("Type\t\t\t" + item.getType());
            System.out.println("Usage\t\t\t" + item.getUsage());
            System.out.println("Peak Usage\t\t" + item.getPeakUsage());
            if(item.getCollectionUsage() != null) System.out.println("Collection Usage\t" + item.getCollectionUsage());

            System.out.println();

        }


    }
}
