package framework.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationUtils {

    public static Properties properties = new Properties();

    @SneakyThrows
    public static Properties loadProperties() {
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "configuration.properties"));
        return properties;
    }
}
