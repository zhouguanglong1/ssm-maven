package com.zhougl.javadesignpatterndemo.decorate_pattern;
import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.UserCoupon;
import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.UserRedPacket;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.*;
import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;
import com.zhougl.javadesignpatterndemo.decorate_pattern.factory.PromotionFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:44
 */
public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        init(order);

        for(OrderDetail orderDetail: order.getList()) {
            BigDecimal payMoney = PromotionFactory.getPayMoney(orderDetail);
            orderDetail.setPayMoney(payMoney);
            System.out.println(" 最终支付金额：" + orderDetail.getPayMoney());
        }
    }

    private static void init(Order order) {
        List<OrderDetail> list = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(1);
        orderDetail.setPayMoney(new BigDecimal(20));
        Merchandise merchandise = new Merchandise();
        merchandise.setName("iphone");
        merchandise.setSku("iphone 11 pro max 金 256");
        merchandise.setPrice(new BigDecimal(9999));
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(1);
        userCoupon.setUserId(1);
        userCoupon.setSku("iphone 11 pro max 金 256");
        userCoupon.setCoupon(new BigDecimal(200));

        UserRedPacket userRedPacket = new UserRedPacket();
        userRedPacket.setId(1);
        userRedPacket.setUserId(1);
        userRedPacket.setSku("iphone 11 pro max 金 256");
        userRedPacket.setRedPacket(new BigDecimal(99));

        Map<PromotionTypeEnum,SupportPromotions> map = new HashMap<>(8);
        SupportPromotions supportPromotions = new SupportPromotions();
        supportPromotions.setId(1);
        supportPromotions.setPromotionType(PromotionTypeEnum.Coupon);
        supportPromotions.setPriority(1);
        supportPromotions.setUserCoupon(userCoupon);

        SupportPromotions supportPromotions1 = new SupportPromotions();
        supportPromotions1.setId(2);
        supportPromotions1.setPromotionType(PromotionTypeEnum.RedPacket);
        supportPromotions1.setPriority(2);
        supportPromotions1.setUserRedPacket(userRedPacket);

        map.put(PromotionTypeEnum.Coupon,supportPromotions);
        map.put(PromotionTypeEnum.RedPacket,supportPromotions1);

        merchandise.setSupportPromotions(map);
        orderDetail.setMerchandise(merchandise);

        list.add(orderDetail);
        order.setList(list);
    }
}
