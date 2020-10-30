//package com.zhougl.jdk;
//
//import cn.hutool.http.HttpUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.deepexi.pay.domain.dto.OrderCenterRequestPayCenterParameter;
//import com.deepexi.pay.util.AuthUtil;
//
///**
// * @author zhougl
// * @version v1.0.0
// * @date 2019/9/25 10:40
// */
//public class Test {
//    public static void main(String[] args) {
//        OrderCenterRequestPayCenterParameter order  = new OrderCenterRequestPayCenterParameter();
//        //全局唯一订单ID，具体根据自己的业务而定
//        order.setOrderId("123123");
//        //系统IP
//        order.setIp("139.199.59.193");
//        //申请扣费
//        order.setApplyDeductionAmount("100");
//        //系统域名
//        order.setDoMain("www.baidu.com");
//        //系统编号
//        order.setPaySysAccessAuthCode("001");
//        //支付应用编号
//        order.setPayApplicationCode("MOBILE_CHECKSTAND");
//        //业务编号
//        order.setPaySysBizTypeCode("001");
//        //租户ID
//        order.setTenantId("2131232adsasdsadas");
//        //加密方式
//        order.setSignType("MD5");
//        //系统密钥
//        String authCode = "13b9e3089a7a4fcbb09e632986664e11";
//        //添加交易摘要
//        order.setTradingSummary("交易测试");
//
//        String sign =null;
//
//        String  json = JSONObject.toJSONString(order);
//        try {
//            //数据签名
//            sign = AuthUtil.makeSign(json, authCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        order.setSignature(sign);
//        String jsonString = JSONObject.toJSONString(order);
//        String url = "http://dev.deepexi.io/deepexi-pay-center/api/v1/pays/tranApps?tenantId=12221221";
//        //HttpUtil是http请求工具，示例中使用的是hutool的工具类
//        String result = HttpUtil.post(url, jsonString);
////        logger.info(result);
//    }
//}
