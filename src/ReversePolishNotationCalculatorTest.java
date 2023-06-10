import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    private ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();;



    @Test
    public void shouldCalculateAddition() {
        int res =calculator.calculatePolishNotation("8 12 +");
        assertEquals(20,res);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int res =calculator.calculatePolishNotation("22 12 -");
        assertEquals(10,res);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int res =calculator.calculatePolishNotation("8 10 *");
        assertEquals(80,res);
    }


    @Test
    public void shouldCalculateMultiplicationMinus() {
        int res =calculator.calculatePolishNotation("8 -10 *");
        assertEquals(-80,res);
    }

    @Test
    public void shouldCalculateMultpOperations() {
        int res =calculator.calculatePolishNotation("20 8 10 * +");
        assertEquals(100,res);
    }

    @Test
    public void shouldCalculateMultiplicationWithSpaces() {
        int res =calculator.calculatePolishNotation("   8     10      *");
        assertEquals(80,res);
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
} 