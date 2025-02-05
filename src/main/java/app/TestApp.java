package app;

import db.dao.AnimalDAO;
import db.dbconnector.MysqlDbConnector;
import service.AnimalService;
import exceptions.InputHandler;
import java.sql.SQLException;
import java.util.Scanner;

public class TestApp {
    public static void main(String[] args) {
        try {
            MysqlDbConnector dbConnector = new MysqlDbConnector();
            AnimalDAO animalDAO = new AnimalDAO(dbConnector);
            AnimalService animalService = new AnimalService(animalDAO);
            Scanner scanner = new Scanner(System.in);
            InputHandler inputHandler = new InputHandler(scanner);
            while (true) {
                System.out.println("Выберите действие:\n1. Добавить животное\n2. Показать всех животных\n3. Изменить животное\n4. Найти по типу\n0. Выход");
                int choice = inputHandler.getPositiveInt("Выберите действие (число от 0 до 4):");

                switch (choice) {

                    case 1:
                        String name = inputHandler.getString("Имя животного:");
                        int age = inputHandler.getPositiveInt("Введите возраст (только положительное число):");
                        String type = inputHandler.getString("Тип (кот, собака и т.д.):");
                        animalService.createAnimal(name, age, type);
                        break;

                    case 2:
                        animalService.listAllAnimals();
                        break;

                    case 3:
                        System.out.print("ID животного: ");
                        int id = inputHandler.getPositiveInt("Введите ID животного (положительное число):");

                        System.out.print("Новое имя: ");
                        String newName = scanner.nextLine();

                        int newAge = inputHandler.getPositiveInt("Введите новый возраст (только положительное число):");

                        System.out.print("Новый тип: ");
                        String newType = scanner.nextLine();

                        animalService.updateAnimal(id, newName, newAge, newType);
                        break;

                    case 4:
                        System.out.print("Введите тип животного: ");
                        String filterType = scanner.nextLine();
                        animalService.listAnimalsByType(filterType);
                        break;

                    case 0:
                        System.out.println("Выход...");
                        dbConnector.close();
                        return;

                    default:
                        System.out.println("Неверный выбор!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
