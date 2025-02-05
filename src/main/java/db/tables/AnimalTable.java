package db.tables;

import data.AnimalTypesData;
import dataobj.AnimalObj;
import db.dbconnector.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable {

    public AnimalTable(IDBConnector idbConnector) {
        super("animals", idbConnector);
    }

    public List<AnimalObj> listAnimals(String... predicate) {
        List<AnimalObj> animals = new ArrayList<>();
        ResultSet resultSet = this.list(predicate);

        try {
            while (resultSet.next()) {
                AnimalObj animalObj = new AnimalObj(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("type")
                );
                animals.add(animalObj);
            }
            return animals;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    }
