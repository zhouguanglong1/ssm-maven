package com.zhougl.handler;

/**
 * @author zhougl
 * 2018/12/28
 **/
public class DebugTest {
    public static void main(String[] args){
        String s = "asdf";
        s.substring(1);
        modify(s);
    }

    private static void modify(String s){
        s = "hahaha";
        hahaha();
    }

    private static void hahaha() {
        System.out.println("fdsfs");
    }
}
