package com.mei.test.arithmetic;

/**
 * �����㷨
 * 
 * @author mei
 *
 */
public class SearchAlgorithm {

	/**
	 * ˳�����
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
	 * ���ֲ���
	 * 
	 * @param array
	 *            �����ҵ����飬��������������
	 * @param fromIndex
	 *            ���ҵ���ʼλ��
	 * @param toIndex
	 *            ���ҵĽ���λ��
	 * @param value
	 *            Ҫ���ҵ�ֵ
	 * @return ���ز��ҵ��������±�
	 */
	public static int binarySearch(int[] array, int fromIndex, int toIndex, int value) {
		int low = fromIndex;
		int high = toIndex - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (value < array[mid]) {// С���м�ֵ����ǰ�벿�ֲ���
				high = mid - 1;
			} else if (value > array[mid]) {// �����м�ֵ������벿�ֲ���
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
