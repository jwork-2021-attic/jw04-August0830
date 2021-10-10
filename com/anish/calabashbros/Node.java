package com.anish.calabashbros;


import java.awt.Color;

public class Node extends Thing {
    public final int x;
    public final int y;
    Node(Color color, char glyph, World world,int x,int y) {
        super(color, glyph, world);
        this.x = x;
        this.y = y;
    }
}