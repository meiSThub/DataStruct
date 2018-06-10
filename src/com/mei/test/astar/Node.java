package com.mei.test.astar;

/**
 * �ڵ��࣬��¼�����·��
 * 
 * @author mei
 *
 */
public class Node implements Comparable<Node> {

	public Coord coord;
	public Node parent;// ���ڵ�
	public int g;// g�������ֵ
	public int h;// h�������ֵ

	public Node(int x, int y) {
		coord = new Coord(x, y);
	}

	public Node(Coord coord, Node parent, int g, int h) {
		this.coord = coord;
		this.parent = parent;
		this.g = g;
		this.h = h;
	}

	@Override
	public int compareTo(Node node) {
		if (node == null) {
			return -1;
		}
		if (g + h > node.g + node.h) {
			return 1;
		} else if (g + h < node.g + node.h) {
			return -1;
		}
		return 0;
	}

}
