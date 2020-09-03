package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:34
 */
@Data
public class Order {
    private int id; // 订单 ID
    private String orderNo; // 订单号
    private BigDecimal totalPayMoney; // 总支付金额
    private List<OrderDetail> list; // 详细订单列表
}
