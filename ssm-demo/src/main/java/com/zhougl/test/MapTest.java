//package com.zhougl.test;
//
//import com.deepexi.util.StringUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @author zhougl
// * @version v1.0.0
// * @since 2019/10/30 21:26
// */
//public class MapTest {
//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>(16);
//        map.put("a.name", "1");
//        map.put("a.b.c", "2");
//        map.put("a.d.e", "3");
//        map.put("a.b.f", "4");
//        map.put("a.hello", "5");
//        map.put("a.sex", "6");
//
//        Map<String, Object> result = new HashMap<>(16);
//
//        map.forEach((k, v) -> {
//            if(k.contains(".")){
//
//
//                result.put(k.substring(0,k.indexOf(".")),new Object());
//                String substring = k.substring(k.indexOf("."));
//                String key = null;
//
//                if(substring.contains(".")){
//
//                    Map<String,Object> after = new HashMap<>(18);
//                    after.put(substring.substring(0,substring.indexOf(".")),new Object());
//                    result.put(k.substring(0,k.indexOf(".")),after);
//
//                    String substring1 = substring.substring(substring.indexOf(","));
//                    key = substring1;
//                    if(substring1.contains(",")){
//
//                    }else{
//                        result.put(substring1,v);
//                    }
//
//                }else{
//                    result.put(substring,v);
//                }
//                if(StringUtil.isNotEmpty(key)){
//                    result.put(key,)
//                }
//            }else{
//                result.put(k,v);
//            }
//
//
//
//        });
//
//
//    }
//    static void recurse(String key, Object value, Map<String, Object> result){
//        if(!key.contains(".")){
//            Map<String,Object> map = new HashMap<>(18);
//            map.put(key,value);
//
//            result.put(key,value);
//        }
//        String first = key.substring(0, key.indexOf("."));
//        String after = key.substring(key.indexOf("."));
//
//        recurse(after,);
//        result.put(first,after);
//    }
//}
