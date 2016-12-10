import java.io.*;
import java.util.*;
import java.nio.file.*;

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
            System.out.println("\n" + userGrid);
            if (userGrid.isValid())
                break;
            System.out.println("Invalid sudoku board. Try again.\n");
        }
    }
    public void queryGrid() {
        //int type = queryType();
        int type = 2;

        //for type 2
        List<String> lines = queryLines();
        if (lines.size() < 9)
            return;
        
        int rowCounter = 0;
        String input = "";
        
        while (rowCounter < Grid.NUM_CELLS) {

            input = queryInput(type, lines, rowCounter);

            if (input.length() != 9) {
                System.out.println("Error: length is not 9. Try again");
                continue;
            }

            Cell[] row = new Cell[Grid.NUM_CELLS];
            if (!Utils.interpretRow(input, row)) {
                System.out.println("Error interpreting row");
                continue;
            }
            if (!Utils.allUnique(row)) {
                System.out.println("Error: invalid row");
                continue;
            }
            
            userGrid.setRow(rowCounter, row);
            rowCounter++;
        }
        
    }
    public int queryType() {
        System.out.println("Method of input:\n1. Type row by row\n2. File input");
        try {
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            return 2;
        }
    }
    public static List<String> queryLines() {
        Path file = Paths.get("ESB/exampleSudokuBoard.txt");
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            return new ArrayList<String>();
        }
    }
    public String queryInput(int type, List<String> lines, int rowCounter) {
        if (type == 2)
            return lines.get(rowCounter);
        
        System.out.println("Please enter row " + rowCounter +
                           " (input spaces for empty cell):");
        try {
            return in.readLine();
        } catch ( IOException e) {
            return "";
        }
    }

    //~~~solve
    public void solve() {
        //vals: invalid grid, added cells
        boolean[] vals;
        
        while (!solvedGrid.isSolved()) {
            vals = solveIterative();
            if (vals[0]) {
                System.out.println("Invalid grid\n");
                break;
            }
            if (!vals[1]) {
                //to be implemented
                System.out.println("A little hard to solve?\n");
                if (!solvedGrid.isValid())
                    System.out.println("invalid grid!");
                break;
            }
        }
        
        if (solvedGrid.isValid() && solvedGrid.isSolved())
            System.out.println("\nSudoku Grid is valid and solved!\n");
        System.out.println(solvedGrid);
    }

    public boolean[] solveIterative() {
        boolean[] vals = OPFC();
        if (vals[0])
            return vals;
        if (!vals[1]) {
            solvedGrid.updatePossibleDigits();
            return OPIRCB();
        }
        return vals;
    }
    
    //"Only possiblity for cell" method
    /*Two possible implementations?:
      1. iterate over cells w/o digits and check what digit they are (can be)
      2. iterate over cells w/ digits and filter other cells (what they can't be)
           with addedCells, iterate only over those

      we'll do 1.
    */
    /*iterate over cells, find what they can be
      if only 1 possibility, set digit
      if 0 possibilites, grid is invalid
      if the grid is invalid, set vals[0] true;
      if 1 cell's digit was added, set vals[1] true;
    */
    public boolean[] OPFC() {
        boolean[] vals = {false, false};
        for (int r = 0; r < Grid.NUM_CELLS; r++) {
            for (int c  = 0; c < Grid.NUM_CELLS; c++) {
                if (solvedGrid.cells[r][c].hasDigit())
                    continue;

                //https://stackoverflow.com/questions/2965747/why-i-get-unsupportedoperationexception-when-trying-to-remove-from-the-list
                List<String> possibleDigs = new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
                
                Utils.filterRCB(solvedGrid, r, c, possibleDigs);

                if (possibleDigs.size() == 1) {
                    solvedGrid.cells[r][c].digit = possibleDigs.get(0);
                    vals[1] = true;
                } else if (possibleDigs.isEmpty()) {
                    vals[0] = true;
                    System.out.println("debug: r: " + r + "\tc: " + c);
                    return vals;
                }
            }
        }
        
        return vals;
    }

    //"Only possiblity in row/col/block" method
    /*
      iterate over row/col/block
      if digit of a cell is unique to row/col/block, set the cell's digit as that digit
      if 1 cell's digit was added, set vals[1] true;
     */
    
    public boolean[] OPIRCB() {
        List<Object[]> toAdd = new LinkedList<Object[]>();
        
        //accumulate changes to be made (if done in spot, have to update possible digits)
        for (int r = 0; r < Grid.NUM_CELLS; r++) {
            for (int c = 0; c < Grid.NUM_CELLS; c++) {
                if (solvedGrid.cells[r][c].hasDigit())
                    continue;

                accumulateChange(toAdd, r, c);
            }
        }

        causeChange(toAdd);
        
        boolean[] vals = {false, false};
        if (!toAdd.isEmpty())
            vals[1] = true;
        
        return vals;
    }
    public void accumulateChange(List<Object[]> toAdd, int r, int c) {
        Cell cell = solvedGrid.cells[r][c];
        
        Cell[] row = solvedGrid.getRow(r);
        Cell[] col = solvedGrid.getCol(c);
        Cell[][] block = solvedGrid.getBlock(r, c);

        for (String dig : cell.possibleDigits) {
            if (!Utils.isUnique(dig, cell, row) && !Utils.isUnique(dig, cell, col) && !Utils.isUnique(dig, cell, block))
                continue;
            Object[] add = {cell, dig};
            toAdd.add(add);
            return;
        }
    }
    public void causeChange(List<Object[]> toAdd) {
        for (Object[] add : toAdd) {
            Cell cell = (Cell) add[0];
            String dig = (String) add[1];
            cell.digit = dig;
        }
    }
    
    //~~~~~MAIN
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        ss.solve();

        /*
        ss.userGrid.updatePossibleDigits();

        for (Cell[] row : ss.userGrid.getBlock(0,6)) {
            for (Cell c : row) {
                if (c.hasNoDigit())
                    Utils.printList(c.possibleDigits);
            }
            System.out.println();
        }

        ss.OPIRCB();

        Utils.printCell2(ss.solvedGrid.cells);
        */
    }
}
