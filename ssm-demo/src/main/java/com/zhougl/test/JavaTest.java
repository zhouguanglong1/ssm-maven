package com.zhougl.test;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/9 18:44
 */
public class JavaTest {
    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c==d);//true
        System.out.println(e==f);//false
        System.out.println(c==(a+b));//true
        System.out.println(c.equals(a+b));//true
        System.out.println(g ==(a+b));//true
        System.out.println(g.equals(a+b));//false

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1==str1.intern());//true
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2==str2.intern());//false
    }
}
