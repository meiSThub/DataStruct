package com.mei.test.arithmetic;

/**
 * �齫ʵ����
 */
public class Mahjong {

	public int suit;// ��ɫ��Ͳ�ӣ����ӣ�����

	public int rank;// ����

	public Mahjong(int suit, int rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "(" + suit + " " + rank + ")";
	}

}
