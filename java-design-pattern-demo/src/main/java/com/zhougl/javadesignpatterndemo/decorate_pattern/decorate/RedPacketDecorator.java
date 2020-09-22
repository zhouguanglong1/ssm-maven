package com.zhougl.javadesignpatterndemo.decorate_pattern.decorate;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;
import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;

import java.math.BigDecimal;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:42
 */
public class RedPacketDecorator extends BaseCountDecorator {

    public RedPacketDecorator(IBaseCount baseCount) {
        super(baseCount);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        super.countPayMoney(orderDetail);
        return countCouponPayMoney(orderDetail);
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {
        BigDecimal redPacket = orderDetail.getMerchandise().getSupportPromotions().get(PromotionTypeEnum.RedPacket).getUserRedPacket().getRedPacket();
        System.out.println(" 红包优惠金额：" + redPacket);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacket));
        return orderDetail.getPayMoney();

    }
}
