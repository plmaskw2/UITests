package framework.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationUtils {

    public static Properties properties = new Properties();
    public  static boolean isRemote = getProperty("driverType").equals("REMOTE");
    public static boolean isSelenoid = getProperty("selenoid").equals("true");
    public static final String DRIVER_TYPE = getProperty("driverType");
    public static final String SELENOID_HUB = getProperty("selenoidHub");
    public static final String GRID_HUB = getProperty("gridHub");
    public static boolean isEnableVideo = getProperty("enableVideo").equals("true");

    @SneakyThrows
    public static Properties loadProperties() {
        properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "configuration.properties"));
        return properties;
    }

    private static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
