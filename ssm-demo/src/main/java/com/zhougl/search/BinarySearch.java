package com.zhougl.search;

/**
 * 使用二分查找法找字符串数组{"a","b","c","d","e","f","g","h"}中"g"
 * @author zhougl
 * 2019/1/18
 **/
public class BinarySearch {
    public static void main(String[] args) {
        String[] strings = {"a","b","c","d","e","f","g","h"};
        int g = search(strings, "g");
        System.out.println(strings[g]);
    }

    private static int search(String[] strings,String s){
        int left;
        int right;
        left = 0;
        right = strings.length-1;
        while (left <= right){
            int middle = (left+right)/2;
            if(strings[middle].compareTo(s) == 0){
                return middle;
            }else if(strings[middle].compareTo(s) < 0){
                left = middle + 1;
            }else if(strings[middle].compareTo(s) > 0){
                right = middle - 1;
            }
        }
        return -1;
    }
}
