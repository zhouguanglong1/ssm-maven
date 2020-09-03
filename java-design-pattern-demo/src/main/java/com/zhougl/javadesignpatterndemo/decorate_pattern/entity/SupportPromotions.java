package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;
import lombok.Data;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:51
 */
@Data
public class SupportPromotions implements Cloneable {
    private int id;// 该商品促销的 ID
    private PromotionTypeEnum promotionType;// 促销类型 1\优惠券 2\红包
    private int priority; // 优先级
    private UserCoupon userCoupon; // 用户领取该商品的优惠券
    private UserRedPacket userRedPacket; // 用户领取该商品的红包

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SupportPromotions supportPromotions = null;
        try {
            supportPromotions = (SupportPromotions) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return supportPromotions;
    }
}
