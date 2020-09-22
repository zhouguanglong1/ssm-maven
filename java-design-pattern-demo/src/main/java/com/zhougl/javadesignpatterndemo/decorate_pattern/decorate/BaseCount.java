package com.zhougl.javadesignpatterndemo.decorate_pattern.decorate;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;

import java.math.BigDecimal;

/**
 * 支付基本类（定义了商品原价的计算）
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:31
 */
public class BaseCount implements IBaseCount {

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println(" 商品sku为: " + orderDetail.getMerchandise().getSku());
        System.out.println(" 商品原单价金额为：" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}
