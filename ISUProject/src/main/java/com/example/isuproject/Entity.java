package com.example.isuproject;
import java.awt.*;

public class Entity
{
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int speed;
    private Color color;
    public Entity(int x, int y, int dx, int dy, int speed, Color color)
    {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.color = color;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public int getDx() {return dx;}
    public int getDy() {return dy;}
    public int getSpeed() {return speed;}
    public Color getColor() {return color;}

    public void setX(int x1) {x = x1;}
    public void setY(int y1) {y = y1;}
    public void setDx(int dx1) {dx = dx1;}
    public void setDy(int dy1) {dy = dy1;}
    public void setSpeed(int sp) {speed = sp;}
    public void setColor(Color c) {color = c;}

    public void move (Graphics g)
    {
        x += dx;

        draw(g);
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x+5, y+5, 40, 40);
    }

}
