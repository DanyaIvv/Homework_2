import animals.AbsAnimal;
import data.ColorData;
import data.CommandsData;
import data.AnimalTypesData;
import db.dbconnector.IDBConnector;
import db.dbconnector.MysqlDbConnector;
import exceptions.InputHandler;
import factory.FactoryAnimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        List<AbsAnimal> animals = new ArrayList<>();
        CommandsData[] commandsData = CommandsData.values();

        IDBConnector idbConnector = new MysqlDbConnector();


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

            try{
            switch (CommandsData.valueOf(commandStrUser)) {
                case ADD: {

                    String animalTypeInput = inputHandler.getValidEnum("Введите тип животного (CAT, DOG, DUCK):", new String[]{"CAT", "DOG", "DUCK"});
                    AnimalTypesData animalType = AnimalTypesData.valueOf(animalTypeInput);
                    FactoryAnimal factoryAnimal = new FactoryAnimal();
                    AbsAnimal animal = factoryAnimal.create(animalType);

                    String name = inputHandler.getString("Введите имя:");

                    int age = inputHandler.getPositiveInt("Введите возраст (положительное число):");

                    int weight = inputHandler.getPositiveInt("Введите вес (положительное число):");

                    String colorName = inputHandler.getValidEnum(
                            "Введите цвет (RED, BLUE, GREEN, BROWN):",
                            new String[]{"RED", "BLUE", "GREEN", "BROWN"});
                    ColorData color = ColorData.valueOf(colorName);


                    animal.setName(name);
                    animal.setAge(age);
                    animal.setWeight(weight);
                    animal.setColor(color);

                    animals.add(animal);
                    animal.say();
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

        } finally {
                idbConnector.close();
            }
        }
    }
}
