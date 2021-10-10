package com.anish.calabashbros;

import java.awt.Color;
import java.util.Random;


public class Matrix{
    public Calabash[] lines;
    private Calabash[][] matrix;
    private World world;
    public Matrix(int groupCnt,int groupLen,World world){
        this.world = world;
        matrix = new Calabash[groupCnt][];
        lines  = new Calabash[groupLen*groupCnt];
        Random r = new Random();
        int[] seq = randomSequence(groupCnt*groupLen);
        for(int i=0;i<groupCnt;++i){
            int red = (r.nextInt(256)+255)/2;
            matrix[i]=new Calabash[groupLen];
            for(int j=0;j<groupLen;++j){
                int blue = (r.nextInt(256)+255)/2;
                int green = (r.nextInt(1+i*groupLen+j)+255)/2;
                Color color = new Color(red,blue,green);
                //matrix[i][j] = new Calabash(color, seq[i*groupLen+j], this.world);
                matrix[i][j] = new Calabash(color, green+blue/2, this.world);
                lines[i*groupLen+j]=matrix[i][j];
            }
        }
        for(int i=0;i<groupCnt;++i){
            for(int j=0;j<groupLen;++j){
                world.put(matrix[i][j],5+2*j,5+2*i);
            }
        }
    }

    private int[] randomSequence(int count) {
        int[] sequence = new int[count];
        int[] nums = new int[count];
        for (int i = 0; i < count; ++i)
            nums[i] = i;
        int n = count;
        for (int i = 0; i < count; ++i) {
            int r = (int) (Math.random() * n);
            sequence[i] = nums[r];
            nums[r] = nums[n - 1];
            --n;
        }
        return sequence;
    }

    public Calabash[] toLine(){
        return lines;
    }
}