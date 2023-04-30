package com.example.isuproject;

public class MazeSolver {
    private Maze maze;

    public MazeSolver(Maze maze)
    {
        this.maze = maze;
    }
    public boolean traverse(int row, int col)
    {
        boolean done = false;

        if (maze.validPosition(row, col))
        {
            maze.tryPosition(row, col);

            if (maze.solved(row, col))
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
                maze.markPath(row, col);
        }
        return done;
    }
}
