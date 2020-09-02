package com.zhougl.test.algo;

import java.util.*;

/**
 * @author by zhougl
 * @date 2020/5/21 21:02
 */
public class Test {
    public static void main(String[] args) {
        ListNode listNode = null;
        System.out.println(listNode);
        ArrayList<Integer> integers = printListFromTailToHead(listNode);
        System.out.println(integers);
    }

    public String firstNonRepeatedCharacter(String str) {
        // write code here
        String s = "stress";
        String[] split = s.split("");
        for (int i = 0; i < split.length; i++) {

        }
        Set<String> set = new HashSet<>();
        Map map = new TreeMap();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Integer number = (Integer) map.get(chars[i]);
            if (number == null) {
                map.put(chars[i], 1);
            } else {
                map.put(chars[i], (Integer) map.get(chars[i]) + 1);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if ((Integer) map.get(chars[i]) == 1) {
                return String.valueOf(chars[i]);
            }
        }

        return null;
    }

    /**
     * 1234 -> 4321
     *
     * @param number
     * @return
     */
    public int reverseNumber(int number) {
        // write code here
        if (number > 0) {
            int num1 = number % 10;

        }

        int result = 0;
        while (number > 0) {
            result = result * 10;
            result = result + number % 10;
            number = number / 10;
        }
        return result;
    }

    public boolean Find(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {

        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        String s = str.toString();
        s = s.replaceAll(" ", "%20");
        return s;
//        String[] split = s.split(" ");
//        StringBuffer sb = new StringBuffer();
//        boolean flag = false;
//        for (int i = 0; i < split.length; i++) {
//            if(i != split.length - 1){
//                sb.append(split[i]).append("%20");
//            }else{
//                sb.append(split[i]);
//            }
//        }
//
//        return sb.toString();
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        ListNode temp = listNode;
        while (temp!=null){
            list.add(temp.val);
            temp = temp.next;
        }
        for (int i = list.size() -1; i >= 0; i--) {
            result.add(list.get(i));
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
