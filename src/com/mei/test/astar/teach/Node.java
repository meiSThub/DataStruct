package com.mei.test.astar.teach;

/**
 * Created by 48608 on 2017/12/15.
 */

public class Node implements Comparable<Node> {
	public Coord coord;
	public Node parent;
	public int g;
	public int h;

	public Node(int x, int y) {
		this.coord = new Coord(x, y);
	}

	public Node(Coord coord, Node parent, int g, int h) {
		this.coord = coord;
		this.parent = parent;
		this.g = g;
		this.h = h;
	}

	// ��Ҫһ�����������Ƚϵ�ǰ�ڵ����һ���ڵ㵽�յ�ľ��룬˭����
	@Override
	public int compareTo(Node o) {
		if (o == null)
			return -1;
		if (g + h > o.g + o.h) {
			return 1;
		} else if (g + h < o.g + o.h) {
			return -1;
		}

		return 0;
	}

}
