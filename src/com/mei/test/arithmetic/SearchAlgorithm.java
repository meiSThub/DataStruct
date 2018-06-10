package com.mei.test.arithmetic;

/**
 * 查找算法
 * 
 * @author mei
 *
 */
public class SearchAlgorithm {

	/**
	 * 顺序查找
	 * 
	 * @param array
	 * @param value
	 */
	public static int orderSearch(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 二分查找
	 * 
	 * @param array
	 *            待查找的数组，数组必须是有序的
	 * @param fromIndex
	 *            查找的起始位置
	 * @param toIndex
	 *            查找的结束位置
	 * @param value
	 *            要查找的值
	 * @return 返回查找到的数据下标
	 */
	public static int binarySearch(int[] array, int fromIndex, int toIndex, int value) {
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (value < array[mid]) {// 小于中间值，到前半部分查找
				high = mid - 1;
			} else if (value > array[mid]) {// 大于中间值，到后半部分查找
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return -(low + 1);
	}

	public static void main(String[] args) {
		int array[] = { 1, 3, 5, 8, 9, 11, 22, 36, 47, 58 };
		for (int i = 0; i < array.length; i++) {
			System.out.println(i + "->"
					+ binarySearch(array, 0, array.length, array[i]));
		}

	}
}
