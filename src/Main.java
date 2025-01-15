import animals.AbsAnimal;
import data.ColorData;
import data.CommandsData;
import data.AnimalTypesData;
import exceptions.InputHandler;
import factory.FactoryAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        List<AbsAnimal> animals = new ArrayList<>();
        CommandsData[] commandsData = CommandsData.values();

        List<String> commandsNames = new ArrayList<>();
        for (CommandsData commandData : commandsData) {
            commandsNames.add(commandData.name().toLowerCase());
        }

        while (true) {
            System.out.printf("Введите одну из команд %s:\n", String.join("/", commandsNames));
            String commandStrUser = scanner.next().trim().toUpperCase();


            boolean isCommandExist = false;
            for (CommandsData command : commandsData) {
                if (command.name().equals(commandStrUser)) {
                    isCommandExist = true;
                    break;
                }
            }
            if (!isCommandExist) {
                System.out.println("Вы ввели неверную команду! Повторите ввод.");
                continue;
            }

            switch (CommandsData.valueOf(commandStrUser)) {
                case ADD: {
                    System.out.println("Введите тип животного (CAT, DOG, DUCK):");
                    String animalTypeInput = scanner.next().trim().toUpperCase();

                    try {
                        AnimalTypesData animalType = AnimalTypesData.valueOf(animalTypeInput);
                        FactoryAnimal factoryAnimal = new FactoryAnimal();
                        AbsAnimal animal = factoryAnimal.create(animalType);

                        String name = inputHandler.getString("Введите имя:");
                        int age = inputHandler.getPositiveInt("Введите возраст:");
                        int weight = inputHandler.getPositiveInt("Введите вес:");

                        System.out.println("Введите цвет (RED, BLUE, GREEN, BROWN):");
                        String colorName = scanner.next().trim().toUpperCase();

                        boolean isValidColor = false;
                        for (ColorData color : ColorData.values()) {
                            if (color.name().equals(colorName)) {
                                animal.setColor(color);
                                isValidColor = true;
                                break;
                            }
                        }

                        if (!isValidColor) {
                            System.out.println("Некорректный цвет! Используйте один из доступных цветов.");
                            continue;
                        }

                        animal.setName(name);
                        animal.setAge(age);
                        animal.setWeight(weight);

                        animals.add(animal);
                        animal.say();

                    } catch (IllegalArgumentException e) {
                        System.out.println("Некорректный тип животного! Попробуйте снова.");
                        continue;
                    }
                    break;
                }
                case LIST: {
                    if (animals.isEmpty()) {
                        System.out.println("Список животных пуст.");
                    } else {
                        animals.forEach(animal -> System.out.println(animal.toString()));
                    }
                    break;
                }
                case EXIT: {
                    System.out.println("Выход из программы. До свидания!");
                    System.exit(0);
                }
            }
        }
    }
}