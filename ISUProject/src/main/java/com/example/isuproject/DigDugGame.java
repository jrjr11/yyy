package com.example.isuproject;
import javafx.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class DigDugGame extends JPanel implements KeyListener, ActionListener {
    // Instance Vars
    private Timer timer;
    private Player player;
    private Enemy enemy;
    private Entity entity;
    private Wall[][] map = new Wall[14][16];
    private Maze maze;

    boolean[] keys = new boolean[200];

    // Constructor
    public DigDugGame() {
        player = new Player(300, 450, 1, 1, 5, Color.blue);
        enemy = new Enemy(450, 550, 1, 1, 5, Color.green);
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                map[i][j] = new Wall((i * 50), ((j + 1) * 50), false, false, false, false, false);
            }
        }


        addKeyListener(this);
        timer = new Timer(50, this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 850);

        g.setColor(Color.yellow);
        g.fillRect(0, 54, 700, 850);
        paintPlayerMovement();
        paintMap(g);
        enemy.draw(g);
        enemy.move(maze, map, player);
        player.draw(g);
        findPlayer(g);
        enemy.collidesWith(player);
        //System.out.println(maze);
        //paintGrid(g);
    }

    public void findPlayer(Graphics g)
    {
        int x = (enemy.getX()+25)/50;
        int y = (enemy.getY()-25)/50;
        maze = new Maze(14, 16, map, player);
        MazeSolver solver = new MazeSolver(maze);

        //if(solver.traverse(x, y))
            //System.out.println("caught");
        //else
            //System.out.println("Noncaught");
        enemy.collidesWith(player);
    }

    public void paintMap(Graphics g) {

        for (int i = 0; i < 3; i++) {map[i + 5][8].setDead(true);}
        for (int i = 0; i < 2; i++) {map[i + 5][8].setRightD(true);}
        for (int i = 0; i < 2; i++) {map[i + 6][8].setLeftD(true);}

        for (int i = 0; i < 4; i++) {map[i + 9][2].setDead(true);}
        for (int i = 0; i < 3; i++) {map[i + 9][2].setRightD(true);}
        for (int i = 0; i < 3; i++) {map[i + 10][2].setLeftD(true);}

        for (int i = 0; i < 4; i++) {map[i + 2][11].setDead(true);}
        for (int i = 0; i < 3; i++) {map[i + 2][11].setRightD(true);}
        for (int i = 0; i < 3; i++) {map[i + 3][11].setLeftD(true);}

        for (int i = 0; i < 4; i++) {map[1][i + 2].setDead(true);}
        for (int i = 0; i < 3; i++) {map[1][i + 3].setUpD(true);}
        for (int i = 0; i < 3; i++) {map[1][i + 2].setDownD(true);}

        for (int i = 0; i < 4; i++) {map[9][i + 10].setDead(true);}
        for (int i = 0; i < 3; i++) {map[9][i + 11].setUpD(true);}
        for (int i = 0; i < 3; i++) {map[9][i + 10].setDownD(true);}


        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                if(map[i][j].getDownD() == true && map[i][j].getUpD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);
                else if(map[i][j].getDownD() == true && map[i][j].getRightD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);
                else if(map[i][j].getDownD() == true && map[i][j].getLeftD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);
                else if(map[i][j].getLeftD() == true && map[i][j].getUpD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);
                else if(map[i][j].getLeftD() == true && map[i][j].getRightD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);
                else if(map[i][j].getRightD() == true && map[i][j].getUpD() == true)// If two directions are occupied within a square
                    map[i][j].setDead(true);

                map[i][j].draw(g);// Draw
            }
        }
    }

    public void paintPlayerMovement()
    {
        int i1 = (player.getX()+25)/50;
        int j1 = (player.getY()-25)/50;
        //System.out.println(map[i1][j1]);

        if(player.getDx() == 1)
        {
            if(map[i1-1][j1].getRightD() == true)//if to right is true
            {
                map[i1][j1].setLeftD(true);
            }
            if(player.getX() % 50 == 0 || player.getX() % 50 == 45 || player.getX() % 50 == 5)
            {
                map[i1][j1].setDead(true);
            }
            if(player.getX() % 50 > 10 && player.getX() % 50 < 25)
            {
                map[i1][j1].setRightD(true);
            }
            if(map[i1][j1-1].getDownD() == true)//if to right is true
            {
                map[i1][j1].setUpD(true);
            }
            if(map[i1][j1+1].getUpD() == true)//if to right is true
            {
                map[i1][j1].setDownD(true);
            }
        }
        else if(player.getDy() == -1){
            if(map[i1][j1+1].getUpD() == true)//if to right is true
            {
                map[i1][j1].setDownD(true);
            }
            if(player.getY() % 50 == 0 || player.getX() % 50 == 45 || player.getX() % 50 == 5)
            {
                map[i1][j1].setDead(true);
            }
            if(player.getY() % 50 < 40 && player.getY() % 50 > 25)
            {
                map[i1][j1].setUpD(true);
            }
            //System.out.println("YLOC%%%: "+ (player.getY() % 50));
            if(map[i1+1][j1].getLeftD() == true)//if to left is true
            {
                map[i1][j1].setRightD(true);
            }
            if(map[i1-1][j1].getRightD() == true)//if to right is true
            {
                map[i1][j1].setLeftD(true);
            }
        }
        else if(player.getDx() == -1){
            if(map[i1+1][j1].getLeftD() == true)//if to left is true
            {
                map[i1][j1].setRightD(true);
            }
            if(player.getX() % 50 == 0 || player.getY() % 50 == 45 || player.getY() % 50 == 5)
            {
                map[i1][j1].setDead(true);
            }
            if(player.getX() % 50 < 40 && player.getX() % 50 > 25)
            {
                map[i1][j1].setLeftD(true);
            }
            if(map[i1][j1-1].getDownD() == true)//if to right is true
            {
                map[i1][j1].setUpD(true);
            }
            if(map[i1][j1+1].getUpD() == true)//if to right is true
            {
                map[i1][j1].setDownD(true);
            }
        }
        else if(player.getDy() == 1){
            if(map[i1][j1-1].getDownD() == true)//if to right is true
            {
                map[i1][j1].setUpD(true);
            }
            if(player.getY() % 50 == 0 || player.getY() % 50 == 45 || player.getY() % 50 == 5)
            {
                map[i1][j1].setDead(true);
            }
            if(player.getY() % 50 > 10 && player.getY() % 50 < 25)
            {
                map[i1][j1].setDownD(true);
            }
            //System.out.println("YLOC%%%: "+ (player.getY() % 50));
            if(map[i1+1][j1].getLeftD() == true)//if to left is true
            {
                map[i1][j1].setRightD(true);
            }
            if(map[i1-1][j1].getRightD() == true)//if to right is true
            {
                map[i1][j1].setLeftD(true);
            }
        }
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
        update();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void update()
    {
        if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]){
            player.moveUp();
        }

        if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
            player.moveDown();
        }

        if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
            player.moveLeft();
        }

        if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
            player.moveRight();
        }
    }
}
