package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Random;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Node;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash hero;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        Random r = new Random();

       
       

        int red = (r.nextInt(255)+255)/2;
        int blue = (r.nextInt(255)+255)/2;
        int green = (r.nextInt(255)+255)/2;
        hero = new Calabash(new Color(red,green,blue),1,world);
        world.put(hero,5,0);

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }
    private void mazeMove(KeyEvent key){
        int keycode = key.getKeyCode();
        int[][] action ={{-1,0},{0,-1},{1,0},{0,1}};
        int[] step = action[keycode-37];
        int nxtX = step[0]+hero.getX();
        int nxtY = step[1]+hero.getY();
        if(nxtX>=0 && nxtX < World.WIDTH
        && nxtY>=0 && nxtY < World.HEIGHT
        && !(world.get(nxtX,nxtY) instanceof Node)){
            hero.moveTo(nxtX, nxtY);
        }
    }
    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        
        mazeMove(key);
        return this;
    }

}
