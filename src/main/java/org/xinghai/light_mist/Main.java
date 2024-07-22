package org.xinghai.light_mist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.xinghai.light_mist.RunCommand;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

public class Main {
    public static void main(String[] args) {
        String file = "config/data.json";
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String jsonString;
            StringBuilder jsonContent = new StringBuilder();
            // 逐行读取文件内容
            while ((jsonString = reader.readLine()) != null) {
                jsonContent.append(jsonString);
            }
            JSONArray jsonArray = JSON.parseArray(jsonContent.toString());

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println("启动: " + obj.getString("name"));
                String runvalue = obj.getString("command");
                String dirPath = obj.getString("dir");
                File rundir = new File(dirPath);
                names.add(obj.getString("name"));
                RunCommand.run(rundir, runvalue);
                if (!RunCommand.run_yes){
                    System.out.println("抱歉！暂不支持此系统！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已成功执行所有指令，列表为："+names);
    }
}