package com.zhougl.algo;

import lombok.Data;

import java.util.Arrays;

/**
 * 堆是一个完全二叉树（除了最后一层，其他层的节点都是满的，且最后一层的子节点都靠左排列）可以使用数组存储
 * 堆中每个节点的值，都大于等于（小于等于）左右子节点的值
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/8/29 15:05
 */
@Data
public class HeapIfy {

    /**
     * 存放数据的数组
     */
    private int[] a;
    /**
     * 数组最大存放的个数
     */
    private int n;
    /**
     * 数组当前存放的个数
     */
    private int count;

    public HeapIfy(int capacity){
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 往堆中插入数据，从下往上堆化
     * @param data
     */
    public void insert(int data){
        if(count >= n){
            return;
        }

        count ++;
        a[count] = data;
        int i = count;
        while(i/2 > 0 && a[i/2] < a[i]){
            swap(a,i,i/2);
            i = i/2;
        }
    }

    private void swap(int[] a,int i,int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void removeMax(){
        if(count == 0){
            return;
        }
        a[1] = a[count];
        -- count;
        heapIfy(a,count,1);
    }

    /**
     * 从上往下堆化
     */
    private void heapIfy(int[] a,int n,int i) {
        while (true){
            int maxPos = i;
            if(i*2 <= n && a[i] < a[i*2]){
                maxPos = maxPos *2;
            }
            else if(i*2+1 <= n && a[i] < a[i * 2 + 1]){
                maxPos = maxPos * 2 + 1;
            }
            if(maxPos == i){
                break;
            }
            swap(a,i,maxPos);
            i = maxPos;
        }
    }

    public static void main(String[] args) {
        HeapIfy heapIfy = new HeapIfy(10);
        heapIfy.insert(3);
        heapIfy.insert(7);
        heapIfy.insert(1);
        heapIfy.insert(2);
        heapIfy.insert(5);
        heapIfy.insert(4);
        heapIfy.insert(6);
        heapIfy.insert(8);
        heapIfy.insert(10);
        heapIfy.insert(9);
        Arrays.stream(heapIfy.getA()).forEach(System.out::println);
        System.out.println("===============================");
        heapIfy.removeMax();
        Arrays.stream(heapIfy.getA()).forEach(System.out::println);


        int[] nums = {2,2,1};
        int i = singleNumber(nums);
        System.out.println(i);

        int a = 10;
        a = a += a -= a*= 10;
        System.out.println(a);
    }


    public static int singleNumber(int[] nums) {
        if(nums.length == 0){
            return -1;
        }
        for(int i=1;i<nums.length;i++){
            nums[0] ^= nums[i];
        }

        return nums[0];
    }
}
