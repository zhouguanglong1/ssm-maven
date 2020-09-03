package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:53
 */
@Data
public class OrderDetail {
    private int id; // 详细订单 ID
    private int orderId;// 主订单 ID
    private Merchandise merchandise; // 商品详情
    private BigDecimal payMoney; // 支付单价
}
