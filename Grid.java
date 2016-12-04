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
        String ret = "";
        for (Cell[] r : cells) {
            for (Cell c : r) {
                ret += c + " ";
            }
            ret += "\n";
        }

        return ret;
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
    }
}
