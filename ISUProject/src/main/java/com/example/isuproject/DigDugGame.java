package com.example.isuproject1;
import javafx.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class DigDugGame extends JPanel implements KeyListener, ActionListener {
    // Instance Vars
    private Timer timer;
    private Player player;

    private boolean fire;

    private Gun gun;
    private Enemy enemy;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;

    private Entity entity;
    private Wall[][] map = new Wall[14][16];
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private Maze maze;
    private int score;

    boolean[] keys = new boolean[200];

    // Constructor
    public DigDugGame() {
        player = new Player(300, 450, 1, 1, 10, Color.blue);
        gun = new Gun(player.getX(), player.getY(),player.getDx(), player.getDy(), 10, Color.green);
        enemy = new Enemy(450, 550, 1, 1, 1, Color.red, true);
        enemy1 = new Enemy(200, 600, 1, 1, 1, Color.red, true);
        enemy2 = new Enemy(50, 300, 1, 1, 1, Color.red, true);
        enemy3 = new Enemy(500, 150, 1, 1, 1, Color.red, true);
        score = 0;
        enemies.add(enemy);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 16; j++) {
                map[i][j] = new Wall((i * 50), ((j + 1) * 50), false, false, false, false, false);
            }
        }


        addKeyListener(this);
        timer = new Timer(75, this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
    }
    public Wall[][] getMap()
    {
        return map;
    }
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 850);
        g.setColor(Color.white);
        g.drawString("" + score, 10, 20);
        g.setColor(Color.yellow);
        g.fillRect(0, 54, 700, 850);
        paintPlayerMovement();
        paintMap(g);
        g.setColor(Color.black);
        g.fillRoundRect(player.getX()+5, player.getY()+5, 41, 41, 10, 10);//to look like player is mining, creates a small black square
        enemyBehaviour(g);
        drawGun(g);
        player.draw(g);
        paintGrid(g);
    }

    public void enemyBehaviour(Graphics g)//The way the enemy behaves
    {
        for(Enemy e: enemies)
        {
            if(e.getAlive() == true) {
                e.draw(g);
                e.move(maze, map, player);
                e.collidesWith(player);
                findPlayer(g, e);
            }
            else
            {
                if(e.getY() > 650)
                {
                    score += 400;
                }
                else if(e.getY() > 450)
                {
                    score += 300;
                }
                else if(e.getY() > 250)
                {
                    score += 200;
                }
                else if(e.getY() > 0)
                {
                    score += 100;
                }

                System.out.println(score);

                enemies.remove(e);
            }
        }
    }
    public void findPlayer(Graphics g, Enemy e)
    {
        int x = (e.getX()+25)/50;
        int y = (e.getY()-25)/50;
        maze = new Maze(14, 16, map, player);
        MazeSolver solver = new MazeSolver(maze);
        e.collidesWith(player);
    }

    public void paintMap(Graphics g) {//sets all the spots on the map that are already open

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

    public void drawGun (Graphics g)//draws the gun and how it operates (vertical direction or not)
    {

        int i1 = (gun.getX()+25)/50;
        int j1 = (gun.getY()-25)/50;
        if(fire == false){
            gun.setX(player.getX()+10);
            gun.setY(player.getY()+10);
        }
        else {
            if(player.getDx() == 0) {
                gun.setDy(player.getDy());
                if(Math.abs(player.getY() - gun.getY()) < 60)
                {
                    if(j1 != 0)
                    {
                        if (map[i1][j1 - 1].getDownD() == true) {
                            gun.shootV(g);
                        }
                    }
                    if(j1 != 15)
                    {
                        if (map[i1][j1 + 1].getUpD() == true) {
                            gun.shootV(g);
                        }
                    }
                }
            }
            else {
                gun.setDx(player.getDx());
                if(Math.abs(player.getX() - gun.getX()) < 60 )
                {
                    if(i1 != 0)
                    {
                        if (map[i1-1][j1].getRightD() == true) {
                            gun.shoot(g);
                        }
                    }
                    if(i1 != 13)
                    {
                        if (map[i1+1][j1].getLeftD() == true) {
                            gun.shoot(g);
                        }
                    }
                }
            }
        }
        for(Enemy e: enemies)
            e.ifShot(gun);// if they are shot
    }


    public void paintPlayerMovement()// paints the movement of the player and sets the square they occupy to mined.
    {
        int i1 = (player.getX()+25)/50;
        int j1 = (player.getY()-25)/50;
        if(player.getY() > 25) {
            if (player.getDx() == 1) {
                if (i1 != 0) {
                    if (map[i1 - 1][j1].getRightD() == true) {
                        map[i1][j1].setLeftD(true);
                    }
                }
                if (player.getX() % 50 == 0 || player.getX() % 50 == 45 || player.getX() % 50 == 5) {
                    map[i1][j1].setDead(true);
                }
                if (player.getX() % 50 > 10 && player.getX() % 50 < 25) {
                    map[i1][j1].setRightD(true);
                }
                if (j1 != 0) {
                    if (map[i1][j1 - 1].getDownD() == true) {
                        map[i1][j1].setUpD(true);
                    }
                }
                if (j1 != 15) {
                    if (map[i1][j1 + 1].getUpD() == true) {
                        map[i1][j1].setDownD(true);
                    }
                }
            }
            else if (player.getDy() == -1) {
                if (j1 != 15) {
                    if (map[i1][j1 + 1].getUpD() == true) {
                        map[i1][j1].setDownD(true);
                    }
                }
                if (player.getY() % 50 == 0 || player.getX() % 50 == 45 || player.getX() % 50 == 5) {
                    map[i1][j1].setDead(true);
                }
                if (player.getY() % 50 < 40 && player.getY() % 50 > 25) {
                    map[i1][j1].setUpD(true);
                }
                if (i1 != 13) {
                    if (map[i1 + 1][j1].getLeftD() == true) {
                        map[i1][j1].setRightD(true);
                    }
                }
                if (i1 != 0) {
                    if (map[i1 - 1][j1].getRightD() == true) {
                        map[i1][j1].setLeftD(true);
                    }
                }
            }
            else if (player.getDx() == -1) {
                if (i1 != 13) {
                    if (map[i1 + 1][j1].getLeftD() == true) {
                        map[i1][j1].setRightD(true);
                    }
                }
                if (player.getX() % 50 == 0 || player.getY() % 50 == 45 || player.getY() % 50 == 5) {
                    map[i1][j1].setDead(true);
                }
                if (player.getX() % 50 < 40 && player.getX() % 50 > 25) {
                    map[i1][j1].setLeftD(true);
                }
                if (j1 != 0) {
                    if (map[i1][j1 - 1].getDownD() == true) {
                        map[i1][j1].setUpD(true);
                    }
                }
                if (j1 != 15) {
                    if (map[i1][j1 + 1].getUpD() == true) {
                        map[i1][j1].setDownD(true);
                    }
                }
            }
            else if (player.getDy() == 1) {
                if (j1 != 0) {
                    if (map[i1][j1 - 1].getDownD() == true) {
                        map[i1][j1].setUpD(true);
                    }
                }
                if (player.getY() % 50 == 0 || player.getY() % 50 == 45 || player.getY() % 50 == 5) {
                    map[i1][j1].setDead(true);
                }
                if (player.getY() % 50 > 10 && player.getY() % 50 < 25) {
                    map[i1][j1].setDownD(true);
                }
                if (i1 != 13) {
                    if (map[i1 + 1][j1].getLeftD() == true) {
                        map[i1][j1].setRightD(true);
                    }
                }
                if (i1 != 0) {
                    if (map[i1 - 1][j1].getRightD() == true) {
                        map[i1][j1].setLeftD(true);
                    }
                }
            }
        }
        else if (player.getDy() == 1 && player.getY() > 20) {
            map[i1][j1].setUpD(true);
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
        fire = false;
        if(keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]){
            player.moveUp();
        }

        else if(keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]){
            player.moveDown();
        }

        else if(keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]){
            player.moveLeft();
        }

        else if(keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]){
            player.moveRight();
        }
        else if(keys[KeyEvent.VK_SPACE]){
            fire = true;
        }

    }
}
