package com.zhougl.test.algo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author by zhougl
 * @date 2020/5/20 23:22
 */
public class RedPackDemo {
    public static void main(String[] args) {
        // 设计一个类似红包分发的系统和算法：微信红包
        // 红包金额
        // 先调用某个人的账户扣款接口
        BigDecimal redPack = BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_UP);
        List<BigDecimal> list = new ArrayList<>();
        // 外包个数
        int num = 10;
        Random r = new Random();
        while (redPack.compareTo(BigDecimal.ZERO) > 0 && num > 0){
            num --;
            double red = r.nextDouble()*(2*redPack.doubleValue()/num-0.01)+0.01;
            BigDecimal bigDecimal = BigDecimal.valueOf(red).setScale(2, RoundingMode.HALF_UP);
            list.add(bigDecimal);
            redPack = redPack.subtract(bigDecimal);
        }

        int num1 = 10;
        while (num1 > 0){
            num1 --;
            BigDecimal red = getRed(list);
            System.out.println(red);
        }
    }

    private static BigDecimal getRed(List<BigDecimal> list) {
        Iterator<BigDecimal> iterator = list.iterator();
        while (iterator.hasNext()){
            BigDecimal next = iterator.next();
            iterator.remove();
            return next;
        }
        return BigDecimal.ZERO;
    }
}
