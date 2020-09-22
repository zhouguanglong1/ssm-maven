package com.zhougl.test.algo;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/21 14:57
 */
public class SlideWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        if(length == 0){
            return new int[0];
        }
        int[] result = new int[length-k+1];
        for(int i = 0;i < length-k+1;i++){
            int max = nums[i];
            int j = 0;
            while(j < k){
                max = Math.max(max,nums[i+j]);
                j++;
            }
            result[i] = max;
        }
        return result;
    }
}
