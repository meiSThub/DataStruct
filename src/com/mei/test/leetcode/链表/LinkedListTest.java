package com.mei.test.leetcode.链表;

/**
 * @author mxb
 * @date 2021/2/24
 * @desc 链表相关的算法题
 * @desired
 */
public class LinkedListTest {

    public class ListNode {

        int val;

        ListNode next;

        ListNode(int value) {
            val = value;
        }
    }

    /**
     * 1. 链表反转
     *
     * 题目链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     *
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseLinkedList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;

        while (curr != null) {
            // 1.保存当前节点的下一个节点
            ListNode next = curr.next;
            // 2.把当前节点，指向保存的前驱节点
            curr.next = pre;
            // 3.把当前节点，当前下一个节点的前驱节点
            pre = curr;
            // 遍历下一个节点
            curr = next;
        }

        return pre;
    }

    /**
     * 2. 链表反转2
     *
     * 题目链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/
     *
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     *
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (left == right) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        int index = 0;

        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode midHead = null;
        ListNode minTail = null;

        while (curr != null) {
            if (++index < left) {
                prev = curr;
                curr = curr.next;
                continue;
            } else if (index == left) {
                midHead = curr;
                leftNode = prev;
                prev = null;
            } else if (index == right) {
                rightNode = curr.next;
                minTail = curr;
            }
            if (index > right) {
                break;
            }
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (leftNode != null) {
            leftNode.next = minTail;
        }
        if (midHead != null) {
            midHead.next = rightNode;
        }
        if (leftNode == null) {
            return prev;
        }

        return head;
    }
}
