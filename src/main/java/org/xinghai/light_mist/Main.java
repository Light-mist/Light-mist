package org.xinghai.light_mist;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jsonValue;
        try (BufferedReader reader = new BufferedReader(new FileReader("config/data.json"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            jsonValue = sb.toString();
        } catch (IOException e) {
            System.err.println("读取文件时出现错误：" + e.getMessage());
            return;
        }

        List<JSONObject> jsonArray = JSON.parseArray(jsonValue, JSONObject.class);
        List<Integer> names = new ArrayList<>();

        for (JSONObject obj : jsonArray) {
            System.out.println("启动: " + obj.getString("name"));
            String runvalue = obj.getString("command");
            String dirPath = obj.getString("dir");
            ProcessBuilder pb = new ProcessBuilder(runvalue.split(" "));
            pb.directory(new File(dirPath));
            try {
                Process process = pb.start();
                names.add(obj.getIntValue("name"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("已成功执行所有指令，列表为：" + names);
    }
}