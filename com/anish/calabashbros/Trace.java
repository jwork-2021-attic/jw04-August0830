package com.anish.calabashbros;

import java.awt.Color;

public class Trace extends Thing {
    public Trace(World world,int x,int y){
        super(new Color(100,255,255), (char) (7), world);
    }
}