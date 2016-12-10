import java.util.List;
import java.util.Arrays;

public final class Utils {
    public static void printList(List<String> list) {
        System.out.println(Arrays.toString(list.toArray()));
    }
    
    public static void printCell1(Cell[] arr) {
        String s = "";
        for (Cell c : arr) {
            s += c + " ";
        }
        System.out.println(s);
    }
    public static String stringCell2(Cell[][] arr) {
        String s = "";
        for (Cell[] r : arr) {
            for (Cell c : r) {
                s += c + " ";
            }
            s += "\n";
        }
        return s;
    }
    public static void printCell2(Cell[][] arr) {
        System.out.println(stringCell2(arr));
    }

    //get a row from input
    public static boolean interpretRow(String input, Cell[] row) {
        for (int i = 0; i < input.length(); i++) {
            String s = input.substring(i, i + 1);
            if (!s.equals(" ") && Cell.DIGITS.indexOf(s) == -1)
                return false;
            row[i] = new Cell(s);
        }

        return true;
    }
    
    //skip spaces
    //true if each Cell has a different digit from the rest
    public static boolean allUnique(Cell[] arr) {
        int[] carry = new int[Grid.NUM_CELLS];
        for (Cell c : arr) {
            if (c.hasNoDigit())
                continue;
            
            int i = Integer.parseInt(c.digit);
            if (carry[i - 1] == 0)
                carry[i - 1]++;
            else
                return false;
        }
        return true;
    }
    public static boolean allUnique(Cell[][] arr) {
        int[] carry = new int[Grid.NUM_CELLS];
        for (Cell[] r : arr) {
            for (Cell c : r) {
                if (c.hasNoDigit())
                    continue;
            
                int i = Integer.parseInt(c.digit);
                if (carry[i - 1] == 0)
                    carry[i - 1]++;
                else
                    return false;
            }
        }
        return true;
    }

    //filter possibleDigs with row/col/block digits
    public static void filterRCB(Grid grid,int r, int c, List<String> possibleDigs) {
        Cell[] row = grid.getRow(r);
        filter(possibleDigs, row);

        Cell[] col = grid.getCol(c);
        filter(possibleDigs, col);

        Cell[][] block = grid.getBlock(r, c);
        filter(possibleDigs, block);
    }
    public static void filter(List<String> possibleDigs, Cell[][] checkss) {
        for (Cell[] checks : checkss) {
            filter(possibleDigs, checks);
        }
    }
    public static void filter(List<String> possibleDigs, Cell[] checks) {
        for (Cell check : checks) {
            filter(possibleDigs, check);
        }
    }
    public static void filter(List<String> possibleDigs, Cell check) {
        if (check.hasNoDigit())
            return;
        String checkDig = check.digit;
        if (possibleDigs.contains(checkDig)) {
            possibleDigs.remove(checkDig);
        }
    }

    //returns true if the digit is unique to the Cells' possibleDigits
    //exclude checking own cell
    public static boolean isUnique(String dig, Cell c, Cell[][] arr) {
        for (Cell[] row : arr) {
            if (!isUnique(dig, c, row))
                return false;
        }
        return true;
    }
    public static boolean isUnique(String dig, Cell c, Cell[] arr) {
        for (Cell check : arr) {
            if (check.hasDigit() || check == c)
                continue;
            
            if (check.possibleDigits.contains(dig)) {
                return false;
            }
        }
        return true;
    }
}
