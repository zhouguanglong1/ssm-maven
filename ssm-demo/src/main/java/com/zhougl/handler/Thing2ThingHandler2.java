package com.zhougl.handler;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author zhougl
 * 2018/12/5
 **/
public class Thing2ThingHandler2 {
    public static void main(String[] args) throws SQLException {

        final Logger logger = LoggerFactory.getLogger(Thing2ThingHandler2.class);

        QueryRunner runner = QueryRunnerUtils.getRunner("basicdb");
//        QueryRunner runnerB = QueryRunnerUtils.getRunner("webdb");
        QueryRunner runnerB = QueryRunnerUtils.getRunner("basicdb");
        long start = System.currentTimeMillis();
        // XZQHDM   行政区划
        // SPSXBH   事项编号
        // SPSXMC   事项名称
        // SQRZJHM  申请人证件号
        // SQRMC    申请人名称
        // SBSJ     申办时间
        String sql = "select XZQHDM,SPSXBH,SPSXMC,SQRZJHM,SQRMC,SBSJ from A_APPLICATION1 limit 100000";
        List<Map<String,Object>> list = runner.query(sql,new MapListHandler());
        logger.info(sql);
        // 以办理事项为维度，办理人为链表保存办理了该事项的人
        // 办理事项 --- 办理人1->办理人2->办理人3...
        Map<String,List<String>> sxbhMap = new LinkedHashMap<>();
        Map<String,Map<String,Object>> infoMap = new HashMap<>();
        list.forEach(map -> {
            String sxbh = (String) map.get("SPSXBH");
            // 注释掉sxbhMap的实现
            if(sxbhMap.containsKey(sxbh)){
                sxbhMap.get(sxbh).add(map.get("SQRZJHM")+"");
            }else{
                List<String> l = new LinkedList<>();
                l.add(map.get("SQRZJHM")+"");
                sxbhMap.put(sxbh,l);
//                infoMap.put(sxbh,map);
            }
            if(!infoMap.containsKey(sxbh)){
                infoMap.put(sxbh,map);
            }
        });
        sxbhMap.forEach((key,value) -> System.out.println(key + "  " + value));

        // 以申请人为维度，按照办理时间排序的办理事项链表
        // 申请人 --- 事项1->事项2->事项3...
        Map<String,List<Map<String,Object>>> sqrMap = new LinkedHashMap<>();
        list.forEach(map -> {
            String sqrmc = (String) map.get("SQRZJHM");
            if(sqrMap.containsKey(sqrmc)){
                sqrMap.get(sqrmc).add(map);
            }else{
                List<Map<String,Object>> l = new LinkedList<>();
                l.add(map);
                sqrMap.put(sqrmc,l);
            }
        });
        // 申请人为维度的办事轨迹按 申办时间 排序
        sqrMap.forEach((key, value) -> value.sort((o1, o2) -> {
            long t1 = ((Timestamp) o1.get("SBSJ")).getTime();
            long t2 = ((Timestamp) o2.get("SBSJ")).getTime();
            if(t1 > t2) return 1;
            else if(t1 < t2) return -1;
            return 0;
        }));
//        allSxbhSet.forEach(logger::info);
        // 打印 申请人为维度的办事轨迹
//        sqrMap.forEach((key, value) -> logger.info("{} {}",key,value));
        // 事件编号的key集合
        Set<String> keySet = infoMap.keySet();
        Map<String,List<StatisticInfo>> result = new HashMap<>();
        for (String s : keySet) {

            // 确定事项对应的办事人范围
            List<String> strings = sxbhMap.get(s);

            sqrMap.forEach((key, value) -> {
                // 没有办理s事件的记录，不作处理
                if(strings.contains(key)){
                    Set<String> thingSet = new HashSet<>();
                    for (Map<String,Object> map : value) {
                        thingSet.add(map.get("SPSXBH")+"");
                    }
                    if(thingSet.contains(s)) {
                        flag:for (int i = 0; i < value.size(); i++) {
                            // 当前list的第一条记录的事项编号
                            String sxbh = (String) value.get(i).get("SPSXBH");
                            if (s.equals(sxbh) && value.size() > i + 1 ) {
                                String sxbh1 = (String) value.get(i + 1).get("SPSXBH");
//                            System.out.println("事件" + s + "的后置事项为：" + value.get(i+1).get("SPSXBH"));
                                // 记录统计信息的sxbh集合
                                Set<String> sxbhSet = new HashSet<>();
                                if (result.get(s) == null || result.get(s).isEmpty()) {
                                    List<StatisticInfo> list1 = new ArrayList<>();
                                    StatisticInfo info = new StatisticInfo();
                                    info.setSxbh(sxbh1);
                                    info.setCount(1);
                                    list1.add(info);
                                    result.put(s, list1);
                                } else if (result.get(s) != null && !result.get(s).isEmpty()) {
                                    List<StatisticInfo> statisticInfos = result.get(s);
                                    for (StatisticInfo info : statisticInfos) {
                                        sxbhSet.add(info.getSxbh());
                                    }
                                    for (int i1 = 0; i1 < statisticInfos.size(); i1++) {
                                        // 获取后置事项统计信息
                                        StatisticInfo info = statisticInfos.get(i1);
                                        // 相同事件统计值+1
                                        if (info.getSxbh().equals(sxbh1) && sxbhSet.contains(sxbh1)) {
                                            info.setCount(info.getCount() + 1);
                                            continue flag;
                                        } else if (!info.getSxbh().equals(sxbh1) && !sxbhSet.contains(sxbh1)) {
                                            StatisticInfo info1 = new StatisticInfo();
                                            info1.setSxbh(sxbh1);
                                            info1.setCount(1);
                                            statisticInfos.add(info1);
                                            continue flag;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

//                }
            });
        }

        result.forEach((key,value)-> value.sort((o1, o2) -> {
            if(o1.getCount() > o2.getCount()) return -1;
            else if(o1.getCount() < o2.getCount()) return 1;
            return 0;
        }));

        result.forEach((key, value) -> logger.info("{} {}",key,value));

//        result.forEach((key,value) -> logger.info("事项 {} 的后置事项为 {}",infoMap.get(key).get("SPSXMC"),infoMap.get(value.get(0).getSxbh()).get("SPSXMC")));

        result.forEach((key,value) -> {
            String insertSql = "insert into jzts_item_item_test (area,itemcode,itemname,afterarea,afteritemcode,afteritemname) value (?,?,?,?,?,?)";
            StatisticInfo statisticInfo = null;
            if(value.get(0).getSxbh().equals(key)){
                if(value.size() > 1){
                    statisticInfo = value.get(1);
                }
            }
            try {
                runnerB.update(insertSql,
                        infoMap.get(key).get("XZQHDM"),
                        key,
                        infoMap.get(key).get("SPSXMC"),
                        statisticInfo==null?"无":infoMap.get(statisticInfo.getSxbh()).get("XZQHDM"),
                        statisticInfo==null?"无":statisticInfo.getSxbh(),
                        statisticInfo==null?"无":infoMap.get(statisticInfo.getSxbh()).get("SPSXMC"));
                logger.info(insertSql);
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        });
        // 计算处理时间
        long end = System.currentTimeMillis();
        logger.info("处理1w条数据花费 {} ms",(end - start));
    }

    /**
     * 保存后置事项编码和统计次数的实体类
     */
    static class StatisticInfo{
        private String sxbh;
        private Integer count;

        private String getSxbh() {
            return sxbh;
        }

        private void setSxbh(String sxbh) {
            this.sxbh = sxbh;
        }

        private Integer getCount() {
            return count;
        }

        private void setCount(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "StatisticInfo{" +
                    "sxbh='" + sxbh + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

}
