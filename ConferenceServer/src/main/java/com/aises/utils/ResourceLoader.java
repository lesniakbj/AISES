package com.aises.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Brendan on 8/1/2016.
 */
public class ResourceLoader {
    public static String loadFile(String fileName) {
        InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(fileName);
        StringBuilder builder = new StringBuilder();
        try(Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        }

        return builder.toString();
    }

    public static Properties loadProperties(String s) {
        InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("db.properties");
        Properties ret = new Properties();
        try {
            ret.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
