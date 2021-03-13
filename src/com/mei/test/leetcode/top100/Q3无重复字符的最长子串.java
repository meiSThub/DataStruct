package com.mei.test.leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mxb
 * @date 2021/3/12
 * @desc
 * @desired
 */
public class Q3无重复字符的最长子串 {

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 示例 4:
     *
     * 输入: s = ""
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     */

    /**
     * 思路：
     * 这道题主要用到思路是：滑动窗口
     *
     * 什么是滑动窗口？
     *
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     *
     * 如何移动？
     *
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     *
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     * 时间复杂度：O(n)O(n)
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int maxLength = 0;// 最大长度

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 左下标向后移动一位
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }

            maxLength = Math.max(maxLength, i - left + 1);
            // 把字符存入map
            map.put(s.charAt(i), i);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int length = lengthOfLongestSubstring(s);
        System.out.println("" + length);
    }
}
