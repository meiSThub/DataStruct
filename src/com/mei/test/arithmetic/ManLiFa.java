package com.mei.test.arithmetic;

/**
 * 蛮力法相关算法
 */
public class ManLiFa {

	/**
	 * 冒泡排序
	 */
	public void bubbleSort(int array[]) {
		for (int i = array.length - 1; i > 0; i--) {
			boolean flag = true;
			for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
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
	 * 选择排序
	 * 
	 * @param array
	 */
	public void selectSort(int[] array) {

		for (int i = 0; i < array.length; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[index]) {// 找到除0外，值最小的 角标
					index = j;
				}
			}

			if (index != i) {
				int temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int array[] = { 2, 5, 3, 6, 4, 9, 8, 7, 1 };
		ManLiFa fa = new ManLiFa();
//		fa.bubbleSort(array);
		fa.selectSort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
