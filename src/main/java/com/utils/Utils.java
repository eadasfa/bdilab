package com.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class Utils {
    public static String getJSON(String fileName){
        String jsonStr = "";
        try {
           // File jsonFile = new File(fileName);
            File jsonFile = ResourceUtils.getFile("classpath:JSON/"+fileName);
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
}
