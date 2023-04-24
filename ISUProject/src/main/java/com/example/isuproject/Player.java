package com.example.isuproject;
import java.awt.*;

public class Player extends Entity
{
    public Player(int x, int y, int dx, int dy, int speed, Color color)
    {
        super(x, y, dx, dy, speed, color);

    }

    public void moveRight()
    {
        if(getY() % 50 == 0){    //If within Row
            setX(getX()+ getSpeed());
            setDy(0);
            setDx(+1);
        }
        else if(getY() % 50 == 10){ //Leeway within grid
            setY(getY()-10);
        }
        else if(getY() % 50 == 40){ //Leeway within grid
            setY(getY()+10);
        }
        System.out.println("XLOC: "+getX());
    }
    public void moveLeft()
    {
        if(getY() % 50 == 0){    //If within Row
            setX(getX()- getSpeed());
            setDy(0);
            setDx(-1);
        }
        else if(getY() % 50 == 10){ //Leeway within grid
            setY(getY()-10);
        }
        else if(getY() % 50 == 40){ //Leeway within grid
            setY(getY()+10);
        }
        System.out.println("XLOC: "+getX());
    }
    public void moveUp()
    {
        if(getX() % 50 == 0){    //If within column
            setY(getY()- getSpeed());
            setDx(0);
            setDy(+1);

        }
        else if(getX() % 50 == 10){ //Leeway within grid
            setX(getX()-10);
        }
        else if(getX() % 50 == 40){ //Leeway within grid
            setX(getX()+10);
        }
        System.out.println("YLOC: "+getY());
    }
    public void moveDown()
    {
        if(getX() % 50 == 0){    //If within column
            setY(getY()+ getSpeed());
            setDx(0);
            setDy(-1);
        }
        else if(getX() % 50 == 10){ //Leeway within grid
            setX(getX()-10);
        }
        else if(getX() % 50 == 40){ //Leeway within grid
            setX(getX()+10);
        }
        System.out.println("YLOC: "+getY());
    }
}
