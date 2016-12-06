import java.io.*;
import java.util.*;

public class SudokuSolver {
    //~~~~~INSTANCE VARIABLES
    private Grid userGrid;
    private Grid solvedGrid;  //also points to userGrid
    
    private InputStreamReader isr;
    private BufferedReader in;

    //~~~~~CONSTRUCTORS
    public SudokuSolver() {
        userGrid = new Grid();
        
        isr = new InputStreamReader( System.in );
        in = new BufferedReader( isr );

        getGrid();
        solvedGrid = userGrid;
    }

    //~~~~~METHODS
    //~~~getGrid
    //gets userGrid
    public void getGrid() {
        for (;;) {
            queryGrid();
            System.out.println(userGrid);
            if (userGrid.isValid())
                break;
            System.out.println("Invalid sudoku board. Try again.\n");
        }
    }
    public void queryGrid() {
        int rowCounter = 0;
        String input = "";
        
        while (rowCounter < Grid.NUM_CELLS) {
            System.out.println("Please enter row " + rowCounter +
                               " (input spaces for empty cell):");
            try {
                input = in.readLine();
            }
            catch ( IOException e) { }

            if (input.length() != 9) {
                System.out.println("Error: length is not 9. Try again");
                continue;
            }

            Cell[] row = new Cell[Grid.NUM_CELLS];
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
            if (!s.equals(" ") && Cell.DIGITS.indexOf(s) == -1)
                return false;
            row[i] = new Cell(s);
        }

        return true;
    }

    //~~~solve
    public void solve() {
        while (!solvedGrid.isSolved()) {
            if (!solveIterative()) {
                //to be implemented
                System.out.println("A little hard to solve?");
                return;
            }
        }
    }
    
    /*Two implementations?:
      1. iterate over cells w/o digits and check what digit they are (can be)
      2. iterate over cells w/ digits and filter other cells (what they can't be)
           with addedCells, iterate only over those

      we'll do 1.
    */
    //iterate over cells, find what they can be
    //if only 1 possibility, set digit
    //return true if at least 1 cell's digit was determined; false otherwise
    public boolean solveIterative() {
        //to be implemented
        return true;
    }

    //~~~~~MAIN
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        ss.solve();
    }
}
