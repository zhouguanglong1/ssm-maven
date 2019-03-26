package com.zhougl.search;


/**
 * JAVA实现23,53,77,36,84,76,93,13,45,23快速排序算法，由小到大排序？
 * @author zhougl
 * 2019/1/18
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {23,53,77,36,84,76,93,13,45,23};
        int start = 0;
        int end = ints.length-1;
        quickSort(ints,start,end);
//        sort1(ints,start, end);
        /*for (int i : ints) {
            System.out.println(i);
        }*/
    }

    private static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        for (int h : arr) {
            System.out.println(h);
        }
        System.out.println("===========================");
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

    private static void sort(int[] ints,int start,int end){
        int low = start;
        int high = end;
        int key = ints[low];
        while (high > low){
            // 从后往前比较
            while (high > low && ints[high] > key) {
                high--;
            }
            if(ints[high] <= key){
                int temp = ints[high];
                ints[high] = ints[low];
                ints[low] = temp;
            }
            // 从前往后比较
            while (high > low && ints[high] <= key) {
                low++;
            }
            if(ints[low] >= key){
                int temp = ints[low];
                ints[low] = ints[high];
                ints[high] = temp;
            }
            //递归
            if(low>start) sort(ints,start,end-1);//左边序列。第一个索引位置到关键值索引-1
            if(high<end) sort(ints,high+1,end);//右边序列。从关键值索引+1到最后一个
        }
    }

    private static void sort1(int[] a,int low,int high){
        int start = low;
        int end = high;
        int key = a[low];


        while(end>start){
            //从后往前比较
            while(end>start&&a[end]>=key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if(a[end]<=key){
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while(end>start&&a[start]<=key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if(a[start]>=key){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            for (int i : a) {
                System.out.println(i);
            }
            System.out.println("------------------");
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if(start>low) sort1(a,low,start-1);//左边序列。第一个索引位置到关键值索引-1
        if(end<high) sort1(a,end+1,high);//右边序列。从关键值索引+1到最后一个
    }
}
