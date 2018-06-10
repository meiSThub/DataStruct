package com.mei.test.datastruct.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ͼ
 */
public class Graph {

	// ���ڱ�ʾ����󣬼�����֮�䲻�ɴû��·��
	private static final int MAX_WEIGHT = Integer.MAX_VALUE;

	private int verticeSize;// ������
	private int[] vertices;// ����
	private int[][] matrix;// �ڽӾ������ڴ��ͼ�ı�
	private boolean[] visited;// ���ڴ�Ŷ���ķ������

	public Graph(int size) {
		verticeSize = size;
		vertices = new int[size];
		matrix = new int[size][size];
		visited = new boolean[size];

		// ��ʼ������
		for (int i = 0; i < size; i++) {
			vertices[i] = i;
			visited[i] = false;
		}
	}

	public void setGrapth(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * ��ȡͼ�Ķ��������
	 * 
	 * @return
	 */
	public int getVerticesSize() {
		return verticeSize;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public int[] getVertices() {
		return vertices;
	}

	/**
	 * ��ȡ����v1��v2��Ȩ��
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
	 * ��ȡ����v�����
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
	 * ��ȡ����v�ĳ���
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
	 * ��ȡ����v�ĵ�һ���ڽӵ�
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
	 * ���ҽڵ�v ���ڽӵ� index����һ���ڽӵ�
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
	 * ͼ�����������
	 */
	public void dfs() {
		for (int i = 0; i < verticeSize; i++) {
			visited[i] = false;
		}
		System.out.println("size=" + verticeSize);

		for (int i = 0; i < verticeSize; i++) {
			if (!visited[i]) {// �������û�б����ʣ��������
				visited[i] = true;
				System.out.println(i);// ���ʸö���
				dfs(i);
			}
		}
	}

	// ���ƶ�������ǰ�����
	private void dfs(int i) {
		if (!visited[i]) {
			visited[i] = true;
			System.out.println(i);
		}
		int v = getFirstNeighbor(i);// ���ʶ���i�ĵ�һ���ڽӵ�
		while (v != -1) {
			if (!visited[v]) {// �������vû�б����ʣ����������
				visited[v] = true;
				System.out.println(v);
				v = getFirstNeighbor(v);// ���ŷ��ʶ���v�ĵ�һ���ڽӵ�
				dfs(v);
			}
			// ����ȱ������ص�����iʱ��������������v����һ���ڽӵ㣬����ֻ�Ƿ����˵�һ���ڽӵ�
			v = getNextNeighbor(i, v);
		}
	}

	/**
	 * ͼ�����������
	 * 
	 * Ĭ�ϴӶ���v0��ʼ����
	 */
	public void bfs() {
		for (int i = 0; i < verticeSize; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < verticeSize; i++) {
			if (!visited[i]) {// ����i�Ƿ񱻷���
				visited[i] = true;
				System.out.println("���ʽڵ㣺v" + i);
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
				System.out.println("���ʽڵ㣺v" + v);
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
	 * ��С�������㷨֮prim�㷨
	 * 
	 * �Ѷ���ֳ��������֣�һ�������Ѿ������˵ģ���һ�����ǻ�û�в��ҵ�
	 * 
	 * ���Ѳ��ҵĶ��㼯��Ϊu
	 */
	public int prim() {
		/*
		 * ��һ������lowcost����ʾ�Ѳ��Ҷ����δ���Ҷ��㣬���� lowcost[i] == 0 ʱ����ʾ�ڵ�i �Ѿ�������
		 * ���lowcost[i] ��= 0 ����ô��Ȼ��һ��ֵ�� ���ֵ����������ʾ���ҵ��ļ��� �� �±�Ϊi ������ڵ�� ��С����
		 * 
		 * ����ӽڵ�v0��ʼ���ң���ʼ����ʱ��lowcost[i]�ͱ�ʾ�˶���v0�������ڵ�ľ���
		 */
		int[] lowcost = new int[verticeSize];
		for (int i = 0; i < verticeSize; i++) {
			lowcost[i] = matrix[0][i];// Ĭ�ϴӶ���v0��ʼ��ʣ�ඥ�㵽v0�ľ���
		}

		int min = 0;// ʣ�ඥ���е��Ѳ��ҵĶ��������С�� ���룬��Ȩ��
		int minIndex = 0;// ���Ѳ��Ҷ��������С�Ķ��� ��������
		int weightCount = 0;// ��С����������Ȩ��
		for (int i = 0; i < verticeSize; i++) {
			min = MAX_WEIGHT;// ��ʼ����С������Ϊ�����
			minIndex = 0;
			// step1: ����ʣ�µĽڵ㵽�Ѿ������ҽڵ㼯��U�о�����̵Ľڵ�
			for (int j = 0; j < verticeSize; j++) {
				if (lowcost[j] > 0 && lowcost[j] < min) {// �ҵ�������С�Ķ���
					min = lowcost[j];
					minIndex = j;
				}
			}

			// ˵���Ҳ������뼯��u������С�Ķ����ˣ������ж��㶼��������
			if (min == MAX_WEIGHT) {
				break;
			}
			weightCount += min;
			// step2���ҵ�����ڵ����Ҫ����ʣ�����Ľڵ㵽�Ѿ������ҽڵ㼯��U �ľ���
			lowcost[minIndex] = 0;
			// �ҵ����뼯��u������С�Ķ���󣬸��¼���u�͵���ʣ�ඥ��ľ���
			for (int j = 0; j < verticeSize; j++) {
				// lowcost[j]��ʾ���ҵ��ڵ�ʣ��ڵ�j�ľ��룬matrix[minIndex][j]��ʾ�²��ҵ��Ľڵ�minIndex
				// ���ڵ�j�ľ��룬���matrix[minIndex][j]С���͸���lowcost
				if (lowcost[j] != 0 && lowcost[j] > matrix[minIndex][j]) {
					lowcost[j] = matrix[minIndex][j];
				}
			}
		}

		return weightCount;
	}

	// //////////////////prim�㷨����////////////////////

	// //////////////////kruskal�㷨start////////////////
	private int queryEdgeSize = 0;// ���ҵ�����С�ߵ�����

	/**
	 * kruskal��С�������㷨
	 */
	public Edge[] kruskal() {
		// ��ȡ���е���Ч��
		Edge[] edges = getEdges();
		// ���ڱ����Ѳ��ҵ��Ķ���������ͨ������󶥵㣬�±� ��ʾ��ʼ���㣬ֵ��ʾ�������ͨ���յ�
		// ��ѭ�����±�Ӧ�ñ�ֵС
		// ��edge_temp[1]=5����ʾ����1������5�бߣ�����·�����ߣ���1��5����ͨ�ģ�
		int[] edge_temp = new int[verticeSize];
		sortEdge(edges);// ��С��������
		// ���ڱ��浱ǰ�ҵ�����С��
		Edge[] queryEdges = new Edge[edgeSize];

		int weight = 0;

		int index = 0;
		for (int i = 0; i < edgeSize; i++) {
			int p1 = edges[i].startP;
			int p2 = edges[i].endP;

			int m = getEnd(edge_temp, p1);// ���Ѳ��ҽڵ㼯���У��ҵ���ڵ�p1��ͨ�����ڵ�
			int n = getEnd(edge_temp, p2);
			if (m != n) {// ���p1��p2��ͨ�����ڵ㲻��ͬ��˵��p1��p2�ǲ���ͨ�ģ���ô�����߾�����Ч��
				if (m > n) {// ��С�Ķ�����Ϊ�±꣬��Ķ�����Ϊֵ
					int temp = m;
					m = n;
					n = temp;
				}
				// �����Ѳ��ҽڵ�����ͨ�������ڵ㣬���ڵ�m����ͨ��n
				edge_temp[m] = n;
				queryEdges[index++] = edges[i];
				weight += edges[i].weight;// ��С��������Ȩ��
			}
		}

		queryEdgeSize = index;// ������ҵ�����С�ߵ�����
		System.out.println("weight=" + weight);

		return queryEdges;// �����ҵ�����С�ߵļ���
	}

	/**
	 * �ڼ���edge_temp�У����ҽڵ�p����ͨ�������ڵ�
	 * 
	 * @param edge_temp
	 * @param p
	 * @return
	 */
	private int getEnd(int[] edge_temp, int p) {
		int i = p;// ������Ѳ��ҵ���ͨ����У��Ҳ�����p��ͨ�����ڵ㣬��ֱ�ӷ��ؽڵ�p
		while (edge_temp[i] != 0) {
			i = edge_temp[i];
		}
		return i;
	}

	/**
	 * �Ա߽�������
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

	private int edgeSize = 0;// �ߵ�����

	/**
	 * ��ȡ���еıߵļ���
	 * 
	 * @return
	 */
	public Edge[] getEdges() {
		Edge[] edges = new Edge[verticeSize * verticeSize];
		int index = 0;
		edgeSize = 0;
		for (int i = 0; i < verticeSize; i++) {
			for (int j = 0; j < verticeSize; j++) {
				if (matrix[i][j] > 0 && matrix[i][j] != MAX_WEIGHT) {// ȡ��һ����Ч��
					edges[index++] = new Edge(i, j, matrix[i][j]);// ����i��j��һ����
				}
			}
		}
		edgeSize = index;
		return edges;
	}

	/**
	 * ���ڱ�ʾһ����
	 */
	public static class Edge implements Comparable<Edge> {
		public int startP;// ��ʼ��
		public int endP;// �յ�
		public int weight;// ����֮ǰ�ľ���

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
			return 0;// ��Ⱦͷ���0
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

	// //////////////////kruskal�㷨end////////////////

	// //////////////////���·����Dijkstra�㷨start////////////////

	/**
	 * Dijkstra�㷨ʵ��
	 * 
	 * ���ҽڵ�p��ͼ�����������ڵ����С���룬�����·��
	 */
	public int[] dijkstra(int p) {
		int s = p;// Դ��
		// ���ڱ���ڵ�p�������ڵ�����·��
		int distance[] = new int[verticeSize];
		// ���ڱ����Ѿ����ҹ��Ľڵ㣬��verticesS[i]=1��ʾ�ڵ�p���ڵ�i�����·���Ѿ����ҵ���
		// ����ڵ�p���ڵ�i�����·����û���ҵ�
		int verticesS[] = new int[verticeSize];

		// ��ʼֵ��distance[i]Ϊ�ڵ�p���ڵ�i�ľ��룬���p��i�����ڣ���Ϊ�����
		for (int i = 0; i < verticeSize; i++) {
			distance[i] = matrix[s][i];
		}
		verticesS[s] = 1;// Ĭ��Դ���Լ���������
		System.out.println(verticeSize);

		int minDis = 0;
		int minPoint = 0;
		for (int i = 0; i < verticeSize; i++) {
			minDis = MAX_WEIGHT;
			minPoint = 0;

			// step1 ��distance�в��ҵ�����Դ��p��������ĵ�
			for (int j = 0; j < verticeSize; j++) {
				// verticesS[j] == 0 �ڵ�û�б�����,��Դ��p���ڵ�j�����·����û�б��ҵ�
				if (verticesS[j] == 0 && distance[j] < minDis) {
					minDis = distance[j];
					minPoint = j;
				}
				System.out.println("two:" + j);
			}

			if (minDis == MAX_WEIGHT) {// ��������
				return distance;// ˵���Ѿ�������
			}
			// �ҵ���ڵ�p·����̵Ľڵ�minPoint�󣬸���verticesS����
			verticesS[minPoint] = 1;

			// step2.����distance,Ҳ������ʼ�㵽����δ���ʽڵ�ľ���
			// ���ҵ�һ����p·����̵Ľڵ�� ����Դ��p�������ڵ�����·��
			for (int j = 0; j < verticeSize; j++) {
				// matrix[minPoint][j] �ڵ�minPoint���ڵ�j�ľ���
				// verticesS[j]==0 �ڵ�jû�б����ʹ�,���ڵ�p���ڵ�j�����·����û�б��ҵ�
				if (verticesS[j] == 0 && matrix[minPoint][j] > 0
						&& matrix[minPoint][j] != MAX_WEIGHT) {
					// distance[j]��û�в��ҵ���Դ��p���·���Ľڵ�ǰ��Դ��p��ڵ�j�ľ���
					// minDis==distance[minPoint]��Դ��p��ڵ�minPoint����С����
					// ���Դ��p���ڵ�j�ľ��� > (�ڵ�minPoint���ڵ�j�ľ���+Դ��p���ڵ�minPoint�ľ���)
					if (distance[j] > (matrix[minPoint][j] + minDis)) {
						distance[j] = matrix[minPoint][j] + minDis;
					}
				}
			}
		}

		// ���ؽڵ�p�������ڵ�����·������
		return distance;
	}

	public static void main(String[] args) {

		// testSearch();

		// testPrimAndKruskal();

		testDijkstra(0);
	}

	// dijkstra�㷨���㵽�����ڵ�����·��
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

	// prim�㷨��kruskal�㷨����
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

		System.out.println("kruskal��С�������㷨");
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
