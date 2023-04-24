package com.example.isuproject;

import javax.swing.JFrame;
public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        frame.setBounds(0,0,500,535);
        frame.setTitle("Dig Dug");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DigDugGame game = new DigDugGame();
        frame.add(game);

        frame.setVisible(true);
    }
}