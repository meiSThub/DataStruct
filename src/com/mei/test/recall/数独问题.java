package com.mei.test.recall;

/**
 * Created by 48608 on 2017/12/20.
 */

public class 数独问题 {

    public static void main(String[] args) {
        sudoku();
    }

    public static int[][] result = new int[9][9];

    // public static int[][] result={
    // {8, 0, 0, 0, 0, 0, 0, 0, 0},
    // {0, 0, 3, 6, 0, 0, 0, 0, 0},
    // {0, 7, 0, 0, 9, 0, 2, 0, 0},
    // {0, 5, 0, 0, 0, 7, 0, 0, 0},
    // {0, 0, 0, 0, 4, 5, 7, 0, 0},
    // {0, 0, 0, 1, 0, 0, 0, 3, 0},
    // {0, 0, 1, 0, 0, 0, 0, 6, 8},
    // {0, 0, 8, 5, 0, 0, 0, 1, 0},
    // {0, 9, 0, 0, 0, 0, 4, 0, 0}
    // };
    public static void sudoku() {
        sudoku(0, 0);
    }

    public static void sudoku(int i, int j) {
        // 退出条件
        if (i == 8 && j == 9) {
            printResult();
            return;
        }

        // 横着放入的过程中，如果到了数组的最右边，应该跳到下一行
        if (j == 9) {
            i++;
            j = 0;
        }
        if (result[i][j] == 0) {// 如果是空格子
            for (int k = 1; k <= 9; k++) {
                if (judge(i, j, k)) {
                    result[i][j] = k;
                    sudoku(i, j + 1);
                    // 让前一次的格子还原
                    result[i][j] = 0;
                }
            }
        } else {
            sudoku(i, j + 1);
        }
    }

    // 用来验证当前位置能填入的数字
    public static boolean judge(int row, int col, int number) {
        // 判断行和列的不重复
        for (int i = 0; i < 9; i++) {
            if (result[row][i] == number || result[i][col] == number) {// 如果有重复就返回false
                return false;
            }
        }
        // 判断自己所在的宫里面有没有重复值
        int tempRow = row / 3;
        int tempCol = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (result[tempRow * 3 + i][tempCol * 3 + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printResult() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
