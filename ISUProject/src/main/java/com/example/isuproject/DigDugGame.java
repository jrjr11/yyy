package com.example.isuproject1;
import javafx.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class DigDugGame extends JPanel implements KeyListener, ActionListener
{
    // Instance Vars
    private Timer timer;
    private Player player;
    private Entity entity;
    private Wall[][] map = new Wall[14][16];

    // Constructor
    public DigDugGame()
    {
        player = new Player(300, 450, 1, 1, 10, Color.blue);
        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                map[i][j] = new Wall((i * 50), ((j+1) * 50), false, false, false, false, false);
            }
        }

        addKeyListener(this);
        timer = new Timer(10, this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0, 700,850);

        g.setColor(Color.yellow);
        g.fillRect(0,54, 700,850);
        paintMap(g);
        player.draw(g);
        paintGrid(g);
    }
    public void paintMap(Graphics g)
    {
        int i1 = (player.getX()+25)/50;
        int j1 = (player.getY()-25)/50;
        Wall dead = new Wall((i1 * 50), ((j1+1) * 50), false, false, false, false, true);
        map[i1][j1] = dead;

        for(int i = 0; i < 14; i++) {map[i][0].setDead(true);}
        for(int i = 0; i < 3; i++) {map[i+5][8].setDead(true);}
        for(int i = 0; i < 4; i++) {map[i+2][11].setDead(true);}
        for(int i = 0; i < 4; i++) {map[i+9][3].setDead(true);}
        for(int i = 0; i < 4; i++) {map[1][i+3].setDead(true);}
        for(int i = 0; i < 5; i++) {map[9][i+10].setDead(true);}


        for(int i = 0; i < 14; i++)
        {
            for(int j = 0; j < 16; j++)
            {
                map[i][j].draw(g);
            }
        }
        System.out.println(map[1][3].getIsDead());
    }

    public void paintGrid(Graphics g)
    {
        for (int i = 0; i <= 850; i+= 50) {
            g.setColor(Color.black);
            g.fillRect(0, i, 700, 1);
        }
        for (int i = 0; i <= 700; i+= 50) {
            g.setColor(Color.black);
            g.fillRect(i, 0, 1, 850);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        repaint();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        timer.start();
        repaint();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        //play = true;

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            player.moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            player.moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            player.moveUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            player.moveDown();
        }
        
    }

    public void keyReleased(KeyEvent e) {

    }
}
