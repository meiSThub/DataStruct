package com.mei.test.arithmetic;

import java.util.LinkedList;

/**
 * �����㷨
 */
public class SortAlgorithm {

	/**
	 * ������֮ð������
	 * 
	 * ð������8�������ڵ��������
	 * 
	 * @param array
	 */
	public static void bubblingSort(int array[]) {
		for (int i = array.length - 1; i > 0; i--) {
			boolean flag = true;
			// �������鳤��Ϊ10�����һ��ѭ��8�Σ��Ϳ��԰��������ƶ�����󣬵ڶ���ѭ��7�ξͿ��԰ѵڶ�������ƶ��������ڶ�λ
			for (int j = 0; j < i; j++) {// ������һ��ѭ�������������Ƶ������
				if (array[j] > array[j + 1]) {// ������ȣ���ĺ���
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
	 * ������֮ѡ������
	 * 
	 * ��һ�Σ��̶���0�Ȼ�����±�1-array.length�У���һ��ֵ��С�ģ����0����н�����
	 * �ڶ��Σ��̶���1�Ȼ�����±�2-array.length�У���һ��ֵ��С�ģ����1����н�����
	 * �����Σ��̶���2�Ȼ�����±�3-array.length�У���һ��ֵ��С�ģ����2����н�����
	 * 
	 * ʡ��.................
	 * 
	 * ��������ѭ��������ʱ������������
	 * 
	 * @param array
	 */
	public static void selectSort(int[] array) {

		for (int i = 0; i < array.length; i++) {
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[index]) {// ����������һ����С�ģ�����¼�±�
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
	 * ���η�֮����������
	 * 
	 * ���ó��������������������Խṹ
	 * 
	 * �̴���1���д����ظ����ݵ�ʱ�����ܲ��� ��2��������ʽ�ṹ�������ܲ��ã�һ����˵����ʽ����ʹ�ã�
	 * 
	 * ������array��31, 21, 59, 68, 12, 40 ��������
	 */
	public static void quickSort(int[] array, int start, int end) {

		if (end - start <= 0) {// ����ָ���غ�ʱ���˳�
			return;
		}
		int low = start;
		int high = end;
		int stackValue = array[low];
		boolean direction = RIGHT_TO_LEFT;
		L1: while (low < high) {
			if (direction == RIGHT_TO_LEFT) {// ���ҵ������F
				for (int i = high; i > low; i--) {// i>left�ͺã�����i>=left;
					if (array[i] <= stackValue) {// �ҵ����ݴ�ֻС����������䵽��λ
						high = i;
						array[low++] = array[i];
						direction = !direction;// �����򣬿�ʼ�����ұ���
						continue L1;
					}
				}
				high = low;
			} else {// �����ұ���
				for (int i = low; i < high; i++) {
					if (array[i] >= stackValue) {// �ҵ����ݴ�ֵ�����������䵽��λ
						low = i;
						array[high--] = array[i];
						direction = !direction;// �����򣬿�ʼ���ҵ������
						continue L1;
					}
				}
				low = high;
			}
		}
		array[low] = stackValue;// ����

		/**
		 * 31, 21, 59, 68, 12, 40
		 * 
		 * ��������һ�ֲ���֮�����ݾͱ���ˣ� 12,21,31,68,59,40
		 * ��low=31֮ǰ�����ݶ���31С����low=31֮������ݶ���31�� ���žͶ�31ǰ������ݺͺ�������ݽ�������
		 */
		quickSort(array, start, low - 1);// ����ߵ����ݽ�������
		quickSort(array, low + 1, end);// ���ұߵ����ݽ�������
	}

	public static void merge(int[] array, int left, int mid, int right) {
		// mid��Ϊ�ұ����� ����������ʼ�±�
		int leftSize = mid - left;
		int rightSize = right - mid + 1;
		int leftArray[] = new int[leftSize];
		int rightArray[] = new int[rightSize];

		// ����������
		for (int i = left; i < mid; i++) {
			leftArray[i - left] = array[i];
		}

		// ����ұ�����
		for (int i = mid; i <= right; i++) {
			rightArray[i - mid] = array[i];
		}

		// �ϲ�����
		int leftIndex = 0;
		int rightIndex = 0;
		int currIndex = 0;
		// �鲢����
		while (leftIndex < leftSize && rightIndex < rightSize) {
			if (leftArray[leftIndex] < rightArray[rightIndex]) {
				array[left + currIndex++] = leftArray[leftIndex++];
			} else {
				array[left + currIndex++] = rightArray[rightIndex++];
			}
		}

		while (leftIndex < leftSize) {// ˵����ߵ����黹������û�з��뵽array��
			array[left + currIndex++] = leftArray[leftIndex++];
		}

		// ֻ���ܴ���һ��
		while (rightIndex < rightSize) {// ˵���ұߵ����黹������û�б����뵽array��
			array[left + currIndex++] = rightArray[rightIndex++];
		}

	}

	/**
	 * ���η�֮���鲢����
	 * 
	 * ���ó�������������������ʽ�ṹ ���൱�������ĺ�������
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
			mergeSort(array, left, mid);// ����߽�������
			mergeSort(array, mid + 1, right);// ���ұ߽�������
			merge(array, left, mid + 1, right);// ����������Ľ���ϲ�
		}
	}

	/**
	 * ��������
	 */
	public static void radixSort(LinkedList<Mahjong> list) {

		// �Ȱ��յ������з��飬�齫������1-9����9��
		LinkedList<Mahjong>[] rankLists = new LinkedList[9];
		for (int i = 0; i < rankLists.length; i++) {
			rankLists[i] = new LinkedList<Mahjong>();
		}

		// ���ݵ������з��飬������һ���ķŵ�һ����
		while (list.size() > 0) {
			Mahjong mahjong = list.remove();
			rankLists[mahjong.rank - 1].add(mahjong);
		}

		// �����еķ���ȫ�������������������еķ������ºϲ���һ�����ϣ�ǰ���list��ȡ����ʱ��
		// �õ���remove��ѭ������ʱ��list���ǿյ�
		for (int i = 0; i < rankLists.length; i++) {
			list.addAll(rankLists[i]);
		}

		System.out.println(list);

		// ----------------------------------------------//
		// �ڰѼ���list���ջ�ɫ���з�����ϲ�
		// ���軨ɫֻ������
		LinkedList<Mahjong>[] suitList = new LinkedList[3];
		for (int i = 0; i < suitList.length; i++) {
			suitList[i] = new LinkedList<Mahjong>();
		}

		// �Ѽ���list�е����ݰ��ջ�ɫ���з���
		while (list.size() > 0) {
			Mahjong mahjong = list.remove();
			suitList[mahjong.suit - 1].add(mahjong);
		}

		// �ڰѰ��ջ�ɫ�ķ�����кϲ�
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
	 * ��������
	 * 
	 * @param array
	 */
	public static void insertSort(int[] array) {

		for (int i = 1; i < array.length; i++) {
			int insertIndex = i;
			int insertValue = array[insertIndex];// ��ʾ����������
			// ������������С�������ǰһ��ʱ
			while (insertIndex > 0 && insertValue < array[insertIndex - 1]) {
				// ����������ֵ��ǰ��һ����С����ô��ǰ��һ��ֵ�ƶ�������
				array[insertIndex] = array[insertIndex - 1];
				insertIndex -= 1;
			}
			array[insertIndex] = insertValue;
		}
	}

	/**
	 * ϣ������ϣ�������ǽ����ڲ�������Ļ���֮�ϵģ�ֻ�Ƕ���һ����������
	 * 
	 * ���������൱�ڲ�������1
	 * 
	 * @param array
	 */
	public static void shellSort(int[] array, int step) {

		for (int i = 0; i < step; i++) {// �Բ����Ķ�λ��ѡ��ÿ�β����Ŀ�ʼλ��
			for (int j = i + step; j < array.length; j++) {// i��ʾ�ӵ�2������ʼ����
				int s = j;
				int target = array[s];// ��ʾ����������
				while (s > step - 1 && target < array[s - step]) {// ������������С�������ǰһ��ʱ
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
		// ��ͼ��·��
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
