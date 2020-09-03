package com.zhougl.test.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhougl
 * @since 2019/9/5 14:18
 */
public class Print2 {
    public static void main(String[] args) {
        TransferStoragePostRequestDTO requestDTO = new TransferStoragePostRequestDTO();
        requestDTO.setOrderType(1);
        requestDTO.setOperateType(6);

        List<TransferStoragePostRequestDTO.TransferOrderDTO> list = Lists.newArrayList();

        for (int i = 1; i <= 1000; i++) {
            TransferStoragePostRequestDTO.TransferOrderDTO transferOrderDTO = new TransferStoragePostRequestDTO.TransferOrderDTO();
            transferOrderDTO.setBaasTenantId("${baasTenantId}");
            transferOrderDTO.setTenantCode("${tenantCode}");

            TransferStoragePostRequestDTO.TransferOrderDTO.OutboundOrderDTO outboundOrderDTO = new TransferStoragePostRequestDTO.TransferOrderDTO.OutboundOrderDTO();
            outboundOrderDTO.setToDepotName("南昌OPPO");
            outboundOrderDTO.setToDistrictId("111");
            outboundOrderDTO.setToAddress("南昌");
            outboundOrderDTO.setToPhone("${__Random(13700000000,13799999999,)}");
            outboundOrderDTO.setToConsignee("钱总");
            outboundOrderDTO.setDistributorId(i+"");
            outboundOrderDTO.setDepotId(i+"");
            outboundOrderDTO.setExpectedOutboundDate(LocalDateTime.now());
            outboundOrderDTO.setDeliveryType(1);
            outboundOrderDTO.setToDistributorId("${__Random(1,99999,)}");
            outboundOrderDTO.setToDepotId("${__Random(1,99999,)}");

            TransferStoragePostRequestDTO.TransferOrderDTO.OutboundOrderSkuDTO skuDTO = new TransferStoragePostRequestDTO.TransferOrderDTO.OutboundOrderSkuDTO();
            skuDTO.setSkuId(i+"");
            skuDTO.setSkuName("OPPO R15");
            skuDTO.setSkuPrice(new BigDecimal(2999));
            skuDTO.setSkuQty(1);

            transferOrderDTO.setOutboundOrderDTO(outboundOrderDTO);
            transferOrderDTO.setOutboundOrderSkuDTOList(Collections.singletonList(skuDTO));

            list.add(transferOrderDTO);
        }

        requestDTO.setList(list);

        System.out.println(list.toString());

    }

    public static class TransferStoragePostRequestDTO implements Serializable {


        private static final long serialVersionUID = 8045566215018165688L;
        @ApiModelProperty("调拨数组")
        private List<TransferOrderDTO> list;

        private Integer operateType;

        private Integer orderType;

        public Integer getOperateType() {
            return operateType;
        }

        public void setOperateType(Integer operateType) {
            this.operateType = operateType;
        }

        public Integer getOrderType() {
            return orderType;
        }

        public void setOrderType(Integer orderType) {
            this.orderType = orderType;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }

        public static class TransferOrderDTO {
            @ApiModelProperty("tenantCode")
            private String tenantCode;

            @ApiModelProperty("baasTenantId")
            private String baasTenantId;

            /**
             * 出库单
             */
            @ApiModelProperty("出库单")
            private OutboundOrderDTO outboundOrderDTO;

            /**
             * sku出库单
             */
            @ApiModelProperty("sku出库单")
            private List<OutboundOrderSkuDTO> outboundOrderSkuDTOList;

            @Override
            public String toString() {
                return JSON.toJSONString(this);
            }

            public static class OutboundOrderDTO {

                /**
                 * 收货存储点名称
                 */
                @ApiModelProperty(value = "收货存储点名称")
                private String toDepotName;
                /**
                 * 收货存储点行政区划信息
                 */
                @ApiModelProperty(value = "收货存储点行政区划信息")
                private String toDistrictId;
                /**
                 * 收货存储点地址
                 */
                @ApiModelProperty(value = "收货存储点地址")
                private String toAddress;
                /**
                 * 收货存储点电话
                 */
                @ApiModelProperty(value = "收货存储点电话")
                private String toPhone;
                /**
                 * 收货存储点收货人
                 */
                @ApiModelProperty(value = "收货存储点收货人")
                private String toConsignee;


                /**
                 * 渠道商ID
                 */
                @ApiModelProperty(value = "渠道商ID")
                private String distributorId;
                /**
                 * 发货存储点ID
                 */
                @ApiModelProperty(value = "发货存储点ID")
                private String depotId;


                /**
                 * 预计出库日期
                 */
                @ApiModelProperty(value = "预计出库日期")
                private LocalDateTime expectedOutboundDate;
                /**
                 * 1:配送，2：自提
                 */
                @ApiModelProperty(value = "1:配送，2：自提")
                private Integer deliveryType;

                /**
                 * 收货渠道商ID
                 */
                @ApiModelProperty(value = "收货渠道商ID")
                private String toDistributorId;
                /**
                 * 收货存储点ID
                 */
                @ApiModelProperty(value = "收货存储点ID")
                private String toDepotId;


                public String getToDepotName() {
                    return toDepotName;
                }

                public void setToDepotName(String toDepotName) {
                    this.toDepotName = toDepotName;
                }

                public String getToDistrictId() {
                    return toDistrictId;
                }

                public void setToDistrictId(String toDistrictId) {
                    this.toDistrictId = toDistrictId;
                }

                public String getToAddress() {
                    return toAddress;
                }

                public void setToAddress(String toAddress) {
                    this.toAddress = toAddress;
                }

                public String getToPhone() {
                    return toPhone;
                }

                public void setToPhone(String toPhone) {
                    this.toPhone = toPhone;
                }

                public String getToConsignee() {
                    return toConsignee;
                }

                public void setToConsignee(String toConsignee) {
                    this.toConsignee = toConsignee;
                }

                public String getDistributorId() {
                    return distributorId;
                }

                public void setDistributorId(String distributorId) {
                    this.distributorId = distributorId;
                }

                public String getDepotId() {
                    return depotId;
                }

                public void setDepotId(String depotId) {
                    this.depotId = depotId;
                }

                public LocalDateTime getExpectedOutboundDate() {
                    return expectedOutboundDate;
                }

                public void setExpectedOutboundDate(LocalDateTime expectedOutboundDate) {
                    this.expectedOutboundDate = expectedOutboundDate;
                }

                public Integer getDeliveryType() {
                    return deliveryType;
                }

                public void setDeliveryType(Integer deliveryType) {
                    this.deliveryType = deliveryType;
                }

                public String getToDistributorId() {
                    return toDistributorId;
                }

                public void setToDistributorId(String toDistributorId) {
                    this.toDistributorId = toDistributorId;
                }

                public String getToDepotId() {
                    return toDepotId;
                }

                public void setToDepotId(String toDepotId) {
                    this.toDepotId = toDepotId;
                }

                @Override
                public String toString() {
                    return JSON.toJSONString(this);
                }
            }

            public static class OutboundOrderSkuDTO {

                /**
                 * skuID
                 */
                @ApiModelProperty(value = "skuID")
                private String skuId;
                /**
                 * sku名称
                 */
                @ApiModelProperty(value = "sku名称")
                private String skuName;
                /**
                 * sku价格
                 */
                @ApiModelProperty(value = "sku价格")
                private BigDecimal skuPrice;

                /**
                 * sku发货总数量
                 */
                @ApiModelProperty(value = "sku发货总数量")
                private Integer skuQty;

                /**
                 * 已发货sku数量
                 */
                @ApiModelProperty(value = "已发货sku数量")
                private Integer shippedQty;

                @Override
                public String toString() {
                    return JSON.toJSONString(this);
                }


                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getSkuName() {
                    return skuName;
                }

                public void setSkuName(String skuName) {
                    this.skuName = skuName;
                }

                public BigDecimal getSkuPrice() {
                    return skuPrice;
                }

                public void setSkuPrice(BigDecimal skuPrice) {
                    this.skuPrice = skuPrice;
                }

                public Integer getSkuQty() {
                    return skuQty;
                }

                public void setSkuQty(Integer skuQty) {
                    this.skuQty = skuQty;
                }

                public Integer getShippedQty() {
                    return shippedQty;
                }

                public void setShippedQty(Integer shippedQty) {
                    this.shippedQty = shippedQty;
                }
            }

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

            public OutboundOrderDTO getOutboundOrderDTO() {
                return outboundOrderDTO;
            }

            public void setOutboundOrderDTO(OutboundOrderDTO outboundOrderDTO) {
                this.outboundOrderDTO = outboundOrderDTO;
            }

            public List<OutboundOrderSkuDTO> getOutboundOrderSkuDTOList() {
                return outboundOrderSkuDTOList;
            }

            public void setOutboundOrderSkuDTOList(List<OutboundOrderSkuDTO> outboundOrderSkuDTOList) {
                this.outboundOrderSkuDTOList = outboundOrderSkuDTOList;
            }
        }

        public List<TransferOrderDTO> getList() {
            return list;
        }

        public void setList(List<TransferOrderDTO> list) {
            this.list = list;
        }
    }
}
