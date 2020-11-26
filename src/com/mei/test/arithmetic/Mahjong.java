package com.mei.test.arithmetic;

/**
 * 麻将实体类
 */
public class Mahjong {

    public int suit;// 花色，筒子，万子，条子

    public int rank;// 点数

    public Mahjong(int suit, int rank) {
        super();
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "(" + suit + " " + rank + ")";
    }

}
