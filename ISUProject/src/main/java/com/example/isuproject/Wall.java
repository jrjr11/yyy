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
    public void setRightD(boolean rD) {rightD = rD;}
    public void setLeftD(boolean lD) {leftD = lD;}
    public void setUpD(boolean uD) {upD = uD;}
    public void setDownD(boolean dD) {downD = dD;}


    public String toString()
    {
        return "X: " + x + "\tY: " + y + "\tR: " + rightD + "\tL: " + leftD + "\tU: " + upD
                + "\tD: " + downD + "\tDDDD: " +downD;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.yellow);

        if(y>250)
        {
            g.setColor(Color.orange);
        }
        if(y>450)
        {
            g.setColor(Color.orange.darker());
        }
        if(y>650)
        {
            g.setColor(Color.red.darker());
        }
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.yellow.darker());
        if(y>250)
        {
            g.setColor(Color.orange.darker());
        }
        if(y>450)
        {
            g.setColor(Color.orange.darker().darker());
        }
        if(y>650)
        {
            g.setColor(Color.red.darker().darker());
        }
        g.fillOval(x+27, y+2, 3, 3);
        g.fillOval(x+20, y+9, 3, 3);
        g.fillOval(x+45, y+9, 3, 3);
        g.fillOval(x+8, y+14, 3, 3);
        g.fillOval(x+20, y+19, 3, 3);
        g.fillOval(x+29, y+14, 3, 3);
        g.fillOval(x+12, y+23, 3, 3);
        g.fillOval(x+40, y+25, 3, 3);
        g.fillOval(x+19, y+27, 3, 3);
        g.fillOval(x+12, y+34, 3, 3);
        g.fillOval(x+42, y+34, 3, 3);
        g.fillOval(x+10, y+45, 3, 3);
        g.fillOval(x+25, y+42, 3, 3);
        g.fillOval(x+34, y+45, 3, 3);
        g.fillOval(x+31, y+30, 3, 3);
        g.fillOval(x+3, y+25, 3, 3);
        g.fillOval(x+5, y+4, 3, 3);
        g.fillOval(x+46, y+46, 3, 3);
        if (isDead == true)
        {
            g.setColor(Color.black);
            g.fillRoundRect(x+5, y+5, 41, 41, 8, 8);
        }
        if (rightD == true)
        {
            g.setColor(Color.black);
            g.fillRoundRect(x+30, y+5, 30, 41, 8, 8);
        }
        if (leftD == true)
        {
            g.setColor(Color.black);
            g.fillRoundRect(x-10, y+5, 30, 41, 8,8);
        }
        if (upD == true)
        {
            g.setColor(Color.black);
            g.fillRoundRect(x+5, y-5, 41, 25, 8, 8);
        }
        if (downD == true)
        {
            g.setColor(Color.black);
            g.fillRoundRect(x+5, y+30, 41, 25, 8, 8);
        }
    }
}
