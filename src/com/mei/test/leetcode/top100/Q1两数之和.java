package com.mei.test.leetcode.top100;

/**
 * @author mxb
 * @date 2021/3/12
 * @desc
 * @desired
 */
public class Q1两数之和 {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 你可以按任意顺序返回答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     */


    public static int[] twoSum(int[] nums, int target) {

        int[] indexs = new int[2];

        int size = nums.length;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                if (target - nums[i] == nums[j]) {
                    indexs[0] = i;
                    indexs[1] = j;
                    return indexs;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println("[" + ints[0] + "," + ints[1] + "]");
    }
}
