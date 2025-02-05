package db.dbsettings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbPropertiesReader implements IProperties {

    @Override
    public Map<String, String> read() {
        Properties properties = new Properties();
        Map<String, String> settings = new HashMap<>();

        try {
            String path = System.getProperty("user.dir") + "/src/main/resources/sql.properties";
            properties.load(new FileInputStream(path));

            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                settings.put((String) entry.getKey(), (String) entry.getValue());
            }

            return settings;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения sql.properties: " + e.getMessage(), e);
        }
    }
}
