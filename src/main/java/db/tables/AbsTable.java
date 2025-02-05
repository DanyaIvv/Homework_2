package db.tables;

import animals.AbsAnimal;
import db.dbconnector.IDBConnector;
import factory.FactoryAnimal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsTable {

    private String tableName;

    private IDBConnector idbConnector;

    public AbsTable(String tableName, IDBConnector idbConnector) {
        this.tableName = tableName;
        this.idbConnector = idbConnector;
    }


    public void createTable() {

        try {

            this.idbConnector.execute(String.format("create table %s", tableName));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete() {
        try {
            this.idbConnector.execute(String.format("drop table %s", tableName));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet list(String... predicates) {

        String requestPredicates = predicates.length == 0 ? "" : String.format("where %s", String.join(" and ", predicates));


        try {
            return idbConnector.executeQuery(String.format("select * from %s %s", tableName, requestPredicates));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
