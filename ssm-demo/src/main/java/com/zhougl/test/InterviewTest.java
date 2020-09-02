package com.zhougl.test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhougl
 * 2018/12/11
 **/
public class InterviewTest {
    abstract class Name{
        private String name;
        public abstract boolean isStupidName(String name);
    }

    public class SomeThing{
        void doSomeThing(){
            String s = "";
            int l = s.length();
        }
    }

    public static void main(String[] args){
        int s = 12973434%10000;
        String string = "dfsdf -010";
        string.length();
        string = string.trim();
        System.out.println(string);
        System.out.println(s);
        String[] strings = {"34","23","12"};
        int length = strings.length;

        List<String> list = new LinkedList<>();
        list.add("123");
        list.add("123");
        list.add("123");
        list.add("123");
        System.out.println(list);
    }
}
