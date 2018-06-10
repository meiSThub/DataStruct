package com.mei.test.recall;

/**
 * 九宫格
 * 
 * @author mei
 *
 */
public class SpeedDial {

	private int n;

	/**
	 * 填写就宫格，使之每行、每列、斜对角的值和都相等
	 * 
	 * @param n
	 */
	public static void start(int n) {
		int[][] array = new int[n][n];
		int row = 0;
		int colomn = n / 2;// 从第一行的中间一列开始填
		array[row][colomn] = 1;
		for (int k = 0; k < n * n; k++) {
			if (k == 8) {
				break;
			}
			int tempRow = row;
			int tempColomn = colomn;
			// 找到当前节点的右上角空格的下标，
			row--;
			if (row < 0) {// 如果行超出了，就从取最后一行
				row = n - 1;
			}
			// 因为一直是取的右上角，所以行只可能从0超出，列只可能从最后一列超出
			colomn++;
			if (colomn >= n) {// 如果列超出了，就取第一列
				colomn = 0;
			}
			// 右上角的位置上没有填数字
			if (array[row][colomn] == 0) {
				array[row][colomn] = k + 2;
			} else {// 如果右上角已经填了值了,就填充正下方的空格
				// 回到前面的下标
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
