package org.example.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {

    private static final Properties properties = new Properties();

    static {
        // Load the properties file when the class is loaded
        try (InputStream input = ConfigProvider.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load configuration properties.");
        }
    }

    // Method to retrieve property values by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Specific method for base URL to avoid hardcoding keys throughout the code
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    // Example method to get an integer property, e.g., timeout value
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }
}

