import java.util.Random;
public class Fraction {
    private int numerator, denominator;

    public Fraction() {
        numerator = 0;
        denominator = 1;
    } //Create Fraction Instance that initialize the numerator to 0, denominator to 1

    public Fraction(int n, int d) {
        numerator = n;
        denominator = d;
        checkIfDenomis0();
    } //Create Fraction Instance that initialize the numerator and denominator to inputs

    public Fraction(String a) {
        int indexOfSlash = a.indexOf('/');
        numerator = Integer.parseInt(a.substring(0, indexOfSlash));
        denominator= Integer.parseInt(a.substring(indexOfSlash + 1));
        checkIfDenomis0();
    } //Create Fraction Instance that initialize the numerator and denominator to value according to the input string

    public void checkIfDenomis0() {
        if (denominator == 0) {
            System.out.println("Error: Denominator cannot be 0.");
            denominator = 1;
        }
    } //Check if the denominator is 0, if yes, replace with 1

    public Fraction(Fraction myFrac){
        numerator = myFrac.numerator;
        denominator = myFrac.denominator;
    } //Create Fraction Instance that copies a previous fraction

    public static Fraction generateRandomFrac() {
        Random random = new Random();
        int denominator = random.nextInt(8) + 2; // Generates a number between 2 and 9
        int numerator = random.nextInt(denominator - 1) + 1; // Generates a number between 1 and denominator - 1
        Fraction result = new Fraction(numerator, denominator);
        result.reduce();
        return result;
    }

    public int findGCF(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    } //Find the GCF of two integer

    public int getNum() {
        return numerator;
    }

    public void setNum(int numerator) {
        this.numerator = numerator;
    }

    public int getDenom() {
        return denominator;
    }

    public void setDenom(int denominator) {
        this.denominator = denominator;
    }

    public String toString(){
        return numerator + "/" + denominator;
    }

    public double toDouble(){
        return ((double)numerator)/denominator;
    }

    public void reduce(){
        int gcf = findGCF(numerator, denominator);
        numerator = numerator / gcf;
        denominator = denominator / gcf;
    }

    public static Fraction multiply(Fraction a, Fraction b){
        int tempNum = a.numerator * b.numerator;
        int tempDenom = a.denominator * b.denominator;
        Fraction tempFrac = new Fraction(tempNum,tempDenom);
        return tempFrac;
    }

    public static Fraction divide(Fraction a, Fraction b){
        int tempNum = a.numerator * b.denominator;
        int tempDenom = a.denominator * b.numerator;
        Fraction tempFrac = new Fraction(tempNum,tempDenom);
        return tempFrac;
    }

    public static Fraction add(Fraction a, Fraction b){
        int tempNum = a.numerator * b.denominator + a.denominator* b.numerator;
        int tempDenom = a.denominator * b.denominator;
        Fraction tempFrac = new Fraction(tempNum,tempDenom);
        tempFrac.reduce();
        return tempFrac;
    }

    public static Fraction subtract(Fraction a, Fraction b){
        int tempNum = a.numerator * b.denominator - a.denominator* b.numerator;
        int tempDenom = a.denominator * b.denominator;
        Fraction tempFrac = new Fraction(tempNum,tempDenom);
        tempFrac.reduce();
        return tempFrac;
    }

    public boolean equals(Fraction input) {
        // Reduce both fractions to their simplest forms
        this.reduce();
        input.reduce();
        return this.numerator == input.numerator && this.denominator == input.denominator;
    }
}
