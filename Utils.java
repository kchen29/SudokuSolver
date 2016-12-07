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

    //skip spaces
    //gets a row or col
    public static boolean allUnique(Cell[] arr) {
        int[] carry = new int[Grid.NUM_CELLS];
        for (Cell c : arr) {
            if (c.digit.equals(" "))
                continue;
            
            int i = Integer.parseInt(c.digit);
            if (carry[i - 1] == 0)
                carry[i - 1]++;
            else
                return false;
        }
        return true;
    }
    //gets a block
    public static boolean allUnique(Cell[][] arr) {
        int[] carry = new int[Grid.NUM_CELLS];
        for (Cell[] r : arr) {
            for (Cell c : r) {
                if (c.digit.equals(" "))
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
}
