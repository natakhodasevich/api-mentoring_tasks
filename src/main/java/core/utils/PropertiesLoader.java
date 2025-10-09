package core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_FILE = "api.properties";
    private static final Properties properties = new Properties();
    private static PropertiesLoader instance = null;

    public PropertiesLoader() {
        try {
            properties.load(this.getClass()
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_FILE));
        } catch (IOException ex) {
          //  throw new SuvodaApiFrameworkException("Something went wrong while trying to load properties file. Error: ", ex);
        }
    }

    public static synchronized PropertiesLoader getInstance() {
        if (instance == null) instance = new PropertiesLoader();
        return instance;
    }

    public static String getPropertyByKey(String key) {
        String propertyValue = getInstance().getProperties().getProperty(key);
        var sysPropValue = System.getProperty(key);
        if (sysPropValue != null && !sysPropValue.isBlank())
            propertyValue = sysPropValue;
        return propertyValue;
    }

    public static void setProperty(String key, String value) {
        getInstance().getProperties().setProperty(key, value);
    }

    public Properties setProperties() {
        final InputStream streamResource = getClass().getClassLoader()
                .getResourceAsStream(CONFIG_FILE);
        try {
            properties.load(streamResource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public Properties setProperties(String propsPath) {
        final InputStream streamResource = getClass().getClassLoader()
                .getResourceAsStream(propsPath);
        try {
            properties.load(streamResource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public Properties getProperties() {
        return properties;
    }
}

