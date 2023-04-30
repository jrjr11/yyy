package com.example.isuproject;

public class Maze {
    private int open = 1;
    private int tried = 2;
    private int path = 3;
    private int numRow, numCol;
    private int endRow, endCol;
    private int[][] grid;

    public Maze(int row, int col, Wall[][] map, Player p)
    {
        int x = (p.getX()+25)/50;
        int y = (p.getY()-25)/50;
        grid = new int[row][col];
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(map[i][j].getIsDead() == true)
                {
                    grid[i][j] = open;
                }
                else
                    grid[i][j] = 0;
            }
        }
        endRow = x;
        endCol = y;
    }
    public int[][] getGrid()
    {
        return grid;
    }
    public int getRows()
    {
        return grid.length;
    }
    public int getCols()
    {
        return grid[0].length;
    }
    public void tryPosition(int row, int col)
    {
        grid[row][col] = tried;
    }
    public void markPath(int row, int col)
    {
        grid[row][col] = path;
    }
    public boolean solved(int row, int col)
    {
        return (row == endRow && col == endCol);
    }
    public boolean validPosition(int row, int col)
    {
        boolean result = false;

        if(row >= 0 && row < grid.length && col >= 0 && col < grid[row].length)
        {
            if (grid[row][col] == open)
                result = true;
        }
        return result;
    }
    public String toString()
    {
        String res = "\n";

        for(int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[row].length; col++)
            {
                res += grid[row][col] + "";
            }
            res += "\n";
        }
        return res;
    }
}
