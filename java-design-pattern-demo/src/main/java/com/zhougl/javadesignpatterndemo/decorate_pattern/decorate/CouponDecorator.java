package com.zhougl.javadesignpatterndemo.decorate_pattern.decorate;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;
import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;

import java.math.BigDecimal;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:37
 */
public class CouponDecorator extends BaseCountDecorator {
    public CouponDecorator(IBaseCount baseCount) {
        super(baseCount);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        super.countPayMoney(orderDetail);
        return countCouponPayMoney(orderDetail);
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal coupon =  orderDetail.getMerchandise().getSupportPromotions().get(PromotionTypeEnum.Coupon).getUserCoupon().getCoupon();
        System.out.println(" 优惠券金额：" + coupon);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(coupon));
        return orderDetail.getPayMoney();
    }


}
