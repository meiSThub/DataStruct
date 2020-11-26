package com.mei.test.astar;

/**
 * 地图类
 *
 * @author mei
 *
 */
public class MapInfo {

    public int[][] map;// 二阶矩阵，用于表示一个地图
    public Node start;// 寻路的起点
    public Node end;// 寻路的终点

    public int width;// 地图的宽度
    public int height;// 地图的高度

    public MapInfo(int[][] map, int width, int height, Node start, Node end) {
        super();
        this.map = map;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
    }



}
