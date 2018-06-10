package com.mei.test.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A*算法类
 * 
 * 最短路径算法
 * 
 * @author mei
 *
 */
public class AStar {

	// 矩阵的值：表示障碍物
	private final static int BAR = 1;
	// 矩阵的值：表示走的路径
	private final static int PATH = 2;

	// 横竖行走时的移动代价,即距离
	private final static int DIRECT_VALUE = 10;
	// 对角线斜着走的移动代价，即距离
	private final static int OBLIQUE_VALUE = 14;

	// open列表，用于存放找到的可走的节点
	// 优先级队列,用于自动排序（根据Node的compareTo方法进行比较,即g+h的值,即距离）
	private Queue<Node> openList = new PriorityQueue<Node>();
	// close列表，用于存放已经探索过的节点和墙节点
	private List<Node> closeList = new ArrayList<Node>();

	public void start(MapInfo mapInfo) {
		if (mapInfo == null) {
			return;
		}
		// 清空上一次操作的结果
		openList.clear();
		closeList.clear();
		// 开始搜索
		// 1.把开始节点加入到open表
		openList.add(mapInfo.start);// 把起点加入到open列表中
		// 2.目标判定
		moveNode(mapInfo);
	}

	private void moveNode(MapInfo mapInfo) {
		while (!openList.isEmpty()) {
			// 如果终点进入了close表示结束
			if (isCoordInClose(mapInfo.end.coord)) {
				drawPath(mapInfo);
				break;
			}

			// 取出open列表中的第一个节点，也就是距离终点距离较小的节点
			Node currNode = openList.poll();
			// 把这个节点放入到close列表中，表示该节点已经被访问
			closeList.add(currNode);
			// 从该节点从发，寻找下一个距离可走的节点
			addNeighborNodeInOpen(mapInfo, currNode);
		}
	}

	private void addNeighborNodeInOpen(MapInfo mapInfo, Node currNode) {
		int x = currNode.coord.x;// 当前节点的x坐标
		int y = currNode.coord.y;// 当前节点的y坐标

		// 搜索当前节点的8个方向
		// 注意：这里我们把x，y当做是屏幕坐标，而不是数字下标，数组下标的话就用（y，x）
		// 表示，即反过来。
		// 左
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y, DIRECT_VALUE);
		// 上
		addNeighborNodeInOpen(mapInfo, currNode, x, y - 1, DIRECT_VALUE);
		// 右
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y, DIRECT_VALUE);
		// 下
		addNeighborNodeInOpen(mapInfo, currNode, x, y + 1, DIRECT_VALUE);
		// 左上角
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y - 1, OBLIQUE_VALUE);
		// 右上角
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y - 1, OBLIQUE_VALUE);
		// 左下角
		addNeighborNodeInOpen(mapInfo, currNode, x - 1, y + 1, OBLIQUE_VALUE);
		// 右下角
		addNeighborNodeInOpen(mapInfo, currNode, x + 1, y + 1, OBLIQUE_VALUE);

	}

	/**
	 * 把符合要求的节点，添加到open列表当中
	 * 
	 * @param mapInfo
	 * @param currNode
	 * @param x
	 * @param y
	 * @param distance
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo, Node currNode, int x,
			int y, int distance) {
		// 判断节点是否可以添加到open列表当中，即节点不在close列表中且不是墙
		if (canAddNodeToOpen(mapInfo, x, y)) {
			Node end = mapInfo.end;
			Coord coord = new Coord(x, y);
			int g = currNode.g + distance;// 计算当前点到开始点的距离
			Node child = findNodeInOpen(coord);// 查找当前点是否在open中
			// 说明当前节点没有被加入到open列表中，找到的一个新的节点
			if (child == null) {// 新节点不在open列表中
				int h = calH(coord, end.coord);
				if (isEndNode(end.coord, coord)) {// 当前节点是否为终点
					child = end;
					child.g = g;
					child.h = h;
					child.parent = currNode;
				} else {// 当前节点不是终点,直接new出来
					child = new Node(coord, currNode, g, h);
				}
				openList.add(child);
			} else {
				if (child.g > g) {
					child.g = g;// 修改g值
					child.parent = currNode;
					openList.add(child);// 重新排序
				}
			}
		}
	}

	/**
	 * 计算找到的新节点到终点的预估距离：h,采用曼哈顿方法
	 * 
	 * @param coord
	 * @param coord2
	 * @return
	 */
	private int calH(Coord start, Coord end) {
		return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
	}

	/**
	 * 在open列表当中查找当前节点
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
	 * 节点是否可以加入到open列表中
	 * 
	 * 条件：（1）当前节点不在close列表中 （2）当前节点在地图中且不是墙
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
		// 是否在地图中
		if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) {
			return false;
		}

		// 注意：这里我们把x，y当做是屏幕坐标，而不是数字下标，数组下标的话就用（y，x）
		// 表示，即反过来。
		// 当前节点是墙
		if (mapInfo.map[y][x] == BAR) {
			return false;
		}

		if (isCoordInClose(x, y)) {// 节点在close列表中
			return false;
		}

		return true;
	}

	private boolean isCoordInClose(Coord coord) {
		return coord != null && isCoordInClose(coord.x, coord.y);
	}

	/**
	 * 节点是否在close列表当中
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
	 * 回溯填充路径
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
	 * 判断结点是否是最终点
	 */
	private boolean isEndNode(Coord end, Coord coord) {
		return coord != null && end.equals(coord);
	}
}
