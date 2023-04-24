package com.example.isuproject;

import java.awt.*;

public class Wall
{
    private int x;
    private int y;
    private boolean rightD;
    private boolean leftD;
    private boolean upD;
    private boolean downD;
    private boolean isDead;

    public Wall(int x, int y, boolean r, boolean l, boolean u, boolean d, boolean dead)
    {
        this.x = x;
        this.y = y;
        rightD = r;
        leftD = l;
        upD = u;
        downD = d;
        isDead = dead;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public boolean getRightD() {return rightD;}
    public boolean getLeftD() {return leftD;}
    public boolean getUpD() {return upD;}
    public boolean getDownD() {return downD;}
    public boolean getIsDead() {return isDead;}

    public void setDead(boolean is) {isDead = is;}

    public void draw(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(x, y, 50, 50);

        if (isDead == true)
        {
            g.setColor(Color.red);
            g.fillRect(x, y, 50, 50);
        }
    }
}
