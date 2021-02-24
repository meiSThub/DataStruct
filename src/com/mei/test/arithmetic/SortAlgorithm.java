package com.mei.test.arithmetic;

import java.util.LinkedList;

/**
 * 排序算法
 * 1.冒泡排序
 * 2.选择排序
 * 3.快速排序
 * 4.归并排序
 * 5.基数排序
 * 6.插入排序
 * 7.希尔排序
 */
public class SortAlgorithm {

    /**
     * 1.蛮力法之冒泡排序
     *
     * 冒泡排序：8个数以内的排序可用
     *
     * @param array
     */
    public static void bubblingSort(int array[]) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = true;
            // 假设数组长度为10，则第一轮循环8次，就可以把最大的数移动的最后，第二轮循环7次就可以把第二大的数移动到倒数第二位
            for (int j = 0; j < i; j++) {// 经过第一轮循环，把最大的数移到了最后
                if (array[j] > array[j + 1]) {// 两数相比，大的后移
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
    }

    /**
     * 2.蛮力法之选择排序。
     *
     * 第一次：固定第0项，然后在下标1-array.length中，找一个值最小的，与第0项进行交换。
     * 第二次：固定第1项，然后在下标2-array.length中，找一个值最小的，与第1项进行交换。
     * 第三次：固定第2项，然后在下标3-array.length中，找一个值最小的，与第2项进行交换。
     *
     * 省略.................
     *
     * 这样，当循环结束的时候，排序就完成了
     *
     * @param array
     */
    public static void selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index]) {// 在数组中找一个最小的，并记录下标
                    index = j;
                }
            }

            if (index != i) {//
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }
    }

    public static final boolean RIGHT_TO_LEFT = true;
    public static final boolean LEFT_TO_RIGHT = false;

    /**
     * 3.分治法之：快速排序
     *
     * 适用场景：数据量大且是线性结构
     *
     * 短处：1、有大量重复数据的时候，性能不好 ；2、单向链式结构处理性能不好（一般来说，链式都不使用）
     *
     * 把数组array：31, 21, 59, 68, 12, 40 进行排序
     */
    public static void quickSort(int[] array, int start, int end) {

        if (end - start <= 0) {// 两个指针重合时，退出
            return;
        }
        int low = start;
        int high = end;
        int stackValue = array[low];
        boolean direction = RIGHT_TO_LEFT;
        L1: while (low < high) {
            if (direction == RIGHT_TO_LEFT) {// 从右到左遍历F
                for (int i = high; i > low; i--) {// i>left就好，不用i>=left;
                    if (array[i] <= stackValue) {// 找到比暂存只小的数，并填充到低位
                        high = i;
                        array[low++] = array[i];
                        direction = !direction;// 换方向，开始从左到右遍历
                        continue L1;
                    }
                }
                high = low;
            } else {// 从左到右遍历
                for (int i = low; i < high; i++) {
                    if (array[i] >= stackValue) {// 找到比暂存值大的数，并填充到高位
                        low = i;
                        array[high--] = array[i];
                        direction = !direction;// 换方向，开始从右到左遍历
                        continue L1;
                    }
                }
                low = high;
            }
        }
        array[low] = stackValue;// 最后把

        /**
         * 31, 21, 59, 68, 12, 40
         *
         * 经过上面一轮操作之后，数据就变成了： 12,21,31,68,59,40
         * 在low=31之前的数据都比31小，在low=31之后的数据都比31大， 接着就对31前面的数据和后面的数据进行排序。
         */
        quickSort(array, start, low - 1);// 对左边的数据进行排序
        quickSort(array, low + 1, end);// 对右边的数据进行排序
    }

    /**
     * 4.
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    public static void merge(int[] array, int left, int mid, int right) {
        // mid作为右边数组 数据填充的起始下标
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];

        // 填充左边数组
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }

        // 填充右边数组
        for (int i = mid; i <= right; i++) {
            rightArray[i - mid] = array[i];
        }

        // 合并数据
        int leftIndex = 0;
        int rightIndex = 0;
        int currIndex = 0;
        // 归并数据
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                array[left + currIndex++] = leftArray[leftIndex++];
            } else {
                array[left + currIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftSize) {// 说明左边的数组还有数据没有放入到array中
            array[left + currIndex++] = leftArray[leftIndex++];
        }

        // 只可能存在一个
        while (rightIndex < rightSize) {// 说明右边的数组还有数据没有被放入到array中
            array[left + currIndex++] = rightArray[rightIndex++];
        }

    }

    /**
     * 5.分治法之：归并排序
     *
     * 适用场景：数据量大并且是链式结构 ，相当于是树的后续遍历
     *
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSort(int[] array, int left, int right) {
        if (left == right) {
            return;
        } else {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);// 对左边进行排序
            mergeSort(array, mid + 1, right);// 对右边进行排序
            merge(array, left, mid + 1, right);// 把两次排序的结果合并
        }
    }

    /**
     * 6.基数排序
     */
    public static void radixSort(LinkedList<Mahjong> list) {

        // 先按照点数进行分组，麻将点数从1-9共有9种
        LinkedList<Mahjong>[] rankLists = new LinkedList[9];
        for (int i = 0; i < rankLists.length; i++) {
            rankLists[i] = new LinkedList<Mahjong>();
        }

        // 根据点数进行分组，即点数一样的放到一组中
        while (list.size() > 0) {
            Mahjong mahjong = list.remove();
            rankLists[mahjong.rank - 1].add(mahjong);
        }

        // 把所有的分组全部链接起来，即把所有的分组重新合并成一个集合，前面从list中取数据时，
        // 用的是remove，循环结束时，list就是空的
        for (int i = 0; i < rankLists.length; i++) {
            list.addAll(rankLists[i]);
        }

        System.out.println(list);

        // ----------------------------------------------//
        // 在把集合list按照花色进行分组与合并
        // 假设花色只有三种
        LinkedList<Mahjong>[] suitList = new LinkedList[3];
        for (int i = 0; i < suitList.length; i++) {
            suitList[i] = new LinkedList<Mahjong>();
        }

        // 把集合list中的数据按照花色进行分组
        while (list.size() > 0) {
            Mahjong mahjong = list.remove();
            suitList[mahjong.suit - 1].add(mahjong);
        }

        // 在把按照花色的分组进行合并
        for (int i = 0; i < suitList.length; i++) {
            list.addAll(suitList[i]);
        }

        System.out.println(list);

    }

    public static void testRadixSort() {
        LinkedList<Mahjong> list = new LinkedList<Mahjong>();
        list.add(new Mahjong(1, 5));
        list.add(new Mahjong(3, 2));
        list.add(new Mahjong(2, 6));
        list.add(new Mahjong(3, 8));
        list.add(new Mahjong(2, 2));
        list.add(new Mahjong(1, 3));
        list.add(new Mahjong(2, 1));
        list.add(new Mahjong(3, 7));
        list.add(new Mahjong(2, 4));
        list.add(new Mahjong(3, 4));
        radixSort(list);

        System.out.println(list);
    }

    /**
     * 7.插入排序
     *
     * @param array
     */
    public static void insertSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int insertIndex = i;
            int insertValue = array[insertIndex];// 表示想插入的数据
            // 如果插入的数据小于数组的前一个时
            while (insertIndex > 0 && insertValue < array[insertIndex - 1]) {
                // 如果待插入的值比前面一个数小，那么把前面一个值移动到后面
                array[insertIndex] = array[insertIndex - 1];
                insertIndex -= 1;
            }
            array[insertIndex] = insertValue;
        }
    }

    /**
     * 8.希尔排序，希尔排序是建立在插入排序的基础之上的，只是多了一个步长而已
     *
     * 插入排序相当于步长等于1
     *
     * @param array
     */
    public static void shellSort(int[] array, int step) {

        for (int i = 0; i < step; i++) {// 对步长的定位，选择每次操作的开始位置
            for (int j = i + step; j < array.length; j++) {// i表示从第2个数开始插入
                int s = j;
                int target = array[s];// 表示想插入的数据
                while (s > step - 1 && target < array[s - step]) {// 如果插入的数据小于数组的前一个时
                    array[s] = array[s - step];
                    s -= step;
                }
                array[s] = target;
            }
        }
    }

    static int[][] graph = new int[][] { { 0, 12, 100, 100, 100, 16, 14 },
            { 12, 0, 10, 100, 100, 7, 100 }, { 100, 10, 0, 3, 5, 6, 100 },
            { 100, 100, 3, 0, 4, 100, 100 }, { 100, 100, 5, 4, 0, 2, 8 },
            { 16, 7, 6, 100, 2, 0, 9 }, { 14, 100, 100, 100, 8, 9, 0 } };

    public static void floyd() {
        int length = graph.length;
        int[][] path = new int[length][length];

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                path[i][j] = j;
            }
        }

        for (int k = 0; k < length; k++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (graph[i][j] > (graph[i][k] + graph[k][j])) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        path[i][j] =k;
                    }
                }
            }
        }

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        // 绘图找路径
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path.length; j++) {

                System.out.print("V" + i + "->V" + j + " weight:" + graph[i][j]
                        + " path:" + i);
                int k = path[i][j];
                while (k != j) {
                    System.out.print("->" + k);
                    k = path[k][j];
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        int array[] = { 2, 4, 1, 5, 9, 3, 7, 8, 6 };
        // bubblingSort(array);
        // selectSort(array);
        // for (int i = 0; i < array.length; i++) {
        // System.out.print(array[i] + " ");
        // }

        // int a[] = { 31, 21, 59, 68, 12, 40 };
        // quickSort(a, 0, a.length - 1);
        // for (int i = 0; i < a.length; i++) {
        // System.out.println(a[i] + " ");
        // }

        // int[] array2 = { 1, 2, 5, 9, 3, 4, 10, 11 };
        // mergeSort(array2, 0, array2.length - 1);
        // for (int i = 0; i < array2.length; i++) {
        // System.out.print(array2[i] + " ");
        // }

        // testRadixSort();

        // insertSort(array);

        shellSort(array, 4);
        shellSort(array, 1);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
        floyd();
    }
}
