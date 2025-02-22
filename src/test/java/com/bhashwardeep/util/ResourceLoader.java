package com.bhashwardeep.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoader {
    /*
     * A simple utility class for reading files (resources).
     * It first checks if the resource is available in the classpath.
     * If not found in the classpath, it then checks the file system.
     */

    // Initialize a logger for the ResourceLoader class using SLF4J.
    private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

    /*
     * Retrieves a resource as an InputStream.
     *
     * @param path The path of the resource to be loaded.
     * @return An InputStream for the resource.
     * @throws IOException If there is an error reading the resource.
     */
    public static InputStream getResource(String path) throws IOException {
        // Log the action of reading a resource, along with its path.
        log.info("Reading resource from location {}", path);

        // Attempt to load the resource from the classpath.
        // getResourceAsStream returns null if the resource is not found.
        InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);

        // If the resource is found in the classpath, return the InputStream.
        if (Objects.nonNull(stream)) {
            return stream;
        }

        // If the resource is not found in the classpath, load it from the file system.
        // This uses the given path to locate and open the file.
        return Files.newInputStream(Path.of(path));
    }
}
