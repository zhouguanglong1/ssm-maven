package com.zhougl.test.collection;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhougl
 * @since 2019/8/23 16:04
 */
public class CheckListEquals {
    public static void main(String[] args) {
        List<SkuInfo> list = Lists.newArrayList();
        List<SkuInfo> list1 = Lists.newArrayList();


    }

    static class SkuInfo{
        private String skuId;
        private Long skuQty;

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public SkuInfo(String skuId, Long skuQty) {
            this.skuId = skuId;
            this.skuQty = skuQty;
        }

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
    }
}
