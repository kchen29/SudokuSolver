import java.util.Arrays;
import java.util.List;

public class Cell {
    //~~~~~STATIC VARIABLES
    public static final String DIGITS = "123456789";

    //~~~~~INSTANCE VARIABLES
    public List<String> possibleDigits;
    public String digit;

    //~~~~~CONSTRUCTORS
    public Cell() {
        digit = " ";
    }
    public Cell(String newDig) {
        digit = newDig;
    }
    public Cell(int newDig) {
        digit = Integer.toString(newDig);
    }

    //~~~~~METHODS
    public boolean hasDigit() {
        return !hasNoDigit();
    }
    public boolean hasNoDigit() {
        return digit.equals(" ");
    }
    
    public String toString() {
        return digit;
    }

    //~~~~~MAIN
    public static void main(String[] args) {
        Cell c = new Cell(2);
        System.out.println(c.digit);
    }
}
