package com.mei.test.leetcode.动态规划;

/**
 * @author mxb
 * @date 2021/3/4
 * @desc
 * @desired
 */
public class PalindromeSubseq {
    /**
     * 题目：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 示例 1:
     * 输入:"bbbab"
     * 输出:4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:"cabebaf"
     * 输出:4
     * 一个可能的最长回文子序列为 "abba"。
     *
     * 提示：
     * 1 <= s.length <= 1000
     * s 只包含小写英文字母
     *
     */

    // 注意：
    /**
     * 所谓回文字符串，就是一个字符串，从左到右读和从右到左读是完全一样的，比如“aba”、“c”，对于一个字符串，
     * 可以通过删除某些字符而变成回文字符串，如“cabebaf”，删除'c'、'e'、‘f’后剩下子串“abba”就是回文字符串。
     *
     * 要求，给定任意一个字符串，字符串最大长度1000，计算出最长的回文字符串长度。
     *
     * 如“cabebaf”的回文串包括“c”、“aba”、“abba”等，最长回文“abba”长度为4。
     */

    /**
     * 求给定字符串的最长回文子序列 的长度
     *
     * @param s 字符串
     * @return 最长回文子序列长度
     */
    public static int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];

        // base case
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println("");
        }

        return dp[0][length - 1];
    }

    public static int longestPalindromeSubseq1(String s) {
        int length = s.length();

        int maxLength = 0;

        return maxLength;
    }


    public static void main(String[] args) {
        String s = "cabebaf";
        // String s = "bbbab";
        System.out.println("" + longestPalindromeSubseq(s));
    }
}
