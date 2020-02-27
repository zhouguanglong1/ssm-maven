package com.zhougl.wxpaydemo.pay;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/11/15 11:32
 */
public interface PayGateway {

    boolean authAndVerifySign();

    void pay();

    void refund();

    void sign();

    void notifyPay();
}
