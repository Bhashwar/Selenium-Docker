package com.bhashwardeep.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Handles application configuration settings.
 * Loads default properties from file and allows system property overrides.
 * Example: Database credentials, feature flags, environment settings
 */
public class Config {

    // Logger to track configuration loading process and issues
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    // Default configuration file location in project resources
    private static final String DEFAULT_PROPERTIES = "config/default.properties";

    // Storage for all configuration key-value pairs we'll use
    private static Properties properties;

    /**
     * Main setup method - should be called once at application startup.
     * 1. Loads default properties from file
     * 2. Lets system properties (-D flags in command line) override defaults
     * 3. Logs final configuration for verification
     */
    public static void initialize() {
        // Load base settings from default.properties file
        properties = loadProperties();

        // Check if any system settings (from command line) should replace defaults
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                log.debug("Overriding property {} with system value", key);
                properties.setProperty(key, System.getProperty(key));
            }
        }

        // Show all loaded settings in logs for debugging purposes
        log.info("Loaded configuration settings:");
        log.info("-----------------------");
        for (String key : properties.stringPropertyNames()) {
            log.info("{} = {}", key, properties.getProperty(key));
        }
        log.info("-----------------------");
    }

    /**
     * Get a configuration value by its key
     * @param key The name of the setting to retrieve (e.g., "database.url")
     * @return The configured value or null if not found
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Internal helper to read properties file from resources
     * @return Loaded properties or empty set if file not found
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        // Try to load file using classpath resources (works in JAR files)
        try (InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Failed to load configuration file: {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}