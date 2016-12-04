import java.io.*;
import java.util.*;

public class SudokuSolver {
    private Grid userGrid;
    private Grid solvedGrid;  //also points to userGrid
    
    private InputStreamReader isr;
    private BufferedReader in;

    public SudokuSolver() {
        userGrid = new Grid();
        
        isr = new InputStreamReader( System.in );
        in = new BufferedReader( isr );

        getGrid();
        solvedGrid = userGrid;
    }

    //gets userGrid
    public void getGrid() {
        for (;;) {
            queryGrid();
            if (userGrid.isValid())
                break;
            System.out.println("Invalid sudoku board. Try again.\n");
        }
        System.out.println(userGrid);
    }
    public void queryGrid() {
        int rowCounter = 0;

        Cell[] row = new Cell[Grid.NUM_CELLS];

        while (rowCounter < Grid.NUM_CELLS) {
            String input = "";
            
            System.out.println("Please enter the  row " + rowCounter +
                               " (input spaces for empty cell):");
            try {
                input = in.readLine();
            }
            catch ( IOException e) { }

            if (input.length() != 9) {
                System.out.println("Error: length is not 9. Try again");
                continue;
            }

            if (!interpretRow(input, row)) {
                System.out.println("Error interpreting row");
                continue;
            }

            userGrid.setRow(rowCounter, row);
            rowCounter++;
        }
        
    }
    public boolean interpretRow(String input, Cell[] row) {
        for (int i = 0; i < input.length(); i++) {
            String s = input.substring(i, i + 1);
            if (Cell.DIGITS.indexOf(s) == -1)
                return false;
            row[i] = new Cell(s);
        }

        return true;
    }
    
    public void solve() {
        Grid previousGrid = solvedGrid;
        while (!solvedGrid.isSolved()) {
            solveIterative();
            if (previousGrid == solvedGrid) {
                //to be implemented
                System.out.println("A little hard to solve?");
                return;
            }
        }
    }
    //iterate over cells, find what they can be
    //if only 1 possibility, sets true
    public void solveIterative() {
        
    }
    
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        ss.solve();
    }
}
