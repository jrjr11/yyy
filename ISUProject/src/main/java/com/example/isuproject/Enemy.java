package com.example.isuproject1;

import java.awt.*;

public class Enemy extends Entity
{
    private boolean alive;
    public Enemy(int x, int y, int dx, int dy, int speed, Color color, boolean alive1)
    {
        super(x, y, dx, dy, speed, color);
        alive = alive1;

    }
    public void setAlive(boolean alive1){alive = alive1;};
    public boolean getAlive(){return alive;};

    public void moveNext()
    {
        if(getY() % 50 == 5 || getY() % 50 == 10){ //Leeway within grid
            setY(getY()-5);
        }
        else if(getY() % 50 == 40 || getY() % 50 == 45){ //Leeway within grid
            setY(getY()+5);
        }
    }
    public void move(Maze maze, Wall[][] map, Player p) {
        int x = (getX()+25)/50;
        int y = (getY() - 25) / 50;

        maze = new Maze(14, 16, map, p);
        int[][] solved = maze.getGrid();
        MazeSolver solver = new MazeSolver(maze);
        if(solver.traverse(x, y))
        {
            if (x != 0)
            {
                if(solved[x-1][y] == 3)
                {
                    if(getY() % 50 == 0&& map[x][y].getLeftD() == true) {
                        setX(getX() - 5);
                        setDx(-1);

                        setDy(0);
                    }
                    else if(getY() % 50 != 0)
                    {
                        if(getDy() == 1)
                        {
                            setY(getY()+5);
                        }
                        else if(getDy() == -1)
                            setY(getY()-5);
                    }
                }
            }
            if (x != 13)
            {
                if(solved[x+1][y] == 3)
                {
                    if(getY() % 50 == 0 && map[x][y].getRightD() == true){
                        setX(getX()+5);
                        setDx(1);
                        setDy(0);
                    }
                    else if(getY() % 50 != 0)
                    {
                        if(getDy() == 1)
                        {
                            setY(getY()+5);
                        }
                        else if(getDy() == -1)
                            setY(getY()-5);
                    }
                }
            }
            if(y != 0)// up
            {
                if(solved[x][y-1] == 3)
                {
                    if(getX() % 50 == 0 && map[x][y].getUpD() == true) {
                        setY(getY() - 5);
                        setDy(-1);
                        setDx(0);
                    }
                    else if(getX() % 50 != 0)
                    {
                        if(getDx() == 1)
                        {
                            setX(getX()+5);
                        }
                        else if (getDx() == -1)
                            setX(getX()-5);
                    }
                }
            }
            if(y != 15)// down
            {
                if(solved[x][y+1] == 3)
                {
                    if(getX() % 50 == 0 && map[x][y].getDownD() == true) {
                        setY(getY() + 5);
                        setDy(1);
                        setDx(0);
                    }
                    else if(getX() % 50 != 0)
                    {
                        if(getDx() == 1)
                        {
                            setX(getX()+5);
                        }
                        else if (getDx() == -1)
                            setX(getX()-5);
                    }
                }
            }
        }

    }
    public void collidesWith(Player p)
    {
        if(getAlive() == true) {
            Rectangle enemy = new Rectangle(getX(), getY(), 30, 30);
            Rectangle player = new Rectangle(p.getX(), p.getY(), 30, 30);
            if (enemy.intersects(player)) {
                p.setColor(Color.magenta);
            }
        }
    }
    public void ifShot(Gun g)
    {
        Rectangle enemy = new Rectangle(getX(), getY(), 30, 30);
        Rectangle gun = new Rectangle(g.getX(), g.getY(), 5, 5);
        if (enemy.intersects(gun))
        {
            setAlive(false);
            setColor(Color.magenta);
        }
    }
    public void draw(Graphics g)
    {
            g.setColor(getColor());
            g.fillOval(getX() + 10, getY() + 10, 30, 30);

    }
}
