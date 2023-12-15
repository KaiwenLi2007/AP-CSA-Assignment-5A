import java.util.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Test Case for Frac Class
        {

            // Test case for the default constructor
            Fraction fraction1 = new Fraction();
            System.out.println("Expected: 0/1");
            System.out.println("Actual: " + fraction1.toString());
            System.out.println();

            // Test case for the constructor with two parameters
            Fraction fraction2 = new Fraction(2, 3);
            System.out.println("Expected: 2/3");
            System.out.println("Actual: " + fraction2.toString());
            System.out.println();

            // Test case for the constructor with a string parameter
            Fraction fraction3 = new Fraction("4/5");
            System.out.println("Expected: 4/5");
            System.out.println("Actual: " + fraction3.toString());
            System.out.println();

            // Test case for the constructor that copies another Fraction
            Fraction fraction4 = new Fraction(fraction2);
            System.out.println("Expected: 2/3");
            System.out.println("Actual: " + fraction4.toString());
            System.out.println();

            // Test case for the method toDouble
            System.out.println("Expected for toDouble (2/3): " + 2.0 / 3);
            System.out.println("Actual for toDouble: " + fraction2.toDouble());
            System.out.println();

            // Test case for the static method add
            Fraction sum = Fraction.add(fraction2, fraction3);
            System.out.println("Expected for add (2/3 + 4/5): " + (2 * 5 + 3 * 4) + "/" + (3 * 5));
            System.out.println("Actual for add: " + sum.toString());
            System.out.println();

            Fraction difference = Fraction.subtract(fraction2, fraction3);
            System.out.println("Expected for subtract (2/3 - 4/5): " + (2 * 5 - 3 * 4) + "/" + (3 * 5));
            System.out.println("Actual for subtract:" + difference.toString());
            System.out.println();

            // Test case for the static method multiply
            Fraction product = Fraction.multiply(fraction2, fraction3);
            System.out.println("Expected for multiply (2/3 * 4/5): " + (2 * 4) + "/" + (3 * 5));
            System.out.println("Actual for multiply: " + product.toString());
            System.out.println();

            // Test case for the static method divide
            Fraction a = Fraction.divide(fraction2, fraction3);
            System.out.println("Expected for divide (2/3 / 4/5): " + (2 * 5) + "/" + (3 * 4));
            System.out.println("Actual for divide: " + a.toString());
            System.out.println();

            // Test case for zero denominator
            Fraction zeroDenom = new Fraction(1, 0);
            System.out.println("Expected for zero denominator: Error message + 1/1");
            System.out.println("Actual: " + zeroDenom.toString());
            System.out.println();

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
