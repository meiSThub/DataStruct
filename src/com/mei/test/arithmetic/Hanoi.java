package com.mei.test.arithmetic;

/**
 * �ݹ�˼�룺��ŵ��ʵ��
 */
public class Hanoi {

	/**
	 * ��ŵ��
	 * 
	 * @param count
	 *            ��������
	 * @param first
	 *            ��һ������
	 * @param second
	 *            �ڶ�������
	 * @param three
	 *            ����������
	 */
	public static void hanoi(int count, String first, String second,
			String three) {
		if (count < 1) {
			return;
		}
		hanoi(count - 1, first, three, second);
		System.out.println(first + "->" + three);
		hanoi(count - 1, second, first, three);
	}

	public static void main(String[] args) {
		hanoi(4, "A", "B", "C");
	}

}
