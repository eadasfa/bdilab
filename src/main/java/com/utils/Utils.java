package com.utils;


import org.springframework.util.ResourceUtils;

import java.io.*;
public class Utils {
    public static String getJSON(String fileName){
        String jsonStr = "";
        try {
//            File jsonFile = new File(fileName);
            //ResourceUtils.getFile() 方法可以获取到resources 下的文件，可能在linux系统下无效
            File jsonFile = ResourceUtils.getFile("classpath:static/json/"+fileName);

            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String jsonName = "namespace.json";
        System.out.println(getJSON(jsonName));
    }
}
