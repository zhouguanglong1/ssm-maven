package com.zhougl.search;

/**
 * 基数排序
 * @author zhougl
 * 2019/2/5
 **/
public class BasicSearch {
    private static int getDigit(int x,int d){
        int a[] = {1,1,10,100};
        return ((x / a[d]) % 10);
    }
    private static void radixSort(int[] list,int begin,int end,int digit){
        final int radix = 10;
        int i = 0;
        int j = 0;
        int[] count = new int[radix];
        int[] bucket = new int[end - begin + 1];
        for (int i1 = 1; i1 <= digit; i1++) {
            for (int i2 = 0; i2 < radix; i2++) {
                count[i2] = 0;
            }
            for(i = begin;i <= end; i++){
                j = getDigit(list[i],i1);
                count[j] ++;
            }
            for (i = end; i >= begin; i--) {
                j = getDigit(list[i],i1);
                bucket[count[j]-1] = list[i];
                count[j]--;
            }
            for(i = begin,j = 0;i<=end;i++,j++){
                list[i] = bucket[j];
            }
        }
    }

    public static int[] sort(int[] list){
        radixSort(list,0,list.length - 1,3);
        return list;
    }

    public static void printlAll(int[] list){
        for (int i : list) {
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] array = {234,454,3,76,90,11,0,2,100};
//        BasicSearch search = new BasicSearch();
//        System.out.println(search.printlAll(array));
        printlAll(array);
        sort(array);
        printlAll(array);
    }
}
