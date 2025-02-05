package db.dbconnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {

    void execute(String sqlRequest) throws SQLException;

    ResultSet executeQuery(String sqlRequest) throws SQLException;

    void close() throws SQLException;

    Connection getConnection() throws SQLException;
}
