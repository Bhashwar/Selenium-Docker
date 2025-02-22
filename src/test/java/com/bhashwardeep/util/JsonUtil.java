// This is the package declaration. It groups the class into a folder structure called "com.bhashwardeep.util".
package com.bhashwardeep.util;

// These are imports. They bring in tools and code from other libraries so we can use them here.
import com.bhashwardeep.tests.vendorportal.model.VendorPortalTestData; // A custom class for test data.
import com.fasterxml.jackson.databind.ObjectMapper; // A tool to convert JSON data into Java objects.
import org.slf4j.Logger; // A tool for logging messages (like printing to the console but better).
import org.slf4j.LoggerFactory; // Helps create a logger instance.

import java.io.InputStream; // A tool to read data from files or resources.

// This is the main class called JsonUtil. It helps work with JSON data.
public class JsonUtil {

    // This is a logger. It helps log messages (like errors or info) for debugging.
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    // This is an ObjectMapper. It converts JSON data into Java objects and vice versa.
    private static final ObjectMapper mapper = new ObjectMapper();

    // This is a method called getTestData. It reads JSON data from a file and turns it into a Java object.
    public static <T> T getTestData(String path, Class<T> type) {
        // Try to read the JSON file from the given path.
        try (InputStream stream = ResourceLoader.getResource(path)) {
            // Convert the JSON data into a VendorPortalTestData object using the ObjectMapper.
            return mapper.readValue(stream, type);
        } catch (Exception e) {
            // If something goes wrong, log an error message with the file path and the exception details.
            log.error("Unable to read test data {}", path, e);
        }
        // If there's an error, return null (which means "nothing").
        return null;
    }
}
/*
Explanation of Key Concepts:
Package: A folder structure to organize code. Think of it like a drawer in a filing cabinet.

Import: Bringing in tools or code from other places so you can use them in your program.

Class: A blueprint for creating objects. It contains methods (functions) and variables.

Logger: A tool to record messages, like errors or information, to help debug or track what’s happening in the program.

ObjectMapper: A tool that converts JSON (a text format for data) into Java objects (code-friendly data).

Method: A block of code that performs a specific task. Here, getTestData reads JSON and converts it.

Try-Catch: A way to handle errors. The program tries to do something, and if it fails, it "catches" the error and handles it.

InputStream: A tool to read data from a file or resource.

Return: Sends a result back from a method. If something goes wrong, it returns null (nothing).

JSON: A simple text format for storing and exchanging data, like a list of settings or test data.

This code is used to read a JSON file, convert it into a Java object, and handle any errors that might occur during the process.
 */