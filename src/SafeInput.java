import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retValue = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                validInput = true;
            } else {
                System.out.println("You did not enter an integer.");
                pipe.next();
            }
            pipe.nextLine();
        }
        return retValue;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double value = 0;
        boolean done = false;
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("You entered an incorrect input. Please enter a valid double.");
                pipe.next();
            }
        } while (!done);
        return value;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retValue = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                if (retValue >= low && retValue <= high) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("You did not enter an integer.");
                pipe.next();
            }
            pipe.nextLine();
        }
        return retValue;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double doubleVal = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                doubleVal = pipe.nextDouble();
                if (doubleVal >= low && doubleVal <= high) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a double between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("You did not enter a double .");
                pipe.next();
            }
            pipe.nextLine();
        }
        return doubleVal;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String yesOrNo;
        boolean result = false;
        boolean done = false;

        do {
            System.out.print(prompt + "(Y/N):");
            yesOrNo = pipe.nextLine();
            if (yesOrNo.equalsIgnoreCase("Y")) {
                result = true;
                done = true;
            } else if (yesOrNo.equalsIgnoreCase("N")) {
                result = false;
                done = true;

            } else {

                System.out.println("You did not enter Y or N.");

            }
        }
        while (!done);

        return result;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String userInput = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + ": ");
            userInput = pipe.nextLine();

            if (userInput.matches(regEx)) {
                validInput = true;
            } else {
                System.out.println("Invalid input. ");
            }
        }

        return userInput;
    }

    public static String getZeroLengthString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();

        } while (retString.length() == 0);

        return retString;
    }

    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        int msgWidth = msg.length() + 6;
        int space = (WIDTH - msgWidth) / 2;

        // Print top border using a loop
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();


        System.out.print("***");
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println("***");

        // Print bottom border using a loop
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}