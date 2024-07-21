package org.xinghai.light_mist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    static int code = 1;
    public static String get_file(String filePath) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            code = 0;
        } catch (IOException e) {
            result = new StringBuilder(1);
        }
        return result.toString();
    }
}