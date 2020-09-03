package com.zhougl.javadesignpatterndemo.decorate_pattern.enums;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:45
 */
public enum PromotionTypeEnum {

    RedPacket(1,"红包"),
    Coupon(2,"优惠券"),
    ;
    private Integer type;
    private String msg;

    PromotionTypeEnum(Integer type,String msg){
        this.msg = msg;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
