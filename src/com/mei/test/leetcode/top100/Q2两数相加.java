package com.mei.test.leetcode.top100;

/**
 * @author mxb
 * @date 2021/3/12
 * @desc
 * @desired
 */
public class Q2两数相加 {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     */

    public static class ListNode {

        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 执行耗时：3ms
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int preNum = 0;
        ListNode head = null;
        ListNode next = null;

        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + preNum;
            int val = sum % 10;
            preNum = sum / 10;

            ListNode node = new ListNode(val);
            if (next == null) {
                head = node;
            } else {
                next.next = node;
            }
            next = node;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }

        if (preNum != 0) {
            ListNode node = new ListNode(preNum);
            next.next = node;
        }
        return head;
    }

    /**
     * 执行耗时：2ms
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int preNum = 0;
        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (l1 != null || l2 != null) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + preNum;
            int val = sum % 10;
            preNum = sum / 10;

            curr.next = new ListNode(val);
            curr = curr.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;

        }

        if (preNum != 0) {
            ListNode node = new ListNode(preNum);
            curr.next = node;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode curr = l1;
        int[] num1 = {2, 4, 3};
        int[] num2 = {5, 6, 4};
        for (int i = 0; i < num1.length; i++) {
            curr.next = new ListNode(num1[i]);
            curr = curr.next;
        }
        l1 = l1.next;
        curr = l2;
        for (int i = 0; i < num2.length; i++) {
            curr.next = new ListNode(num2[i]);
            curr = curr.next;
        }
        l2 = l2.next;

        int num = 1;
        int sum = 0;
        // ListNode node = addTwoNumbers2(l1, l2);
        ListNode node = addTwoNumbers(l1, l2);
        while (node != null) {
            sum += node.val * (num);
            node = node.next;
            num = num * 10;
        }
        System.out.println(sum);
    }
}
