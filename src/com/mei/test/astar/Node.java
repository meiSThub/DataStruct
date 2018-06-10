package com.mei.test.astar;

/**
 * 节点类，记录距离和路径
 * 
 * @author mei
 *
 */
public class Node implements Comparable<Node> {

	public Coord coord;
	public Node parent;// 父节点
	public int g;// g函数求的值
	public int h;// h函数求的值

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
