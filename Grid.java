public class Grid {
    public static final int NUM_CELLS = 9;
    public Cell[][] cells;

    public Grid() {
        cells = new Cell[NUM_CELLS][NUM_CELLS];
    }
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

    public void setRow(int index, Cell[] row) {
        for (int i = 0; i < NUM_CELLS; i++) {
            cells[index][i] = row[i];
        }
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
    }
}
