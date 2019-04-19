package com.zhougl.test;

import com.zhougl.utils.JsonUtil;

import java.util.*;

/**
 * 一个简单的递归例子
 * @author zhougl
 * 2019/4/19
 **/
public class Recursion {

    private static Map<String,Object> result = new HashMap<>();

    private static List<Map<String,Object>> list = new ArrayList<>();

    static {
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        Map<String,Object> map3 = new HashMap<>();
        Map<String,Object> map4 = new HashMap<>();
        map1.put("id",1);
        map1.put("parent_id",0);
        map1.put("description","电力抢险");
        map2.put("id",2);
        map2.put("parent_id",1);
        map2.put("description","电力");
        map3.put("id",3);
        map3.put("parent_id",2);
        map3.put("description","电线");
        map4.put("id",4);
        map4.put("parent_id",3);
        map4.put("description","设备");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
    }

    public static void main(String[] args){
        recursion(4,result);
        System.out.println(JsonUtil.fromJson(JsonUtil.toJSONString(result)));
    }

    private static void recursion(int i,Map<String,Object> result){
        Iterator<Map<String, Object>> iterator = list.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = iterator.next();
            Integer id = (Integer) map.get("id");
            Integer parent_id = (Integer) map.get("parent_id");
            if(i == id){
                if(result.isEmpty()){
                    result.put("id",id);
                    result.put("name",map.get("description"));
                    result.put("parent",null);
                    recursion(parent_id,result);
                } else if(!result.isEmpty() && null == result.get("parent")){
                    Map<String,Object> result1 = new HashMap<>();
                    result1.put("id",id);
                    result1.put("name",map.get("description"));
                    result.put("parent",result1);
                    recursion(parent_id,result1);
                } else{
                    Map<String,Object> result1 = new HashMap<>();
                    result1.put("id",id);
                    result1.put("name",map.get("description"));
                    Map<String,Object> m = (Map<String, Object>) result.get("parent");
                    m.put("parent",result1);
                    recursion(parent_id,m);
                }
//                recursion(parent_id,result);
            }/*else{
                continue;
            }*/
        }
    }


    private static void recursion(int i, List<Map<String, Object>> list) {
        Iterator<Map<String, Object>> iterator = list.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = iterator.next();
            Integer id = (Integer) map.get("id");
            Integer parent_id = (Integer) map.get("parent_id");
            if(i == id){
                if(result.isEmpty()){
                    result.put("id",id);
                    result.put("name",map.get("description"));
                    result.put("parent",null);
                } else if(!result.isEmpty() && null == result.get("parent")){
                    Map<String,Object> result1 = new HashMap<>();
                    result1.put("id",id);
                    result1.put("name",map.get("description"));
                    result.put("parent",result1);
                } else{
                    Map<String,Object> result1 = new HashMap<>();
                    result1.put("id",id);
                    result1.put("name",map.get("description"));
                    Map<String,Object> m = (Map<String, Object>) result.get("parent");
                    m.put("parent",result1);
                }
                recursion(parent_id,list);
            }/*else{
                continue;
            }*/
        }
        /*for (Map<String, Object> map : list) {
            Integer id = (Integer) map.get("id");
            Integer parent_id = (Integer) map.get("parent_id");
            if(i == id){
                if(null == result.get("parent")){
                    result.put("id",id);
                    result.put("name",map.get("description"));
                } else{
                    Map<String,Object> result1 = new HashMap<>();
                    result1.put("id",id);
                    result1.put("name",map.get("description"));
                    result.put("parent",result1);
                }
                list.remove(map);
            }else{
                continue;
            }
//            if(Objects.equals(id, parent_id)){
                recursion(parent_id,list);
//            } else{
//                recursion(parent_id,list);
//            }
        }*/
    }
//    private static class
}
