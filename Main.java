package bullscows;

import java.util.*;

public class Main {
    static Character[] tries;
    static Character[] secretNumber;

    static int numberOfBulls;
    static int numberOfCows;

    static int numberOfDigits;
    static int numberOfPossibleSymbols;
    static int turn = 0;

    static boolean gameIsWon;

    public static void main(String[] args) {
        secretNumber = generateSecretCode();
        System.out.println("Okay, let's start a game!");

        while (!gameIsWon) {
            getInput();
            gradeNumberOfBullsAndCows();
            gradeOutput();
        }
    }

    private static Character[] generateSecretCode() {
        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        try {
            numberOfDigits = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code:");
            numberOfPossibleSymbols = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Please type in a valid number!");
            System.exit(0);
        }

        if (numberOfPossibleSymbols < numberOfDigits || numberOfDigits == 0) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n", numberOfDigits, numberOfPossibleSymbols);
            System.exit(0);
        }
        if (numberOfPossibleSymbols > 36) {
            System.out.println("Error: can't generate a secret number with a length of more than 36 because there aren't enough unique digits.");
            System.exit(0);
        }

        //There's a maximum of 36 different chars
        Character[] charArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        System.out.print("The secret code is prepared: ");
        for (int i = 0; i < numberOfDigits; i++) {
            System.out.print("*");
        }
        if (numberOfPossibleSymbols <= 10) {
            System.out.printf("(0-%d)%n", numberOfPossibleSymbols < 10 ? numberOfPossibleSymbols : 9);
        }
        if (numberOfPossibleSymbols > 10) {
            System.out.printf("(0-9, a-%c)%n", charArray[numberOfPossibleSymbols - 1]);
        }

        if (numberOfDigits <= 36) {
            List<Character> characterList = Arrays.asList(Arrays.copyOfRange(charArray, 0, numberOfPossibleSymbols));
            Collections.shuffle(characterList);
            characterList.toArray(charArray);
        }
        return Arrays.copyOfRange(charArray, 0, numberOfDigits);
    }

    private static void gradeOutput() {
        System.out.print("Grade: ");
        if (numberOfBulls > 0 && numberOfCows > 0) {
            System.out.printf("%d bull(s) and %d cow(s). \n", numberOfBulls, numberOfCows);
        }
        if (numberOfBulls > 0 && numberOfCows == 0) {
            System.out.printf("%d bull(s). \n", numberOfBulls);
        }
        if (numberOfBulls == 0 && numberOfCows > 0) {
            System.out.printf("%d cow(s). \n", numberOfCows);
        }
        if (numberOfBulls == 0 && numberOfCows == 0) {
            System.out.print("None. \n");
        }
        if (numberOfBulls == numberOfDigits) {
            System.out.println("Congratulations! You guessed the secret code.\n");
            gameIsWon = true;
        }
    }

    private static void gradeNumberOfBullsAndCows() {
        numberOfBulls = 0;
        numberOfCows = 0;
        for (int i = 0; i < numberOfDigits; i++) {
            if (tries[i] == secretNumber[i]) {
                numberOfBulls++;
            } else {
                for (Character character : secretNumber) {
                    if (tries[i] == character) {
                        numberOfCows++;
                    }
                }
            }
        }
    }

    private static void getInput() {
        turn++;
        System.out.printf("Turn %d:%n", turn);
        tries = new Character[numberOfDigits];
        String input;
        Scanner scan = new Scanner(System.in);
        input = scan.next();
        System.out.println("Input: " + input);
        for (int i = 0; i < numberOfDigits; i++) {
            tries[i] = input.charAt(i);
        }
    }
}
