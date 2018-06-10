package com.mei.test.astar.teach;

/**
 * Created by 48608 on 2017/12/15.
 */
public class Coord {
	public int x;
	public int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Coord) {
			Coord c = (Coord) obj;
			return x == c.x && y == c.y;
		}
		return super.equals(obj);
	}
}