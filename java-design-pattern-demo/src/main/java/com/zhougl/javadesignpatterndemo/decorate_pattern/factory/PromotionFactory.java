package com.zhougl.javadesignpatterndemo.decorate_pattern.factory;

import com.zhougl.javadesignpatterndemo.decorate_pattern.decorate.BaseCount;
import com.zhougl.javadesignpatterndemo.decorate_pattern.decorate.CouponDecorator;
import com.zhougl.javadesignpatterndemo.decorate_pattern.decorate.IBaseCount;
import com.zhougl.javadesignpatterndemo.decorate_pattern.decorate.RedPacketDecorator;
import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;
import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.SupportPromotions;
import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:46
 */
public class PromotionFactory {

    private PromotionFactory(){
    }

    public static BigDecimal getPayMoney(OrderDetail orderDetail) {

        // 获取给商品设定的促销类型
        Map<PromotionTypeEnum, SupportPromotions> supportPromotionslist = orderDetail.getMerchandise().getSupportPromotions();

        // 初始化计算类
        IBaseCount baseCount = new BaseCount();
        if (supportPromotionslist != null && supportPromotionslist.size() > 0) {
            // 遍历设置的促销类型，通过装饰器组合促销类型
            for (PromotionTypeEnum promotionType : supportPromotionslist.keySet()) {
                baseCount = promotion(supportPromotionslist.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 组合促销类型
     *
     * @param supportPromotions
     * @param baseCount
     * @return
     */
    private static IBaseCount promotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if (supportPromotions.getPromotionType() == PromotionTypeEnum.Coupon) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotionType() == PromotionTypeEnum.RedPacket) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }
}
