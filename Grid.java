public class Grid {
    public static final int NUM_CELLS = 9;
    public Cell[][] cells;

    public Grid() {
        cells = new Cell[NUM_CELLS][NUM_CELLS];
    }
    /*
    //values: rows contains row, column, and value
    public Grid(int[][] values) {
        this();
        for (int[] r : values) {
            Cell c = new Cell(r[2]);
            cells[r[0]][r[1]] = c;
        }
    }
    public Grid(Cell[][] newCells) {
        cells = newCells;
    }
    */
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
    
    public boolean isValid() {
        //to be implemented
        return true;
    }
    public boolean isSolved() {
        for (Cell[] r : cells) {

            for (Cell c : r) {
                if (c.digit == " ")
                    return false;
            }
        }

        return true;
    }
    
    public String toString() {
        return Utils.stringCell2(cells);
    }
    public static void main(String[] args) {
        Grid g = new Grid();
        System.out.println(g);

        Cell[] r = new Cell[9];
        for (int i = 0; i < 9; i++) {
            r[i] = new Cell(i + 1);
        }
        g.setRow(1, r);
        System.out.println(g);

        Utils.printCell1(g.getRow(0));
        Utils.printCell1(g.getRow(1));
        Utils.printCell1(g.getCol(0));
        Utils.printCell1(g.getCol(1));
        Utils.printCell2(g.getBlock(1, 1));
        Utils.printCell2(g.getBlock(1, 4));
    }
}
