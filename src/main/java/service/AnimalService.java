package service;

import db.dao.AnimalDAO;
import dataobj.AnimalObj;
import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    private final AnimalDAO animalDAO;

    public AnimalService(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    public void createAnimal(String name, int age, String type) throws SQLException {
        AnimalObj animal = new AnimalObj(name, age, type);
        animalDAO.addAnimal(animal);
        System.out.println("Животное добавлено в базу: " + name);
    }

    public void listAllAnimals() throws SQLException {
        List<AnimalObj> animals = animalDAO.getAllAnimals();
        System.out.println("Список всех животных:");
        for (AnimalObj animal : animals) {
            System.out.println(animal);
        }
    }

    public void updateAnimal(int id, String newName, int newAge, String newType) throws SQLException {
        animalDAO.updateAnimal(id, newName, newAge, newType);
        System.out.println("Данные животного обновлены!");
    }

    public void listAnimalsByType(String type) throws SQLException {
        List<AnimalObj> animals = animalDAO.getAnimalsByType(type);
        System.out.println("Список животных типа " + type + ":");
        for (AnimalObj animal : animals) {
            System.out.println(animal);
        }
    }
}
