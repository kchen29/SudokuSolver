import java.io.*;
import java.util.*;

public class SudokuSolver {
    private Grid userGrid;
    private Grid solvedGrid;
    
    private InputStreamReader isr;
    private BufferedReader in;

    public SudokuSolver() {
        userGrid = new Grid();
        solvedGrid = new Grid();
        
        isr = new InputStreamReader( System.in );
        in = new BufferedReader( isr );
        
        queryGrid();
        System.out.println(userGrid);
    }

    //gets userGrid
    public void queryGrid() {
        int rowCounter = 0;

        Cell[] row = new Cell[9];

        for (;;) {
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
            if (rowCounter == 9)
                break;
        }
        
    }
    public boolean interpretRow(String input, Cell[] row) {
        for (int i = 0; i < input.length(); i++) {
            
        }
    }
    

    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
    }
}
