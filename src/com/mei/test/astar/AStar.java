package com.mei.test.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A*�㷨��
 * 
 * ���·���㷨
 * 
 * @author mei
 *
 */
public class AStar {

	// �����ֵ����ʾ�ϰ���
	private final static int BAR = 1;
	// �����ֵ����ʾ�ߵ�·��
	private final static int PATH = 2;

	// ��������ʱ���ƶ�����,������
	private final static int DIRECT_VALUE = 10;
	// �Խ���б���ߵ��ƶ����ۣ�������
	private final static int OBLIQUE_VALUE = 14;

	// open�б����ڴ���ҵ��Ŀ��ߵĽڵ�
	// ���ȼ�����,�����Զ����򣨸���Node��compareTo�������бȽ�,��g+h��ֵ,�����룩
	private Queue<Node> openList = new PriorityQueue<Node>();
	// close�б����ڴ���Ѿ�̽�����Ľڵ��ǽ�ڵ�
	private List<Node> closeList = new ArrayList<Node>();

	public void start(MapInfo mapInfo) {
		if (mapInfo == null) {
			return;
		}
		// �����һ�β����Ľ��
		openList.clear();
		closeList.clear();
		// ��ʼ����
		// 1.�ѿ�ʼ�ڵ���뵽open��
		openList.add(mapInfo.start);// �������뵽open�б���
		// 2.Ŀ���ж�
		moveNode(mapInfo);
	}

	private void moveNode(MapInfo mapInfo) {
		while (!openList.isEmpty()) {
			// ����յ������close��ʾ����
			if (isCoordInClose(mapInfo.end.coord)) {
				drawPath(mapInfo);
				break;
			}

			// ȡ��open�б��еĵ�һ���ڵ㣬Ҳ���Ǿ����յ�����С�Ľڵ�
			Node currNode = openList.poll();
			// ������ڵ���뵽close�б��У���ʾ�ýڵ��Ѿ�������
			closeList.add(currNode);
			// �Ӹýڵ�ӷ���Ѱ����һ��������ߵĽڵ�
			addNeighborNodeInOpen(mapInfo, currNode);
		}
	}

	private void addNeighborNodeInOpen(MapInfo mapInfo, Node currNode) {
		int x = currNode.coord.x;// ��ǰ�ڵ��x����
		int y = currNode.coord.y;// ��ǰ�ڵ��y����

		// ������ǰ�ڵ��8������
		// ע�⣺�������ǰ�x��y��������Ļ���꣬�����������±꣬�����±�Ļ����ã�y��x��
		// ��ʾ������������
		// ��
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, currNode, x, y - 1, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, currNode, x, y + 1, DIRECT_VALUE);
		// ���Ͻ�
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y - 1, OBLIQUE_VALUE);
		// ���Ͻ�
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y - 1, OBLIQUE_VALUE);
		// ���½�
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y + 1, OBLIQUE_VALUE);
		// ���½�
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y + 1, OBLIQUE_VALUE);

	}

	/**
	 * �ѷ���Ҫ��Ľڵ㣬��ӵ�open�б���
	 * 
	 * @param mapInfo
	 * @param currNode
	 * @param x
	 * @param y
	 * @param distance
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo, Node currNode, int x,
			int y, int distance) {
		// �жϽڵ��Ƿ������ӵ�open�б��У����ڵ㲻��close�б����Ҳ���ǽ
		if (canAddNodeToOpen(mapInfo, x, y)) {
			Node end = mapInfo.end;
			Coord coord = new Coord(x, y);
			int g = currNode.g + distance;// ���㵱ǰ�㵽��ʼ��ľ���
			Node child = findNodeInOpen(coord);// ���ҵ�ǰ���Ƿ���open��
			// ˵����ǰ�ڵ�û�б����뵽open�б��У��ҵ���һ���µĽڵ�
			if (child == null) {// �½ڵ㲻��open�б���
				int h = calH(coord, end.coord);
				if (isEndNode(end.coord, coord)) {// ��ǰ�ڵ��Ƿ�Ϊ�յ�
					child = end;
					child.g = g;
					child.h = h;
					child.parent = currNode;
				} else {// ��ǰ�ڵ㲻���յ�,ֱ��new����
					child = new Node(coord, currNode, g, h);
				}
				openList.add(child);
			} else {
				if (child.g > g) {
					child.g = g;// �޸�gֵ
					child.parent = currNode;
					openList.add(child);// ��������
				}
			}
		}
	}

	/**
	 * �����ҵ����½ڵ㵽�յ��Ԥ�����룺h,���������ٷ���
	 * 
	 * @param coord
	 * @param coord2
	 * @return
	 */
	private int calH(Coord start, Coord end) {
		return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
	}

	/**
	 * ��open�б��в��ҵ�ǰ�ڵ�
	 * 
	 * @param coord
	 * @return
	 */
	private Node findNodeInOpen(Coord coord) {
		if (coord == null || openList.isEmpty()) {
			return null;
		}
		for (Node node : openList) {
			if (coord.equals(node.coord)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * �ڵ��Ƿ���Լ��뵽open�б���
	 * 
	 * ��������1����ǰ�ڵ㲻��close�б��� ��2����ǰ�ڵ��ڵ�ͼ���Ҳ���ǽ
	 * 
	 * @param mapInfo
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
		if (mapInfo == null) {
			return false;
		}
		// �Ƿ��ڵ�ͼ��
		if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) {
			return false;
		}

		// ע�⣺�������ǰ�x��y��������Ļ���꣬�����������±꣬�����±�Ļ����ã�y��x��
		// ��ʾ������������
		// ��ǰ�ڵ���ǽ
		if (mapInfo.map[y][x] == BAR) {
			return false;
		}

		if (isCoordInClose(x, y)) {// �ڵ���close�б���
			return false;
		}

		return true;
	}

	private boolean isCoordInClose(Coord coord) {
		return coord != null && isCoordInClose(coord.x, coord.y);
	}

	/**
	 * �ڵ��Ƿ���close�б���
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isCoordInClose(int x, int y) {
		for (Node node : closeList) {
			if (node.coord.x == x && node.coord.y == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * �������·��
	 * 
	 * @param mapInfo
	 */
	private void drawPath(MapInfo mapInfo) {
		if (mapInfo == null) {
			return;
		}

		Node node = mapInfo.end;
		System.out.println("end:" + node.g);
		while (node != null) {
			mapInfo.map[node.coord.y][node.coord.x] = PATH;
			node = node.parent;
		}
	}

	/**
	 * �жϽ���Ƿ������յ�
	 */
	private boolean isEndNode(Coord end, Coord coord) {
		return coord != null && end.equals(coord);
	}
}
