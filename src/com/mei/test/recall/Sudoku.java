package com.mei.test.recall;

/**
 * ��������
 * 
 * @author mei
 *
 */
public class Sudoku {

//	static int[][] array = new int[9][9];

	 public static int[][] array = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
	 { 0, 0, 3, 6, 0, 0, 0, 0, 0 }, { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
	 { 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
	 { 0, 0, 0, 1, 0, 0, 0, 3, 0 }, { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
	 { 0, 0, 8, 5, 0, 0, 0, 1, 0 }, { 0, 9, 0, 0, 0, 0, 4, 0, 0 } };

	public static void sudoku(int row, int colomn) {
		// ���������һ�е����ұ��ˣ�˵�����������ˣ����Դ�ӡ������˳���
		if (row == 8 || colomn == 9) {
			printResult();
			return;
		}

		// ������ŷŵĹ����У����������������ұߣ�Ӧ��������һ�м�����
		if (colomn == 9) {
			row++;
			colomn = 0;
		}

		if (array[row][colomn] == 0) {// ��λ�û�û������
			for (int k = 1; k <= 9; k++) {
				if (judge(row, colomn, k)) {// �жϵ�ǰλ����������Ƿ�Ϸ�
					// ��ֵû�����
					array[row][colomn] = k;
					sudoku(row, colomn + 1);// �Ϸ�������һ��
					// �����һ�з����ˣ�˵���ڵ�ǰ��һ�������������k��ʱ��
					// ��һ���Ҳ���һ�����ʵ������ˣ����ʱ��Ҫ���ݣ��ѵ�ǰ��һ�л�ԭ��
					// ��k��λ�ÿ�ʼ����������
					array[row][colomn] = 0;// ��ǰһ�εĸ��ӻ�ԭ
				}
			}
		} else {// �����ǰλ��������ˣ�������һ��
			sudoku(row, colomn + 1);
		}
	}

	/**
	 * �ж�ָ�����±꽫Ҫ��������Ƿ�Ϸ�
	 * 
	 * @param row
	 * @param colomn
	 * @return
	 */
	private static boolean judge(int row, int colomn, int value) {

		for (int i = 0; i < 9; i++) {
			// array[row][i] == value����һ���Ƿ��Ѿ��������ֵ
			// array[i][colomn]==value����һ���Ƿ��Ѿ��������ֵ
			if (array[row][i] == value || array[i][colomn] == value) {
				return false;
			}
		}

		int tempRow = row / 3;// ÿ��С�Ź������������
		int tempCol = colomn / 3;// ÿ��С�Ź�������������
		// �ж��Լ����ڵĹ�������û���ظ�ֵ
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (array[tempRow * 3 + i][tempCol * 3 + j] == value) {
					return false;
				}
			}
		}

		return true;
	}

	private static void printResult() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------");
	}

	public static void main(String[] args) {
		sudoku(0, 0);
	}

}
