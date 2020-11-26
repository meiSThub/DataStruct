package com.mei.test.datastruct.list;

/**
 * 手写LinkedList
 * @author mei
 *
 * @param <E>
 */
public class LinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public static class Node<E> {
        E item;
        Node<E> next;
        Node<E> pre;

        public Node(Node<E> next, E item, Node<E> pre) {
            super();
            this.next = next;
            this.item = item;
            this.pre = pre;
        }
    }

    public void add(E e) {
        linkLast(e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == size) {
            linkLast(e);
        } else {
            Node<E> target = node(index);// 如果index=0,target.pre==null的
            Node<E> node = new Node<E>(target, e, target.pre);
            if (target.pre == null) {
                first = node;
            } else {
                target.pre.next = node;
            }
            target.pre = node;
            size++;
        }
    }

    private void linkLast(E e) {
        Node<E> newNode = new Node<E>(null, e, last);
        Node<E> l = last;
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return node(index).item;
    }

    public void remove(int index) {
        Node<E> node = node(index);
        unlinkNode(node);
        size--;
    }

    private void unlinkNode(Node<E> p) {
        // p.pre.next=p.next;
        // p.next.pre=p.pre;
        if (p == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> pre = p.pre;
        Node<E> next = p.next;
        if (pre == null) {// 说明删除的是第一个节点
            first = p.next;
        } else {// 删除的不是第一个节点
            pre.next = p.next;
        }

        if (next == null) {// 说明删除的是最后一个节点
            last = p.pre;
        } else {// 删除的不是最后一个节点
            next.pre = p.pre;
        }
    }

    private Node<E> node(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        if (index < (size >> 1)) {// index处于前半部分
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {// index 处于后半部分
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0, 4);
        list.add(1);
        list.add(6);
        list.add(0, 8);
        list.remove(4);
        for (int i = 0; i < list.size; i++) {
            System.out.println(i + ":" + list.get(i));
        }
    }

}
