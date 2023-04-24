package com.example.isuproject;
import java.awt.*;

public class Entity
{
    private int x;
    private int y;
    private int dx;
    private int dy;
    private double speed;
    public Entity(int x, int y, int dx, int dy, double speed)
    {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public int getDx() {return x;}
    public int getDy() {return y;}

    public void move (Graphics g)
    {
        x += dx;
        y += dy;

        draw(g);
    }

    public void draw(Graphics g)
    {

    }

}