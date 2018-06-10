package com.mei.test.datastruct.list;

/**
 * ��д������:ʵ����Ϣ����
 * 
 * @author mei
 *
 */
public class MessageQueue<E> {

	private Node<E> first;
	private Node<E> last;
	private int size;// Ԫ�ظ���

	/**
	 * ��������
	 * 
	 * @param e
	 */
	public void add(E e) {
		linkLast(e);
	}

	/**
	 * ��ָ��λ�����ӽڵ�
	 * 
	 * @param index
	 * @param e
	 */
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == size) {
			linkLast(e);
		} else {
			Node<E> target = node(index);
			Node<E> pre = node(--index);
			Node<E> node = new Node<E>(e, target);
			if (pre == null) {// ˵������ͷ������Ԫ��,��index==0
				first = node;
			} else {
				pre.next = node;
			}
			size++;
		}
	}

	private void linkLast(E e) {// ������������һ��Ԫ��
		Node<E> node = new Node<E>(e, null);
		if (last == null) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
	}

	/**
	 * ��ѯָ��λ�õĽڵ�����
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return node(index).value;
	}

	/**
	 * ɾ��ָ��λ�õ�����
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		unlinkNode(index);
	}

	/**
	 * ������Ԫ������
	 */
	public void reverse() {
		if (size == 0) {
			return;
		}
		Node<E> target = null;
		Node<E> pre = null;
		for (int i = size - 1; i >= 0; i--) {
			target = node(i);
			pre = node(i - 1);
			if (pre != null) {
				target.next = pre;
			}
		}
		Node<E> temp = first;
		first = last;
		last = temp;
	}

	/**
	 * �����㷨��ʽ��
	 */
	public void reverse2() {
		Node<E> pre = null;
		Node<E> next = first;
		Node<E> temp = null;
		while (next != null) {
			temp = next.next;
			next.next = pre;
			pre = next;
			next = temp;
		}
		last = first;
		first = pre;

	}

	/**
	 * �Ӵ�������ϵ����ɾ���ڵ�
	 * 
	 * @param index
	 */
	private void unlinkNode(int index) {
		Node<E> target = node(index);
		Node<E> pre = node(--index);
		if (pre == null) {// ˵��ɾ�����ǵ�һ���ڵ�,��index==0
			first = target.next;
			target.next = null;
		} else {
			if (target.next == null) {// ˵��ɾ���������һ���ڵ�
				pre.next = null;
				last = pre;
			} else {
				pre.next = target.next;
			}
		}
		size--;
	}

	private Node<E> node(int index) {// �ҵ���Ӧλ�õĽڵ�
		if (index < 0 || index >= size) {
			return null;
		}
		Node<E> target = first;
		if (index == size - 1) {
			return last;
		}
		for (int i = 0; i < index; i++) {
			target = target.next;
		}
		return target;
	}

	public static class Node<E> {
		E value;
		Node<E> next;

		public Node(E value, Node<E> next) {
			super();
			this.value = value;
			this.next = next;
		}

	}

	public static void main(String[] args) {
		MessageQueue<Integer> queue = new MessageQueue<Integer>();
		queue.add(1);
		queue.add(1, 2);
		queue.add(0, 0);
		queue.add(3);
		queue.add(4);
		// queue.remove(4);

		System.out.println("����֮ǰ");
		for (int i = 0; i < queue.size; i++) {
			System.out.print("->" + queue.get(i));
		}

		queue.reverse2();
		System.out.println("\n����֮��");
		for (int i = 0; i < queue.size; i++) {
			System.out.print("->" + queue.get(i));
		}

		queue.add(1, 9);
		queue.remove(2);
		System.out.println("\n����֮���ڲ���һ��Ԫ��");
		for (int i = 0; i < queue.size; i++) {
			System.out.print("->" + queue.get(i));
		}
	}

}
