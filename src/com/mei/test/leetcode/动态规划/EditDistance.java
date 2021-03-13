package com.mei.test.leetcode.动态规划;

/**
 * @author mxb
 * @date 2021/3/3
 * @desc 编辑距离
 * @desired
 */
public class EditDistance {

    /**
     * 求 把字符串s1变成s2所需要操作的最短距离
     * 操作类型有：
     * 插入
     * 删除
     * 修改
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 返回最小的操作数
     */
    private static int minDistance(String s1, String s2, int m, int n) {
        // 1.确定
        if (m == -1) {
            return n + 1;
        }
        if (n == -1) {
            return m + 1;
        }
        if (s1.charAt(m) == s2.charAt(n)) {
            return minDistance(s1, s2, m - 1, n - 1);
        } else {
            int minDis = min(minDistance(s1, s2, m, n - 1),// 插入
                    minDistance(s1, s2, m - 1, n),// 删除
                    minDistance(s1, s2, m - 1, n - 1));// 替换
            return minDis + 1;
        }
    }

    /**
     * 动态规划，优化算法
     *
     * 备忘录方式，优化
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @param m  字符1下标
     * @param n  字符2下标
     * @return 最小编辑距离
     */
    private static int minDistance2(String s1, String s2, int m, int n, int[][] temp) {
        if (m == -1) {
            return n + 1;
        }
        if (n == -1) {
            return m + 1;
        }
        if (s1.charAt(m) == s2.charAt(n)) {
            return minDistance(s1, s2, m - 1, n - 1);
        } else {
            int minDis = min(minDistance(s1, s2, m, n - 1),// 插入
                    minDistance(s1, s2, m - 1, n),// 删除
                    minDistance(s1, s2, m - 1, n - 1));// 替换
            return minDis + 1;
        }
    }

    /**
     * 动态规划，优化算法
     *
     * Dp数组方式，优化
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 最小编辑距离
     */
    private static int minDistance3(String s1, String s2) {
        int m = s1.length() + 1;
        int n = s2.length() + 1;

        int[][] dp = new int[m][n];

        // 如果字符串s1为空，则把s1转成s2，则需要把s2的字符全部填充给s1
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // 如果字符串s2为空，则把s1转成s2，需要执行的操作就是s1字符串的长度，
        for (int i = 0; i < n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        String s1 = "dinitrophenylhydrazine";
        String s2 = "benzalphenylhydrazone";
        // int min = minDistance(s1, s2, s1.length() - 1, s2.length() - 1);
        int min = minDistance3(s1, s2);
        System.out.println("min=" + min);

        int[][] dp = new int[3][5];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = i + j;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println("");
        }
    }

    private static int min(int num1, int num2, int num3) {
        return Math.min(num1, Math.min(num2, num3));
    }
}
