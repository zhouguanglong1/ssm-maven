package com.zhougl.test.collection;
import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

/**
 * @author zhougl
 * @since 2019/8/27 18:45
 */
public class Print {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        int base = 60000;
//        builder.append("[");
//        for (int i = 0; i < 20000; i++) {
//            SkuRequestDTO skuRequestDTO = new SkuRequestDTO();
//            skuRequestDTO.setSkuId(String.valueOf(base + i));
//            skuRequestDTO.setSkuQty(100L);
//            String s = skuRequestDTO.toString();
//            builder.append(s);
//            if(i != 19999){
//                builder.append(",");
//            }
//        }
//        builder.append("]");
        builder.append("[");
        for (int i = 0; i < 20000; i++) {
            SaleStockIncSalableRequestDTO.SaleStockRequestDTO requestDTO = new SaleStockIncSalableRequestDTO.SaleStockRequestDTO();
            requestDTO.setTenantCode("YDOPPO");
            requestDTO.setBaasTenantId("2");
            requestDTO.setOperationType(26);
            requestDTO.setExtendId("1122");
            requestDTO.setExtendNo("1");
            requestDTO.setExtendType(2);
            requestDTO.setDistributorId("777358");
            SaleStockIncSalableRequestDTO.SaleStockRequestDTO.SkuRequestDTO skuRequestDTO = new SaleStockIncSalableRequestDTO.SaleStockRequestDTO.SkuRequestDTO();
            skuRequestDTO.setSkuId(String.valueOf(base + i));
            skuRequestDTO.setSkuQty(100L);
            requestDTO.setSkuList(Collections.singletonList(skuRequestDTO));
            builder.append(requestDTO.toString());
            if(i != 19999){
                builder.append(",\r\n");
            }
        }

        builder.append("]");


        System.out.println(builder.toString());
    }

    public static class SkuRequestDTO {
        private static final long serialVersionUID = -5198053611028807023L;
        /**
         * SKU ID
         */
        private String skuId;
        /**
         * SKU 数量
         */
        private Long skuQty;
        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public Long getSkuQty() {
            return skuQty;
        }

        public void setSkuQty(Long skuQty) {
            this.skuQty = skuQty;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public static class SaleStockIncSalableRequestDTO {
        private static final long serialVersionUID = -8715389360487751283L;
        /**
         * 渠道商sku列表
         */
        @ApiModelProperty(value = "渠道商列表")
        private List<SaleStockRequestDTO> list;

        public List<SaleStockRequestDTO> getList() {
            return list;
        }

        public void setList(List<SaleStockRequestDTO> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

        @ApiModel(description = "库存请求参数")
        public static class SaleStockRequestDTO {
            private static final long serialVersionUID = -2226915557013003081L;

            /**
             * 租户
             */
            private String tenantCode;
            /**
             * 基础租户
             */
            private String baasTenantId;

            public String getTenantCode() {
                return tenantCode;
            }

            public void setTenantCode(String tenantCode) {
                this.tenantCode = tenantCode;
            }

            public String getBaasTenantId() {
                return baasTenantId;
            }

            public void setBaasTenantId(String baasTenantId) {
                this.baasTenantId = baasTenantId;
            }
            /**
             * 渠道商ID
             */
            @ApiModelProperty(value = "渠道商ID")
            private String distributorId;
            /**
             * 序列号
             */
            @ApiModelProperty(value = "序列号")
            private String serialNo;
            /**
             * 单据ID
             */
            @ApiModelProperty(value = "单据ID")
            private String extendId;
            /**
             * 单据号
             */
            @ApiModelProperty(value = "单据号")
            private String extendNo;
            /**
             * 单据类型
             *
             * <p>
             *      1. 销售订单 2. 调度出库单 3. 发货单 4 签收单，默认是值0
             * </p>
             */
            @ApiModelProperty(value = "单据类型")
            private Integer extendType;
            /**
             * 操作类型
             *
             * <p>
             *      1.批发签收入库 2.创建调拨出库单 3.调拨签收入库 4.下单 5.拒绝开单  6.支付 7.签收入库 8.取消订单 9.修改订单 10.创建退货出库单 11.退货签收入库  12.创建退货单 13.退货释放占用 14.创建特殊出库单 15.特殊签收入库 16.在途库存同步到销售库存 17.销售库存扣减掉在途库存 18.批发入库发货 19.调拨出库发货
             * </p>
             */
            @ApiModelProperty(value = "操作类型")
            private Integer operationType;
            /**
             * sku id和数量qty列表
             */
            @ApiModelProperty(value = "sku id和数量列表")
            private List<SkuRequestDTO> skuList;

            public Integer getOperationType() {
                return operationType;
            }

            public void setOperationType(Integer operationType) {
                this.operationType = operationType;
            }

            public String getExtendId() {
                return extendId;
            }

            public void setExtendId(String extendId) {
                this.extendId = extendId;
            }

            public String getExtendNo() {
                return extendNo;
            }

            public void setExtendNo(String extendNo) {
                this.extendNo = extendNo;
            }

            public Integer getExtendType() {
                return extendType;
            }

            public void setExtendType(Integer extendType) {
                this.extendType = extendType;
            }

            public String getDistributorId() {
                return distributorId;
            }

            public void setDistributorId(String distributorId) {
                this.distributorId = distributorId;
            }

            public String getSerialNo() {
                return serialNo;
            }

            public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }

            public List<SkuRequestDTO> getSkuList() {
                return skuList;
            }

            public void setSkuList(List<SkuRequestDTO> skuList) {
                this.skuList = skuList;
            }

            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }

            @ApiModel(description = "sku信息")
            public static class SkuRequestDTO {
                private static final long serialVersionUID = 6047744008321371748L;
                /**
                 * SKU ID
                 */
                @ApiModelProperty(value = "SKU ID")
                private String skuId;
                /**
                 * SKU 数量
                 */
                @ApiModelProperty(value = "SKU 数量")
                private Long skuQty;
                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public Long getSkuQty() {
                    return skuQty;
                }

                public void setSkuQty(Long skuQty) {
                    this.skuQty = skuQty;
                }

                @Override
                public String toString() {
                    return JSON.toJSONString(this);
                }
            }
        }
    }
}
