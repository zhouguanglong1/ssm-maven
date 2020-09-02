package com.zhougl.javadesignpatterndemo.refactor.origin;

/**
 * @author by zhougl
 * @date 2019/12/9 14:47
 */
public interface AlertRule {
    Rule getMatchRule(String rule);


    class Rule{
        private Long maxTps;

        public Long getMaxTps() {
            return maxTps;
        }

        public Rule(Long maxTps) {
            this.maxTps = maxTps;
        }

        public void setMaxTps(Long maxTps) {
            this.maxTps = maxTps;
        }
    }
}


