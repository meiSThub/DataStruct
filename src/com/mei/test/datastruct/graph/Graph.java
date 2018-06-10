package com.mei.test.datastruct.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 */
public class Graph {

	// 用于表示无穷大，即两点之间不可达，没有路径
	private static final int MAX_WEIGHT = Integer.MAX_VALUE;

	private int verticeSize;// 顶点数
	private int[] vertices;// 顶点
	private int[][] matrix;// 邻接矩阵，用于存放图的边
	private boolean[] visited;// 用于存放顶点的访问情况

	public Graph(int size) {
		verticeSize = size;
		vertices = new int[size];
		matrix = new int[size][size];
		visited = new boolean[size];

		// 初始化顶点
		for (int i = 0; i < size; i++) {
			vertices[i] = i;
			visited[i] = false;
		}
	}

	public void setGrapth(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * 获取图的顶点的数量
	 * 
	 * @return
	 */
	public int getVerticesSize() {
		return verticeSize;
	}

	/**
	 * 获取顶点数组
	 * 
	 * @return
	 */
	public int[] getVertices() {
		return vertices;
	}

	/**
	 * 获取顶点v1到v2的权重
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getWeight(int v1, int v2) {
		int weight = matrix[v1][v2];
		return weight == MAX_WEIGHT ? 0 : weight;
	}

	/**
	 * 获取顶点v的入度
	 * 
	 * @param v
	 * @return
	 */
	public int getInDegree(int v) {
		int degree = 0;
		for (int i = 0; i < verticeSize; i++) {
			if (matrix[i][v] > 0 && matrix[i][v] != MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取顶点v的出度
	 * 
	 * @param v
	 * @return
	 */
	public int getOutDegree(int v) {
		int degree = 0;
		for (int i = 0; i < verticeSize; i++) {
			if (matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT) {
				degree++;
			}
		}
		return degree;
	}

	/**
	 * 获取顶点v的第一个邻接点
	 * 
	 * @param v
	 * @return
	 */
	public int getFirstNeighbor(int v) {
		for (int i = 0; i < verticeSize; i++) {
			if (matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 查找节点v 的邻接点 index的下一个邻接点
	 * 
	 * @param v
	 * @param neighbor
	 * @return
	 */
	public int getNextNeighbor(int v, int neighbor) {
		for (int i = neighbor + 1; i < verticeSize; i++) {
			if (matrix[v][i] > 0 && matrix[v][i] != MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 图深度优先搜索
	 */
	public void dfs() {
		for (int i = 0; i < verticeSize; i++) {
			visited[i] = false;
		}
		System.out.println("size=" + verticeSize);

		for (int i = 0; i < verticeSize; i++) {
			if (!visited[i]) {// 如果顶点没有被访问，则访问他
				visited[i] = true;
				System.out.println(i);// 访问该顶点
				dfs(i);
			}
		}
	}

	// 类似二叉树的前序遍历
	private void dfs(int i) {
		if (!visited[i]) {
			visited[i] = true;
			System.out.println(i);
		}
		int v = getFirstNeighbor(i);// 访问顶点i的第一个邻接点
		while (v != -1) {
			if (!visited[v]) {// 如果顶点v没有被访问，则访问他，
				visited[v] = true;
				System.out.println(v);
				v = getFirstNeighbor(v);// 接着访问顶点v的第一个邻接点
				dfs(v);
			}
			// 当深度遍历返回到顶点i时，继续遍历顶点v的下一个邻接点，上面只是访问了第一个邻接点
			v = getNextNeighbor(i, v);
		}
	}

	/**
	 * 图广度优先搜素
	 * 
	 * 默认从顶点v0开始搜素
	 */
	public void bfs() {
		for (int i = 0; i < verticeSize; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < verticeSize; i++) {
			if (!visited[i]) {// 顶点i是否被访问
				visited[i] = true;
				System.out.println("访问节点：v" + i);
				bfs(i);
			}
		}
	}

	private Queue<Integer> queue = new LinkedList<Integer>();

	private void bfs(int i) {
		int v = getFirstNeighbor(i);
		while (v != -1) {
			if (!visited[v]) {
				visited[v] = true;
				System.out.println("访问节点：v" + v);
				queue.offer(v);
			}
			v = getNextNeighbor(i, v);
		}

		while (!queue.isEmpty()) {
			int curr = queue.poll();
			bfs(curr);
		}
	}

	/**
	 * 最小生成树算法之prim算法
	 * 
	 * 把顶点分成两个部分，一部分是已经查找了的，另一部分是还没有查找的
	 * 
	 * 设已查找的顶点集合为u
	 */
	public int prim() {
		/*
		 * 用一个集合lowcost，表示已查找顶点和未查找顶点，其中 lowcost[i] == 0 时，表示节点i 已经被查找
		 * 如果lowcost[i] ！= 0 ，那么必然有一个值， 这个值可以用来表示查找到的集合 到 下标为i 的这个节点的 最小距离
		 * 
		 * 假设从节点v0开始查找，初始化的时候，lowcost[i]就表示了顶点v0到其他节点的距离
		 */
		int[] lowcost = new int[verticeSize];
		for (int i = 0; i < verticeSize; i++) {
			lowcost[i] = matrix[0][i];// 默认从顶点v0开始，剩余顶点到v0的距离
		}

		int min = 0;// 剩余顶点中到已查找的顶点距离最小的 距离，即权重
		int minIndex = 0;// 到已查找顶点距离最小的顶点 数组索引
		int weightCount = 0;// 最小生成树的总权重
		for (int i = 0; i < verticeSize; i++) {
			min = MAX_WEIGHT;// 开始把最小距离设为无穷大
			minIndex = 0;
			// step1: 查找剩下的节点到已经被查找节点集合U中距离最短的节点
			for (int j = 0; j < verticeSize; j++) {
				if (lowcost[j] > 0 && lowcost[j] < min) {// 找到距离最小的顶点
					min = lowcost[j];
					minIndex = j;
				}
			}

			// 说明找不到距离集合u距离最小的顶点了，即所有顶点都查找完了
			if (min == MAX_WEIGHT) {
				break;
			}
			weightCount += min;
			// step2：找到这个节点后，我要更新剩下来的节点到已经被查找节点集合U 的距离
			lowcost[minIndex] = 0;
			// 找到距离集合u距离最小的顶点后，更新集合u和到到剩余顶点的距离
			for (int j = 0; j < verticeSize; j++) {
				// lowcost[j]表示查找到节点剩余节点j的距离，matrix[minIndex][j]表示新查找到的节点minIndex
				// 到节点j的距离，如果matrix[minIndex][j]小，就更新lowcost
				if (lowcost[j] != 0 && lowcost[j] > matrix[minIndex][j]) {
					lowcost[j] = matrix[minIndex][j];
				}
			}
		}

		return weightCount;
	}

	// //////////////////prim算法结束////////////////////

	// //////////////////kruskal算法start////////////////
	private int queryEdgeSize = 0;// 查找到的最小边的数量

	/**
	 * kruskal最小生成树算法
	 */
	public Edge[] kruskal() {
		// 获取所有的有效边
		Edge[] edges = getEdges();
		// 用于保存已查找到的顶点所能连通到的最大顶点，下标 表示起始顶点，值表示起点能连通的终点
		// 遵循规则：下标应该比值小
		// 如edge_temp[1]=5，表示顶点1到顶点5有边，即有路径可走，即1到5是连通的，
		int[] edge_temp = new int[verticeSize];
		sortEdge(edges);// 从小到达排序
		// 用于保存当前找到的最小边
		Edge[] queryEdges = new Edge[edgeSize];

		int weight = 0;

		int index = 0;
		for (int i = 0; i < edgeSize; i++) {
			int p1 = edges[i].startP;
			int p2 = edges[i].endP;

			int m = getEnd(edge_temp, p1);// 在已查找节点集合中，找到与节点p1连通的最大节点
			int n = getEnd(edge_temp, p2);
			if (m != n) {// 如果p1，p2连通的最大节点不相同，说明p1，p2是不连通的，那么这条边就是有效边
				if (m > n) {// 把小的顶点作为下标，大的顶点作为值
					int temp = m;
					m = n;
					n = temp;
				}
				// 更新已查找节点能连通到的最大节点，即节点m能连通到n
				edge_temp[m] = n;
				queryEdges[index++] = edges[i];
				weight += edges[i].weight;// 最小生成树的权重
			}
		}

		queryEdgeSize = index;// 保存查找到的最小边的数量
		System.out.println("weight=" + weight);

		return queryEdges;// 返回找到的最小边的集合
	}

	/**
	 * 在集合edge_temp中，查找节点p能连通到的最大节点
	 * 
	 * @param edge_temp
	 * @param p
	 * @return
	 */
	private int getEnd(int[] edge_temp, int p) {
		int i = p;// 如果在已查找的连通结合中，找不到与p连通的最大节点，则直接返回节点p
		while (edge_temp[i] != 0) {
			i = edge_temp[i];
		}
		return i;
	}

	/**
	 * 对边进行排序
	 * 
	 * @param edges
	 */
	private void sortEdge(Edge[] edges) {
		if (edges == null) {
			return;
		}

		for (int i = edgeSize - 1; i > 0; i--) {
			boolean flag = true;
			for (int j = 0; j < i; j++) {
				if (edges[j].compareTo(edges[j + 1]) > 0) {
					Edge temp = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = temp;
					flag = false;
				}
			}
			if (flag) {
				break;
			}
		}
	}

	private int edgeSize = 0;// 边的数量

	/**
	 * 获取所有的边的集合
	 * 
	 * @return
	 */
	public Edge[] getEdges() {
		Edge[] edges = new Edge[verticeSize * verticeSize];
		int index = 0;
		edgeSize = 0;
		for (int i = 0; i < verticeSize; i++) {
			for (int j = 0; j < verticeSize; j++) {
				if (matrix[i][j] > 0 && matrix[i][j] != MAX_WEIGHT) {// 取出一条有效边
					edges[index++] = new Edge(i, j, matrix[i][j]);// 顶点i到j的一条边
				}
			}
		}
		edgeSize = index;
		return edges;
	}

	/**
	 * 用于表示一条边
	 */
	public static class Edge implements Comparable<Edge> {
		public int startP;// 起始点
		public int endP;// 终点
		public int weight;// 两点之前的距离

		public Edge(int startP, int endP, int weight) {
			super();
			this.startP = startP;
			this.endP = endP;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge target) {
			if (target == null) {
				return -1;
			}
			if (weight > target.weight) {
				return 1;
			} else if (weight < target.weight) {
				return -1;
			}
			return 0;// 相等就返回0
		}

	}

	public void printEdge(Edge[] edges) {
		char[] chars = new char[verticeSize];
		chars[0] = 'A';
		chars[1] = 'B';
		chars[2] = 'C';
		chars[3] = 'D';
		chars[4] = 'E';
		chars[5] = 'F';
		chars[6] = 'G';

		for (int i = 0; i < queryEdgeSize; i++) {
			System.out.printf("(%s, %s)---> %d \n", chars[edges[i].startP],
					chars[edges[i].endP], edges[i].weight);
		}
	}

	// //////////////////kruskal算法end////////////////

	// //////////////////最短路径：Dijkstra算法start////////////////

	/**
	 * Dijkstra算法实现
	 * 
	 * 查找节点p到图中其他各个节点的最小距离，即最短路径
	 */
	public int[] dijkstra(int p) {
		int s = p;// 源点
		// 用于保存节点p到其他节点的最短路径
		int distance[] = new int[verticeSize];
		// 用于保存已经查找过的节点，即verticesS[i]=1表示节点p到节点i的最短路径已经查找到了
		// 否则节点p到节点i的最短路径还没有找到
		int verticesS[] = new int[verticeSize];

		// 初始值：distance[i]为节点p到节点i的距离，如果p与i不相邻，则为无穷大
		for (int i = 0; i < verticeSize; i++) {
			distance[i] = matrix[s][i];
		}
		verticesS[s] = 1;// 默认源点自己，被查找
		System.out.println(verticeSize);

		int minDis = 0;
		int minPoint = 0;
		for (int i = 0; i < verticeSize; i++) {
			minDis = MAX_WEIGHT;
			minPoint = 0;

			// step1 从distance中查找到距离源点p距离最近的点
			for (int j = 0; j < verticeSize; j++) {
				// verticesS[j] == 0 节点没有被查找,即源点p到节点j的最短路径还没有被找到
				if (verticesS[j] == 0 && distance[j] < minDis) {
					minDis = distance[j];
					minPoint = j;
				}
				System.out.println("two:" + j);
			}

			if (minDis == MAX_WEIGHT) {// 查找完了
				return distance;// 说明已经找完了
			}
			// 找到与节点p路径最短的节点minPoint后，更新verticesS数组
			verticesS[minPoint] = 1;

			// step2.更新distance,也就是起始点到其他未访问节点的距离
			// 在找到一个与p路径最短的节点后， 更新源点p到其他节点的最短路径
			for (int j = 0; j < verticeSize; j++) {
				// matrix[minPoint][j] 节点minPoint到节点j的距离
				// verticesS[j]==0 节点j没有被访问过,即节点p到节点j的最短路径还没有被找到
				if (verticesS[j] == 0 && matrix[minPoint][j] > 0
						&& matrix[minPoint][j] != MAX_WEIGHT) {
					// distance[j]在没有查找到与源点p最短路径的节点前，源点p与节点j的距离
					// minDis==distance[minPoint]，源点p与节点minPoint的最小距离
					// 如果源点p到节点j的距离 > (节点minPoint到节点j的距离+源点p到节点minPoint的距离)
					if (distance[j] > (matrix[minPoint][j] + minDis)) {
						distance[j] = matrix[minPoint][j] + minDis;
					}
				}
			}
		}

		// 返回节点p到其他节点的最短路径数组
		return distance;
	}

	public static void main(String[] args) {

		// testSearch();

		// testPrimAndKruskal();

		testDijkstra(0);
	}

	// dijkstra算法计算到其他节点的最短路径
	public static void testDijkstra(int p) {
		Graph graph = new Graph(6);
		int[] v0 = new int[] { 0, MAX_WEIGHT, 5, 30, MAX_WEIGHT, MAX_WEIGHT };
		int[] v1 = new int[] { 2, 0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8 };
		int[] v2 = new int[] { MAX_WEIGHT, 15, 0, MAX_WEIGHT, 7, MAX_WEIGHT };
		int[] v3 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0,
				MAX_WEIGHT, MAX_WEIGHT };
		int[] v4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT,
				0, 18 };
		int[] v5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 4,
				MAX_WEIGHT, 0 };
		graph.matrix[0] = v0;
		graph.matrix[1] = v1;
		graph.matrix[2] = v2;
		graph.matrix[3] = v3;
		graph.matrix[4] = v4;
		graph.matrix[5] = v5;

		int[] distance = graph.dijkstra(p);
		for (int i = 0; i < 6; i++) {
			System.out.println(i + ": " + distance[i]);
		}

	}

	// prim算法和kruskal算法测试
	public static void testPrimAndKruskal() {
		Graph graph = new Graph(7);
		int[] v0 = new int[] { 0, 50, 60, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT,
				MAX_WEIGHT };
		int[] v1 = new int[] { 50, 0, MAX_WEIGHT, 65, 40, MAX_WEIGHT,
				MAX_WEIGHT };
		int[] v2 = new int[] { 60, MAX_WEIGHT, 0, 52, MAX_WEIGHT, MAX_WEIGHT,
				45 };
		int[] v3 = new int[] { MAX_WEIGHT, 65, 52, 0, 50, 30, 42 };
		int[] v4 = new int[] { MAX_WEIGHT, 40, MAX_WEIGHT, 50, 0, 70,
				MAX_WEIGHT };
		int[] v5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 30, 70, 0,
				MAX_WEIGHT };
		int[] v6 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 45, 42, MAX_WEIGHT,
				MAX_WEIGHT, 0 };
		graph.matrix[0] = v0;
		graph.matrix[1] = v1;
		graph.matrix[2] = v2;
		graph.matrix[3] = v3;
		graph.matrix[4] = v4;
		graph.matrix[5] = v5;
		graph.matrix[6] = v6;

		System.out.println(graph.prim());

		System.out.println("kruskal最小生成树算法");
		graph.printEdge(graph.kruskal());
	}

	public static void testSearch() {
		int[][] matrix = { { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

		System.out.println("-------------------");
		Graph graph2 = new Graph(11);
		graph2.setGrapth(matrix);
		graph2.bfs();// 0,1,2,3,4,5,6,7,9,8,10
		graph2.dfs();// 0,1,2,4,6,9,10,3,5,7,8
	}
}
