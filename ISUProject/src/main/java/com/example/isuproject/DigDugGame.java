
package com.example.isuproject;
import javafx.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class DigDugGame extends JPanel implements KeyListener, ActionListener
{
    // Instance Vars
    private Timer timer;

    // Constructor
    public DigDugGame()
    {
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0, 500, 500);

        g.setColor(Color.yellow);
        g.fillRect(0,20, 500, 480);
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

    }

    public void keyReleased(KeyEvent e) {

    }
}