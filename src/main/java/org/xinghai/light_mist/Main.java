package org.xinghai.light_mist;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.xinghai.light_mist.ReadFile;

public class Main {
    public static void main(String[] args) {
        String jsonValue;
        jsonValue = ReadFile.get_file("config/data.json");
        if (ReadFile.code == 1){
            System.out.println("出现错误！来自文件读取，错误内容："+jsonValue);
        }
        List<Integer> names = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> jsonArray;
            jsonArray = mapper.readValue(jsonValue, List.class);
            for (Map<String, Object> obj : jsonArray) {
                System.out.println("启动: " + obj.get("name"));
                String runvalue = (String) obj.get("command");
                String dirPath = (String) obj.get("dir");
                File rundir = new File(dirPath);
                names.add(Integer.valueOf((String) obj.get("name")));
                Process process = Runtime.getRuntime().exec(runvalue, null, rundir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已成功执行所有指令，列表为："+names);
    }
}
