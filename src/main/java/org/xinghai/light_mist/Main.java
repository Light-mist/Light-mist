package org.xinghai.light_mist;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.xinghai.light_mist.ReadFile;

public class Main {
    public static void main(String[] args) {
        String jsonValue;
        jsonValue = ReadFile.get_file("config/data.json");
        if (ReadFile.code == 1){
            System.out.println("出现错误！来自文件读取，错误内容："+jsonValue);
        }

        List<JSONObject> jsonArray = JSON.parseArray(jsonValue, JSONObject.class);
        List<Integer> names = new ArrayList<>();
        try {
            jsonArray = mapper.readValue(jsonValue, List.class);
            for (Map<String, Object> obj : jsonArray) {
                System.out.println("启动: " + obj.getString("name"));
                String runvalue = obj.getString("command");
                String dirPath = obj.getString("dir");
                File rundir = new File(dirPath);
                names.add(Integer.valueOf(obj.getString("name")));
                Process process = Runtime.getRuntime().exec(runvalue, null, rundir);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("已成功执行所有指令，列表为：" + names);
    }
}