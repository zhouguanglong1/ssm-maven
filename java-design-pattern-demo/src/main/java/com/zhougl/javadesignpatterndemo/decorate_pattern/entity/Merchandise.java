package com.zhougl.javadesignpatterndemo.decorate_pattern.entity;

import com.zhougl.javadesignpatterndemo.decorate_pattern.enums.PromotionTypeEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/12 16:52
 */
@Data
public class Merchandise {
    private String sku;// 商品 SKU
    private String name; // 商品名称
    private BigDecimal price; // 商品单价
    private Map<PromotionTypeEnum, SupportPromotions> supportPromotions; // 支持促销类型
}
