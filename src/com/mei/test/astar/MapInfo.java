package com.mei.test.astar;

/**
 * ��ͼ��
 * 
 * @author mei
 *
 */
public class MapInfo {

	public int[][] map;// ���׾������ڱ�ʾһ����ͼ
	public Node start;// Ѱ·�����
	public Node end;// Ѱ·���յ�

	public int width;// ��ͼ�Ŀ��
	public int height;// ��ͼ�ĸ߶�
	
	public MapInfo(int[][] map, int width, int height, Node start, Node end) {
		super();
		this.map = map;
		this.width = width;
		this.height = height;
		this.start = start;
		this.end = end;
	}
	
	

}
