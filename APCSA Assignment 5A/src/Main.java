import java.util.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Test Case for Frac Class
        {
            Fraction fraction1 = new Fraction();
            System.out.println("0/1");
            System.out.println("Actual: " + fraction1.toString());
            System.out.println();

            // Test Case 2: Parameterized Constructor with valid values
            Fraction fraction2 = new Fraction(3, 4);
            System.out.println("3/4");
            System.out.println("Actual: " + fraction2.toString());
            System.out.println();

            // Test Case 3: Parameterized Constructor with denominator 0
            Fraction fraction3 = new Fraction(5, 0);
            System.out.println();

            // Test Case 4: String Constructor with valid string
            Fraction fraction4 = new Fraction("2/5");
            System.out.println("2/5");
            System.out.println("Actual: " + fraction4.toString());
            System.out.println();

            // Test Case 5: String Constructor with denominator 0
            Fraction fraction5 = new Fraction("1/0");
            System.out.println();

            // Test Case 6: Copy Constructor
            Fraction fraction6 = new Fraction(fraction2);
            System.out.println("3/4");
            System.out.println("Actual: " + fraction6.toString());
            System.out.println();

            // Test Case 7: Multiplication
            Fraction fraction7 = Fraction.multiply(fraction2, fraction4);
            System.out.println("6/20");
            System.out.println("Actual: " + fraction7.toString());
            System.out.println();

            // Test Case 8: Division
            Fraction fraction8 = Fraction.divide(fraction2, fraction4);
            System.out.println("15/8");
            System.out.println("Actual: " + fraction8.toString());
            System.out.println();

            // Test Case 9: Addition
            Fraction fraction9 = Fraction.add(fraction2, fraction4);
            System.out.println("23/20");
            System.out.println("Actual: " + fraction9.toString());
            System.out.println();

            // Test Case 10: Subtraction
            Fraction fraction10 = Fraction.subtract(fraction2, fraction4);
            System.out.println("7/20");
            System.out.println("Actual: " + fraction10.toString());

        }
        //Problem 1
        {

            final Fraction MILU = new Fraction(355, 113);
            final double EPSILON = Math.abs(Math.PI - MILU.toDouble());
            Fraction closestFraction = new Fraction(3, 1); // Starting with a simple approximation 3/1

            while (Math.abs(Math.PI - closestFraction.toDouble()) >= EPSILON) {
                if (closestFraction.toDouble() < Math.PI) {
                    // If the approximation is too small, increase the numerator
                    closestFraction.setNum(closestFraction.getNum() + 1);
                } else {
                    // Otherwise, increase the denominator
                    closestFraction.setDenom(closestFraction.getDenom() + 1);
                }
            }

            System.out.println("The closest fraction to Pi is: " + closestFraction);
        }
        //Problem 2
        {
            Scanner scanner = new Scanner(System.in);
            int wins = 0, losses = 0;
            String input = "";
            System.out.println("Let the Fraction Quiz Begin. Answers should be in lowest terms. Good luck!");

            while (true) {
                Fraction f1 = Fraction.generateRandomFrac();
                Fraction f2 = Fraction.generateRandomFrac();
                Fraction correctAnswer = new Fraction();
                String operation = getRandom();

                if (operation.equals("+")) {
                    correctAnswer = Fraction.add(f1, f2);
                } else if (operation.equals("-")) {
                    correctAnswer = Fraction.subtract(f1, f2);
                } else if (operation.equals("*")) {
                    correctAnswer = Fraction.multiply(f1, f2);
                } else if (operation.equals("/")) {
                    correctAnswer = Fraction.divide(f1, f2);
                }

                System.out.print(f1 + " " + operation + " " + f2 + " = ");
                input = scanner.nextLine();
                if (input.toLowerCase().equals("quit"))
                    break;
                try {
                    if (!isValid(input)) {
                        throw new IllegalArgumentException("Invalid fraction format.");
                    }
                    Fraction userAnswer = new Fraction(input);
                    if (userAnswer.equals(correctAnswer)) {
                        System.out.println("Correct!");
                        wins++;
                    } else {
                        System.out.println("Wrong, the answer was " + correctAnswer);
                        losses++;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. Please enter a valid fraction.");
                    continue; // Skip to the next iteration of the loop
                }
            }

            Fraction userScore = new Fraction(wins, wins + losses);
            System.out.println("Your win/loss ratio was " + userScore + ", for a score of " + Math.round(userScore.toDouble() * 100) + " percent!");
        }
    }
    private static String getRandom() {
        String[] operations = {"+", "-", "*", "/"};
        Random random = new Random();
        return operations[random.nextInt(operations.length)];
    }
    private static boolean isValid(String input) {
        int slashIndex = input.indexOf('/');
        return slashIndex > 0 && slashIndex < input.length() - 1;
    }
}
