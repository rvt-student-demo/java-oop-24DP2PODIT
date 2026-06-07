package rvt;

import java.util.Scanner;

public class DivisionPractice {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the numerator: ");
            String numeratorText = input.nextLine();

            if (numeratorText.length() == 0) {
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                System.out.println();
                continue;
            }

            char firstChar = numeratorText.charAt(0);

            if (firstChar == 'q' || firstChar == 'Q') {
                break;
            }

            try {
                int numerator = Integer.parseInt(numeratorText);

                System.out.print("Enter the divisor: ");
                String divisorText = input.nextLine();

                int divisor = Integer.parseInt(divisorText);

                if (divisor == 0) {
                    System.out.println("You can't divide " + numerator + " by " + divisor);
                } else {
                    int result = numerator / divisor;
                    System.out.println(numerator + " / " + divisor + " is " + result);
                }

            } catch (NumberFormatException e) {
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
            }

            System.out.println();
        }

        input.close();
    }
}