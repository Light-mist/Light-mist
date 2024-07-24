package org.xinghai.light_mist;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static JSONArray jsonArray = null;
    public static void main(String[] args) {
        String file = "config/light_mist.json";
        List<String> names = new ArrayList<>();
        List<Process> runs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String jsonString;
            StringBuilder jsonContent = new StringBuilder();
            // 逐行读取文件内容
            while ((jsonString = reader.readLine()) != null) {
                jsonContent.append(jsonString);
            }
            jsonArray = JSON.parseArray(jsonContent.toString());

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println("启动: " + obj.getString("name"));
                String runvalue = obj.getString("command");
                String dirPath = obj.getString("dir");
                File rundir = new File(dirPath);
                names.add(obj.getString("name"));
                runs.add(RunCommand.run(rundir, runvalue));
                if (!RunCommand.run_yes){
                    System.out.println("抱歉！暂不支持此系统！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("已成功执行所有指令，列表为："+names);
        String get_input = Input.input();
        if (get_input.equals("exit")) {
            System.err.println("正在退出中...");
            for (Process p : runs) {
                p.destroy();
            }
        } else if (get_input.startsWith("run")) {
            String[] parts = get_input.split(" ");
            if (parts.length >= 3) {
                try {
                    String dirPath = parts[1];
                    String command = parts[2];
                    File rundir = new File(dirPath);
                    command = command+" > log.txt";
                    Process run_return = RunCommand.run(rundir, command);
                    try {
                        int exitVal = run_return.waitFor();
                        System.out.println(exitVal == 0 ? "执行成功！" : "执行失败");
                        String s = Files.readString(Paths.get(rundir+"log.txt"));
                        System.out.println(s);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.err.println("错误：指令错误!");
            }
        }
    }
}