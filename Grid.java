import java.util.*;

public class Grid {
    //~~~~~STATIC VARIABLES
    //number of cells in a row
    public static final int NUM_CELLS = 9;

    //~~~~~INSTANCE VARIABLES
    public Cell[][] cells;

    //~~~~~CONSTRUCTORS
    public Grid() {
        cells = new Cell[NUM_CELLS][NUM_CELLS];
    }
    
    //~~~~~METHODS
    //~~~get/set
    public Cell[] getRow(int r) {
        return cells[r];
    }
    public void setRow(int index, Cell[] row) {
        cells[index] = row;
    }

    public Cell[] getCol(int c) {
        Cell[] col = new Cell[NUM_CELLS];
        for (int i = 0; i < cells.length; i++) {
            col[i] = cells[i][c];
        }
        return col;
    }
    public Cell[][] getBlock(int r, int c) {
        Cell[][] block = new Cell[3][3];
        
        //locate top-left corner
        r -= r % 3;
        c -= c % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                block[i][j] = cells[r + i][c + j];
            }
        }

        return block;
    }

    //~~~isValid
    //iterate through rows, cols, blocks, if more than one of same digit appears in
    //row, col, or block, board is invalid (skip spaces)
    public boolean isValid() {
        for (int i = 0; i < NUM_CELLS; i++) {
            Cell[] row = getRow(i);
            if (!Utils.allUnique(row))
                return false;
            Cell[] col = getCol(i);
            if (!Utils.allUnique(col))
                return false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cell[][] block = getBlock(i * 3, j * 3);
                if (!Utils.allUnique(block))
                    return false;
            }
        }
        return true;
    }

    
    //~~~isSolved
    // assumes valid grid
    public boolean isSolved() {
        for (Cell[] r : cells) {
            for (Cell c : r) {
                if (c.hasNoDigit())
                    return false;
            }
        }

        return true;
    }

    
    //~~~updatePossibleDigits
    public void updatePossibleDigits() {
        for (int r = 0; r < NUM_CELLS; r++) {
            for (int c = 0; c < NUM_CELLS; c++) {
                if (cells[r][c].hasDigit())
                    continue;
                
                List<String> possibleDigs = new LinkedList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));

                Utils.filterRCB(this, r, c, possibleDigs);
                
                cells[r][c].possibleDigits = possibleDigs;
            }
        }
    }
    
    
    //~~~toString
    public String toString() {
        return Utils.stringCell2(cells);
    }

    //~~~~~MAIN
    public static void main(String[] args) {
        Grid g = new Grid();
        System.out.println(g);

        Cell[] r = new Cell[9];
        for (int i = 0; i < 9; i++) {
            r[i] = new Cell(i + 1);
        }
        g.setRow(1, r);
        g.setRow(5, r);
        System.out.println(g);

        /*
          Utils.printCell1(g.getRow(0));
          Utils.printCell1(g.getRow(1));
          Utils.printCell1(g.getCol(0));
          Utils.printCell1(g.getCol(1));
          Utils.printCell2(g.getBlock(1, 1));
          Utils.printCell2(g.getBlock(1, 4));
          Utils.printCell2(g.getBlock(3, 0));
          /*/
            
        System.out.println(Utils.allUnique(r));
        r[3] = new Cell(5);
        Utils.printCell1(r);
        System.out.println(Utils.allUnique(r));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                int dig = (j + 3 * i + 3) % 9 + 1;
                g.cells[i][j] = new Cell(dig);
            }
        }
        Utils.printCell2(g.cells);
        System.out.println(Utils.allUnique(g.getBlock(0, 0)));
        
        g.cells[0] = r;
        Utils.printCell2(g.cells);
        System.out.println(Utils.allUnique(g.getBlock(0, 0)));
    }
}
