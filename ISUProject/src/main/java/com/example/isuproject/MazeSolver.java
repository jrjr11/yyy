package com.example.isuproject1;

public class MazeSolver {
    private Maze maze;

    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }
    public boolean traverse(int row, int col)
    {
        boolean done = false;

        if (maze.validPosition(row, col))// if valid pos
        {
            maze.tryPosition(row, col);// if it has been gone thru already

            if (maze.solved(row, col))// if possible path
            {
                done = true;
            }
            else
            {
                if(!done)
                    done = traverse(row-1,col);
                if(!done)
                    done = traverse(row,col-1);
                if(!done)
                    done = traverse(row+1,col);
                if(!done)
                    done = traverse(row,col+1);
            }
            if(done)
                maze.markPath(row, col);// marks path
        }
        return done;
    }
}
