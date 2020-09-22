package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:43
 */
@Data
public class UserRedPacket {
    private int id; // 红包 ID
    private int userId; // 领取用户 ID
    private String sku; // 商品 SKU
    private BigDecimal redPacket; // 领取红包金额
}
