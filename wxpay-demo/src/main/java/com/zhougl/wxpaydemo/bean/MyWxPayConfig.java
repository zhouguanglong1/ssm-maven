package com.zhougl.wxpaydemo.bean;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @author zhougl
 * 2018/9/5
 **/
public class MyWxPayConfig implements WXPayConfig{
    @Override
    public String getAppID() {
        return "wx123456";
    }

    @Override
    public String getMchID() {
        return "123456";
    }

    @Override
    public String getKey() {
        return "123456";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 6000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 8000;
    }
}
