import java.util.Arrays;
import java.util.List;

public class Cell {
    public List<String> possibleDigits = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
    public String digit;

    public Cell() {
	digit = "0";
    }
    public Cell(String newDig) {
	digit = newDig;
    }
    public Cell(int newDig) {
	digit = Integer.toString(newDig);
    }

    public void removePossibleDig(String dig) {
        possibleDigits.remove(dig);
    }

    public String toString() {
        return digit;
    }
    
    public static void main(String[] args) {
	Cell c = new Cell(2);
	System.out.println(c.digit);
        Utils.printList(c.possibleDigits);
    }
}
