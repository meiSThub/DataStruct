package com.mei.test.recall;

/**
 * 数独问题
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
		// 如果填到了最后一行的最右边了，说明数独填完了，可以打印结果并退出了
		if (row == 8 || colomn == 9) {
			printResult();
			return;
		}

		// 如果横着放的过程中，如果到了数组的最右边，应该跳到下一行继续放
		if (colomn == 9) {
			row++;
			colomn = 0;
		}

		if (array[row][colomn] == 0) {// 该位置还没有填数
			for (int k = 1; k <= 9; k++) {
				if (judge(row, colomn, k)) {// 判断当前位置填的数字是否合法
					// 该值没有填过
					array[row][colomn] = k;
					sudoku(row, colomn + 1);// 合法就填下一列
					// 如果下一列返回了，说明在当前这一列填上这个数字k的时候，
					// 下一列找不到一个合适的数填了，则此时需要回溯，把当前这一列还原，
					// 从k的位置开始，从新填数
					array[row][colomn] = 0;// 让前一次的格子还原
				}
			}
		} else {// 如果当前位置填过数了，则填下一列
			sudoku(row, colomn + 1);
		}
	}

	/**
	 * 判断指定的下标将要填的数字是否合法
	 * 
	 * @param row
	 * @param colomn
	 * @return
	 */
	private static boolean judge(int row, int colomn, int value) {

		for (int i = 0; i < 9; i++) {
			// array[row][i] == value，这一行是否已经填了这个值
			// array[i][colomn]==value，这一列是否已经填了这个值
			if (array[row][i] == value || array[i][colomn] == value) {
				return false;
			}
		}

		int tempRow = row / 3;// 每个小九宫格的起点横坐标
		int tempCol = colomn / 3;// 每个小九宫格的起点纵坐标
		// 判断自己所在的宫里面有没有重复值
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
