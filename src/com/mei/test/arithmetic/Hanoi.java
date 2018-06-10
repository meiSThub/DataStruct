package com.mei.test.arithmetic;

/**
 * 递归思想：汉诺塔实现
 */
public class Hanoi {

	/**
	 * 汉诺塔
	 * 
	 * @param count
	 *            盘子数量
	 * @param first
	 *            第一根柱子
	 * @param second
	 *            第二根柱子
	 * @param three
	 *            第三个柱子
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
