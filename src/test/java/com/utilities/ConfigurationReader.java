package com.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {

        try {
            String path = "config.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Retrieves the value of the specified property from the loaded properties.
     * This method is used to access configuration values stored in a properties file.
     *
     * @param keyName The name of the property whose value is to be retrieved.
     * @return The value of the specified property or null if the property is not found.
     */
    public static String getProperty(String keyName) {
        return properties.getProperty(keyName);
    }

}

