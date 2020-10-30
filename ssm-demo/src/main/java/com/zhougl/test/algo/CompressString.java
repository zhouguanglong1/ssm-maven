package com.zhougl.test.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * 把aaaabbbbcccc转换成a4b4c4
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/22 10:31
 */
public class CompressString {

    /**
     * 版本1，使用hashmap
     * @param s
     * @return
     */
    public static String compress(String s){
        Map<String,Integer> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        int length = s.length();
        char preStr = s.charAt(0);
        map.put(String.valueOf(preStr),1);
        for(int i=1;i<length;i++){
            if(s.charAt(i)!=preStr){
                stringBuilder.append(preStr).append(map.get(String.valueOf(preStr)));
                map.remove(String.valueOf(preStr));
                preStr = s.charAt(i);
                map.put(String.valueOf(preStr),1);
            }else{
                if(map.containsKey(String.valueOf(preStr))){
                    map.put(String.valueOf(preStr),map.get(String.valueOf(preStr))+1);
                }else{
                    map.put(String.valueOf(preStr),1);
                }
            }
        }

        map.forEach((key,value)-> stringBuilder.append(key).append(value));

        return stringBuilder.toString();
    }

    /**
     * 双指针法
     * @param s
     * @return
     */
    public static String compress2(String s){
        int length = s.length();
        char preStr = s.charAt(0);
        StringBuilder stringBuilder = new StringBuilder().append(preStr);
        int count = 1;
        for(int i=1;i<length;i++){
            if(s.charAt(i)!=preStr){
                stringBuilder.append(count).append(preStr);
                preStr = s.charAt(i);
                count = 1;
            }else{
                count ++;
            }
        }

        String res = stringBuilder.append(count).toString();

        return res.length() < length ? res:s;
    }


    public static void main(String[] args) {
        System.out.println(compress2("aaaabbbbccccdddd"));
    }
}
