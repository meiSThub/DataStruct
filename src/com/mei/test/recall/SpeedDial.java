package com.mei.test.recall;

/**
 * �Ź���
 * 
 * @author mei
 *
 */
public class SpeedDial {

	private int n;

	/**
	 * ��д�͹���ʹ֮ÿ�С�ÿ�С�б�Խǵ�ֵ�Ͷ����
	 * 
	 * @param n
	 */
	public static void start(int n) {
		int[][] array = new int[n][n];
		int row = 0;
		int colomn = n / 2;// �ӵ�һ�е��м�һ�п�ʼ��
		array[row][colomn] = 1;
		for (int k = 0; k < n * n; k++) {
			if (k == 8) {
				break;
			}
			int tempRow = row;
			int tempColomn = colomn;
			// �ҵ���ǰ�ڵ�����Ͻǿո���±꣬
			row--;
			if (row < 0) {// ����г����ˣ��ʹ�ȡ���һ��
				row = n - 1;
			}
			// ��Ϊһֱ��ȡ�����Ͻǣ�������ֻ���ܴ�0��������ֻ���ܴ����һ�г���
			colomn++;
			if (colomn >= n) {// ����г����ˣ���ȡ��һ��
				colomn = 0;
			}
			// ���Ͻǵ�λ����û��������
			if (array[row][colomn] == 0) {
				array[row][colomn] = k + 2;
			} else {// ������Ͻ��Ѿ�����ֵ��,��������·��Ŀո�
				// �ص�ǰ����±�
				tempRow++;
				row = tempRow;
				colomn = tempColomn;
				array[tempRow][tempColomn] = k + 2;
			}
		}

		printArray(array);
	}

	private static void printArray(int[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		start(3);
	}

}
