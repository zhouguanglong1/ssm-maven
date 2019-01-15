package com.zhougl.wxpaydemo.test;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhougl.wxpaydemo.bean.MyWxPayConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * 2018/9/5
 **/
public class WxpayDemo {
    public static void main(String[] args) throws Exception {
        MyWxPayConfig config = new MyWxPayConfig();
        WXPay wxPay = new WXPay(config);

        Map<String,String> data = new HashMap<>();
        // 微信支付分配的公众账号ID（企业号corpid即为此appId）
//        data.put("appid",config.getAppID());
        // 微信支付分配的商户号
//        data.put("mch_id",config.getMchID());
        // 设备号
        data.put("device_info","WEB");
        // 随机字符串
//        data.put("nonce_str", WXPayUtil.generateNonceStr());
        // 商品简单描述，该字段请按照规范传递
        // PC网站	扫码支付	浏览器打开的网站主页title名 -商品概述	腾讯充值中心-QQ会员充值
        data.put("body","测试支付006 - 0.01元");
        // 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。
        data.put("out_trade_no","1882508");
        // 订单总金额，单位为分
        data.put("total_fee","1");
        // 标价币种(默认人民币)
        data.put("fee_type", "CNY");
        // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        data.put("spbill_create_ip","61.144.20.248");
        // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        data.put("notify_url","http://2t7e2k.natappfree.cc/smsweb/payNotify");
        // JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付
        data.put("trade_type","NATIVE");
        // trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
        data.put("product_id","555");

        // 通过签名算法计算得出的签名值
        // 签名传的key为商户平台设置的密钥key
//        data.put("sign",WXPayUtil.generateSignedXml(data,config.getKey()));

        Map<String, String> order = wxPay.unifiedOrder(data);


        System.out.println("返回信息===="+order);

        /*String return_code = order.get("return_code");
        String result_code = order.get("result_code");
        if(WXPayConstants.SUCCESS.equals(return_code) && WXPayConstants.SUCCESS.equals(result_code)){

        }*/
    }
}
