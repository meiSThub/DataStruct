package com.mei.test.recall;

/**
 * 八皇后问题
 *
 * 在8*8的棋盘中，填入8个皇后，使之彼此之间不再同一行，同一列，同一对角线上
 *
 * @author mei
 *
 */
public class EightQueens {

    // 用于存放每个皇后存放的位置，如 queensLocations[0]=4，表示在第一行第5列有一个皇后
    static int array[] = new int[8];

    /**
     * 开始算法
     */
    public static void eightQuees(int row) {
        //如果有结果直接退出
        if (row == 8) {// 超出
            printResult();
            return;
        }
        //开始从第一列到最后一列一个个放入
        for (int colomn = 0; colomn < 8; colomn++) {
            array[row] = colomn;
            if (judge(row)) {// 如果填入的元素与前面填入的不在同一列或者同一对角线上
                eightQuees(row + 1);// 填下一行
            }
        }
    }

    private static boolean judge(int row) {
        // 循环判断与前面的[0,row)行是否有冲突
        for (int i = 0; i < row; i++) {
            // queensLocations[row] == queensLocations[i]
            // 当前行放入的皇后与其他的行的皇后是否在同一列上

            // 二维数组的规律，如果第n行与第i行的元素的行号相减的绝对值与其列号相减的绝对值相等，
            // 则对应的元素在同一对角线上
            // Math.abs(row - i) == Math.abs(array[row] - array[i])
            if (array[row] == array[i]// 同一列
                    || Math.abs(row - i) == Math.abs(array[row] - array[i])) {// 同一行
                return false;
            }
        }
        return true;
    }

    static int index = 1;

    public static void printResult() {
        System.out.println("种类：" + index++);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        eightQuees(0);// 从第0行开始填入
    }

}
