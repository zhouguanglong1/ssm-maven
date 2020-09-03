package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:35
 */
@Data
public class UserCoupon {
    private int id; // 优惠券 ID
    private int userId; // 领取优惠券用户 ID
    private String sku; // 商品 SKU
    private BigDecimal coupon; // 优惠金额
}
