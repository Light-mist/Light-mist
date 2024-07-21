package org.xinghai.light_mist;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import static org.xinghai.light_mist.ReadFile.get_file;

public class Main {
    public static void main(String[] args) {
        String jsonInput;
        jsonInput = get_file("config/data.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> jsonArray;
            jsonArray = mapper.readValue(jsonInput, List.class);
            for (Map<String, Object> obj : jsonArray) {
                System.out.println("启动: " + obj.get("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}