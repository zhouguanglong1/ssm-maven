package com.zhougl.search;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/9 17:37
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr1 = {1, 2, 2, 3, 4, 5};
        BinarySearchDemo demo = new BinarySearchDemo();
        System.out.println(demo.search(arr, 5));
        System.out.println(demo.searchFirst(arr1, 5));
    }

    /**
     * 查找数组arr中等于n的下标
     *
     * @param arr   有序不重复数组
     * @param value 指定的值
     * @return 查询的下标
     */
    public int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == value) {
                return mid;
            }
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查询数组arr中第一个value的下标
     *
     * @param arr   有序带重复元素的数组
     * @param value 指定值
     * @return 查询的下标
     */
    public int searchFirst(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询数组arr中最后一个value的下标
     *
     * @param arr   有序带重复元素的数组
     * @param value 指定值
     * @return 查询的下标
     */
    public int searchLast(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查询数组arr中最后一个大于等于value的下标
     *
     * @param arr   有序带重复元素的数组
     * @param value 指定值
     * @return 查询的下标
     */
    public int searchLastEqualAndLarge(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 查询数组arr中第一个小于等于value的下标
     *
     * @param arr   有序带重复元素的数组
     * @param value 指定值
     * @return 查询的下标
     */
    public int searchFirstEqualAndSmall(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
