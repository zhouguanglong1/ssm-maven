package com.zhougl.javadesignpatterndemo.decorate_pattern.decorate;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;

import java.math.BigDecimal;

/**
 * 计算支付金额接口类
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:30
 */
public interface IBaseCount {
    BigDecimal countPayMoney(OrderDetail orderDetail);
}
