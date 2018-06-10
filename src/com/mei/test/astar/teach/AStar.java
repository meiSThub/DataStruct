package com.mei.test.astar.teach;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by 48608 on 2017/12/15.
 */

public class AStar {
	// �ϰ���
	public final static int BAR = 1;
	// ��·
	public final static int PATH = 2;
	// �����ϵ��ƶ�����
	public final static int DIRECT_VALUE = 10;
	// б�ƶ��Ĵ���
	public final static int OBLIQUE_VALUE = 14;
	// Ϊ�˲����Լ���ȥ����
	Queue<Node> openList = new PriorityQueue<Node>();
	List<Node> closeList = new ArrayList<Node>();

	// ����һЩ�����Եķ���

	/**
	 * ����Hֵ�������ٷ���
	 */
	private int calcH(Coord end, Coord coord) {
		return Math.abs(end.x - coord.x) + Math.abs(end.y - coord.y);
	}

	/**
	 * �жϽ���Ƿ������յ�
	 */
	private boolean isEndNode(Coord end, Coord coord) {
		return coord != null && end.equals(coord);
	}

	/**
	 * ��ʼ�㷨���߼�
	 */
	public void start(MapInfo mapInfo) {
		// ���жϵ�ͼ�Ƿ����
		if (mapInfo == null)
			return;
		// �����һ�β����Ľ��
		openList.clear();
		closeList.clear();
		// ��ʼ����
		// 1.�ѿ�ʼ�ڵ���뵽open��
		openList.add(mapInfo.start);
		// 2.Ŀ���ж�
		moveNodes(mapInfo);
	}

	/**
	 * �����ƶ���ǰ���
	 */
	private void moveNodes(MapInfo mapInfo) {
		while (!openList.isEmpty()) {
			if (isCoordInClose(mapInfo.end.coord)) {// ����յ������close��ʾ����
				// ��ʾ�Ѿ���ɣ����Ի������
				drawPath(mapInfo.map, mapInfo.end);
				// �����
				break;
			}
			// ��open�еĵ�һ���ڵ�ȡ�����ŵ�close��
			Node current = openList.poll();
			closeList.add(current);
			// 3.��ʼ��current��ʼ�ĵط����ڽ������ߵĽڵ�
			addNeighborNodeInOpen(mapInfo, current);
		}
	}

	private void addNeighborNodeInOpen(MapInfo mapInfo, Node current, int x,
			int y, int value) {
		if (canAddNodeToOpen(mapInfo, x, y)) {// �жϵ�ǰ�ڵ��Ƿ������ӣ�����close,Ҳ����ǽ��
			Node end = mapInfo.end;
			Coord coord = new Coord(x, y);
			int g = current.g + value;// ���㵱ǰ�㵽��ʼ��ľ���
			Node child = findNodeInOpen(coord);// ���ҵ�ǰ���Ƿ���open��
			if (child == null) {// ����open��
				// ���if�б�ʾ���Ǵ���û�ѹ���·
				int h = calcH(end.coord, coord);// ����Hֵ
				if (isEndNode(end.coord, coord)) {
					child = end;
					child.parent = current;
					child.g = g;
					child.h = h;
				} else {
					child = new Node(coord, current, g, h);
				}
				openList.add(child);
			} else if (child.g > g) {// �����open��(ֻ��Ҫ�ж������ڵ��Gֵ ,��С�Ļ���
				child.g = g;
				child.parent = current;
				openList.add(child);
			}
		}
	}

	/**
	 * �����Χ�����ܼ���ĵ�
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo, Node current) {
		int x = current.coord.x;
		int y = current.coord.y;
		// ���а˴β���
		// ����һ������������һ���ڵ���ж�
		// ��
		addNeighborNodeInOpen(mapInfo, current, x - 1, y, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, current, x, y - 1, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, current, x + 1, y, DIRECT_VALUE);
		// ��
		addNeighborNodeInOpen(mapInfo, current, x, y + 1, DIRECT_VALUE);
		// ����
		addNeighborNodeInOpen(mapInfo, current, x - 1, y - 1, OBLIQUE_VALUE);
		// ����
		addNeighborNodeInOpen(mapInfo, current, x + 1, y - 1, OBLIQUE_VALUE);
		// ����
		addNeighborNodeInOpen(mapInfo, current, x - 1, y + 1, OBLIQUE_VALUE);
		// ����
		addNeighborNodeInOpen(mapInfo, current, x + 1, y + 1, OBLIQUE_VALUE);

	}

	private Node findNodeInOpen(Coord coord) {
		if (coord == null || openList.isEmpty())
			return null;
		for (Node node : openList) {
			if (node.coord.equals(coord)) {
				return node;
			}
		}
		return null;
	}

	private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
		// �Ƿ��ڵ�ͼ��
		if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) {
			return false;
		}
		// �ж��Ƿ��ǲ���ͨ����λ��
		if (mapInfo.map[y][x] == BAR) {
			return false;
		}
		// �ж��Ƿ���close����
		if (isCoordInClose(x, y)) {
			return false;
		}
		return true;
	}

	/**
	 * �ж������Ƿ���close�ж���
	 */
	private boolean isCoordInClose(Coord coord) {
		return coord != null && isCoordInClose(coord.x, coord.y);
	}

	private boolean isCoordInClose(int x, int y) {
		if (closeList.isEmpty())
			return false;
		for (Node node : closeList) {
			if (node.coord.x == x && node.coord.y == y) {
				return true;
			}
		}
		return false;
	}

	private void drawPath(int[][] map, Node end) {
		if (end == null || map == null)
			return;
		System.out.println("��̳���Ϊ:" + end.g);
		while (end != null) {
			Coord c = end.coord;
			map[c.y][c.x] = PATH;
			end = end.parent;
		}
	}
}
