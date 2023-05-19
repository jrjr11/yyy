package com.example.isuproject1;

import java.awt.*;

public class Gun {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private Color color;
    private int speed;
    public Gun(int x, int y, int dx, int dy, int speed, Color color)
    {

        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.color = color;
    }
    public int getX() {return x;}
    public int getSpeed() {return speed;}

    public int getY() {return y;}
    public int getDx() {return dx;}
    public int getDy() {return dy;}
    public Color getColor() {return color;}

    public void setX(int x1) {x = x1;}
    public void setSpeed(int sp) {speed = sp;}

    public void setY(int y1) {y = y1;}
    public void setDx(int dx1) {dx = dx1;}
    public void setDy(int dy1) {dy = dy1;}
    public void setColor(Color c) {color = c;}

    public void shoot (Graphics g)
    {
        x += dx * 8;
        draw(g);
    }
    public void shootV (Graphics g)//shoot vertically
    {
        y += dy * 8;
        drawV(g);
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x+5, y+10, 18, 10);
    }
    public void drawV(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x+10, y+5, 10, 18);
    }
}
