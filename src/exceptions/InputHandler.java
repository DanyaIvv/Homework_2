package exceptions;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getPositiveInt(String prompt) {
        int value;
        while (true) {
            System.out.println(prompt);
            try {
                value = scanner.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Ошибка! Введите положительное число.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка! Введите корректное число.");
                scanner.next();
            }
        }
    }


    public String getString(String prompt) {
        System.out.println(prompt);
        return scanner.next().trim();
    }
}