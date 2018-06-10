package com.mei.test.datastruct.list;

/**
 * ��дLinkedList
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
			Node<E> target = node(index);// ���index=0,target.pre==null��
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
		if (pre == null) {// ˵��ɾ�����ǵ�һ���ڵ�
			first = p.next;
		} else {// ɾ���Ĳ��ǵ�һ���ڵ�
			pre.next = p.next;
		}

		if (next == null) {// ˵��ɾ���������һ���ڵ�
			last = p.pre;
		} else {// ɾ���Ĳ������һ���ڵ�
			next.pre = p.pre;
		}
	}

	private Node<E> node(int index) {
		if (index < 0 || index > size) {
			return null;
		}
		if (index < (size >> 1)) {// index����ǰ�벿��
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {// index ���ں�벿��
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
