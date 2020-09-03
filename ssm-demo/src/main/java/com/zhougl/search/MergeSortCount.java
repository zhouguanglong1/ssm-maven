package com.zhougl.search;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/16 15:55
 */
public class MergeSortCount {
    private int num = 0; // 全局变量或者成员变量

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                num += (j - q - 1);
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) {
            num += (j - q - 1);
            tmp[k++] = a[i++];
        }
        while (j <= r) {
            tmp[k++] = a[j++];
        }
        for (i = 0; i < r - p; ++i) {
            a[p + i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {1,6,5,2,3,4};
        MergeSortCount count = new MergeSortCount();
        int count1 = count.count(a, a.length);
        System.out.println(count1);

    }
}
