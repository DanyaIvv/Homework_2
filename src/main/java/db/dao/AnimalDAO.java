package db.dao;

import db.dbconnector.MysqlDbConnector;
import dataobj.AnimalObj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private final MysqlDbConnector dbConnector;

    public AnimalDAO(MysqlDbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    public void addAnimal(AnimalObj animal) throws SQLException {
        String sql = "INSERT INTO animals (name, age, type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(sql)) {
            stmt.setString(1, animal.getName());
            stmt.setInt(2, animal.getAge());
            stmt.setString(3, animal.getType());
            stmt.executeUpdate();
        }
    }


    public List<AnimalObj> getAllAnimals() throws SQLException {
        List<AnimalObj> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals";
        try (Statement stmt = dbConnector.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                animals.add(new AnimalObj(rs.getString("name"), rs.getInt("age"), rs.getString("type")));
            }
        }
        return animals;
    }


    public void updateAnimal(int id, String newName, int newAge, String newType) throws SQLException {
        String sql = "UPDATE animals SET name = ?, age = ?, type = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, newAge);
            stmt.setString(3, newType);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }


    public List<AnimalObj> getAnimalsByType(String type) throws SQLException {
        List<AnimalObj> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE type = ?";
        try (PreparedStatement stmt = dbConnector.getConnection().prepareStatement(sql)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                animals.add(new AnimalObj(rs.getString("name"), rs.getInt("age"), rs.getString("type")));
            }
        }
        return animals;
    }
}