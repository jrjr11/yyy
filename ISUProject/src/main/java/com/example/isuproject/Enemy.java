package com.example.isuproject;

import java.awt.*;

public class Enemy extends Entity
{
    public Enemy(int x, int y, int dx, int dy, int speed, Color color)
    {
        super(x, y, dx, dy, speed, color);

    }
    public void moveNext()
    {
        if(getY() % 50 == 5 || getY() % 50 == 10){ //Leeway within grid
        setY(getY()-5);
        }
        else if(getY() % 50 == 40 || getY() % 50 == 45){ //Leeway within grid
        setY(getY()+5);
        }
    }
    public void move(Maze maze, Wall[][] map, Player p)
    {
        int x = (getX()+25)/50;
        int y = (getY()-25)/50;
        maze = new Maze(14, 16, map, p);
        int[][] solved = maze.getGrid();
        MazeSolver solver = new MazeSolver(maze);
        if(solver.traverse(x, y))
        {
            if (x != 0)
            {
                if(solved[x-1][y] == 3)
                {
                    if(getY() % 50 == 0)
                        setX(getX()-5);
                }
            }
            if (x != 13)
            {
                if(solved[x+1][y] == 3)
                {
                    if(getY() % 50 == 0)
                        setX(getX()+5);
                }
            }
            if(y != 0)
            {
                if(solved[x][y-1] == 3)
                {
                    if(getX() % 50 == 0)
                        setY(getY()-5);

                }
            }
            if(y != 15)
            {
                if(solved[x][y+1] == 3)
                {
                    if(getX() % 50 == 0)
                        setY(getY()+5);
                }
            }
            //System.out.println("X: "+getX() % 50);
        }
        //System.out.print(maze);

    }
    public void collidesWith(Player p)
    {
        Rectangle enemy = new Rectangle(getX(), getY(), 41, 41);
        Rectangle player = new Rectangle(p.getX(), p.getY(), 41, 41);
        if (enemy.intersects(player))
        {
            p.setColor(Color.magenta);
        }
    }
    public void draw(Graphics g)
    {
        g.setColor(getColor());
        g.fillOval(getX()+10, getY()+10, 30, 30);
    }
}
