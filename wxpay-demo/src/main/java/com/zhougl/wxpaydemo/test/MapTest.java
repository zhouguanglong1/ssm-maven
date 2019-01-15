package com.zhougl.wxpaydemo.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhougl
 * 2018/9/6
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("no1","ewe");
        map.put("no2","dsd");
        map.put("no3","fgg");
        map.put("no4","axc");
        map.put("no5","bgh");

        Map<String,String> map1 = new HashMap<>(map);
        map1.remove("no1");

        System.out.println(map);
        System.out.println();
        System.out.println(map1);


        Object o = 0.01;
        double t = (double) o;
        System.out.println(t);
        int y = (int)(t*100);
//        int i = Integer.valueOf(o+"")*100;
        Map<String,Object> req = new HashMap<>();
//        req.put("base_balance",0.01);
        req.put("base_balance",0.01);
        System.out.println(req.get("base_balance").getClass());
        String base_balance = "";
        if(req.get("base_balance") instanceof Integer){
            base_balance = String.valueOf(((int) req.get("base_balance")) * 100);
        }else if(req.get("base_balance") instanceof Double){
            base_balance = String.valueOf((int) (((double) req.get("base_balance")) * 100));
        }

        System.out.println("测试======" + base_balance);
//        System.out.println(String.valueOf(y));

        BigDecimal b = new BigDecimal(0.01);
        req.put("doubleValue",b);
//        Object abc = b;
        Object abc = req.get("doubleValue");
        double v1 = ((BigDecimal) abc).doubleValue();
        int i1 = (int) (v1 * 100);
        System.out.println("i1=========="+i1);
        double v = b.doubleValue();
        System.out.println(v);
        int i = (int) (v * 100);
        System.out.println(i);



        Integer orderId = 123;
        String rerere = String.valueOf(orderId) + "-" + UUID.randomUUID().toString().replaceAll("-","").substring(0,10);
        System.out.println(rerere);
        String out_trade_no = "123-33242rwerew";
        out_trade_no = out_trade_no.substring(0,out_trade_no.indexOf("-"));
        System.out.println(out_trade_no);
    }
}
