
/**
 * Write a description of class Minesweeper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Math;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinesweeperBoard2{
    Cell[][] board;
    int rows;
    int columns;
    public MinesweeperBoard2()
    {
        //Put the constructor here.
        rows = 10;
        columns = 10;
        board = new Cell[rows][columns];
        
        //These pieces are for the GUI.
        JFrame frame = new JFrame();
        frame.add(addCells());
        
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addBombs(int bombs) throws Exception
    {
        for(int i = 0; i < bombs; i++)
        {
            int rowNum = 0;
            int colNum = 0;
            int randVal = -1;
            while(randVal == -1)
            {
                rowNum = (int)(Math.random() * rows);
                colNum = (int)(Math.random() * columns);
                randVal = board[rowNum][colNum].getValue();
            }
            board[rowNum][colNum].setValue(-1);
        }
    }

    public void addNums()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(board[i][j].isBomb())
                {
                    if(i > 0 && j > 0){board[i - 1][j - 1].increment();} //TOP LEFT
                    if(i > 0 && j < columns - 1){board[i - 1][j + 1].increment();} // TOP RIGHT
                    if(i < rows - 1 && j > 0){board[i + 1][j - 1].increment();} //BOTTOM LEFT
                    if(i < rows - 1 && j < columns - 1){board[i + 1][j + 1].increment();} //BOTTOM RIGHT
                    if(i > 0){board[i - 1][j].increment();} //TOP
                    if(i < rows - 1){board[i + 1][j].increment();} //BOTTOM
                    if(j > 0){board[i][j - 1].increment();} //LEFT
                    if(j < columns - 1){board[i][j + 1].increment();} //RIGHT
                }
            }
        }
    }
    /**This method is used for testing and will be deleted if using the GUI.
     *  It is still required for all students.
     */
    public void printBoard()
    {
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(board[i][j].isBomb())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print(board[i][j].getValue() + " ");
                }
            }
            System.out.println("");
        }
    }
    public JPanel addCells()
    {
        JPanel panel = new JPanel(new GridLayout(rows,columns));
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                board[i][j] = new Cell();
                panel.add(board[i][j].getButton());
            }
        }
        return panel;
    }

}
