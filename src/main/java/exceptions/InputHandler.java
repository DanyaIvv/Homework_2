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
                scanner.nextLine();
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
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Ошибка! Поле не может быть пустым. Попробуйте еще раз.");
        }
    }


    public String getValidEnum(String prompt, String[] validValues) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.next().trim().toUpperCase();
            for (String value : validValues) {
                if (value.equals(input)) {
                    return input;
                }
            }
            System.out.println("Ошибка! Введите одно из следующих значений: " + String.join(", ", validValues));
        }
    }
}
