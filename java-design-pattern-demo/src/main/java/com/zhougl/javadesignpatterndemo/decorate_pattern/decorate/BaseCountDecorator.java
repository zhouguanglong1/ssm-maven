package com.zhougl.javadesignpatterndemo.decorate_pattern.decorate;

import com.zhougl.javadesignpatterndemo.decorate_pattern.entity.OrderDetail;

import java.math.BigDecimal;

/**
 * 装饰类抽象类（定义了组合的装饰类的成员变量，调用组合类的装饰行为）
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 17:34
 */
public abstract class BaseCountDecorator implements IBaseCount {
    private IBaseCount baseCount;

    public BaseCountDecorator(IBaseCount baseCount) {
        this.baseCount = baseCount;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if (baseCount != null) {
            payTotalMoney = baseCount.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}
