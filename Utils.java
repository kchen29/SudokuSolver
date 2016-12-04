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
}
