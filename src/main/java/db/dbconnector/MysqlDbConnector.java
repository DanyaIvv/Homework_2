package db.dbconnector;

import db.dbsettings.DbPropertiesReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MysqlDbConnector implements IDBConnector  {
    private Map<String, String> settings;
    private Connection connection;

    public MysqlDbConnector() {
        settings = new DbPropertiesReader().read();
        try {
            connection = DriverManager.getConnection(
                    settings.get("url"), settings.get("username"), settings.get("password")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void execute(String sqlRequest) throws SQLException {

    }

    @Override
    public ResultSet executeQuery(String sqlRequest) throws SQLException {
        return null;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
