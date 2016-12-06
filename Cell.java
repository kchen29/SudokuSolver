import java.util.Arrays;
import java.util.List;

public class Cell {
    //~~~~~STATIC VARIABLES
    public static final String DIGITS = "123456789";

    //~~~~~INSTANCE VARIABLES
    public List<String> possibleDigits = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
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
    /*
    public void removePossibleDig(String dig) {
        possibleDigits.remove(dig);
    }
    */
    public void setPossibleDig(String[] newPossibleDigits) {
        possibleDigits = Arrays.asList(newPossibleDigits);
    }
    
    public String toString() {
        return digit;
    }

    //~~~~~MAIN
    public static void main(String[] args) {
	Cell c = new Cell(2);
	System.out.println(c.digit);
        Utils.printList(c.possibleDigits);
    }
}
