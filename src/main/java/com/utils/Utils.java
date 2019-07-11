package com.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {

    //从文件中读取json文件
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
//        JSONObject jsonObject = JSONObject.fromObject(getJSON("demo.json"));
//        System.out.println(jsonObject.toString());
//        Iterator it = jsonObject.keySet().iterator();
////        while(it.hasNext()){
////            Object key = it.next();
////            Object value = jsonObject.get(key);
////            System.out.println(value+"\n"+value.getClass().getName());
////        }
//        parseJson(jsonObject,"",new StringBuilder(),false);
        System.out.println(Utils.getJSON("pod4MergeJsonPatch.json"));
    }
    //将返回的json串变为缩进形式
    public static String parseJson(String jsonStr){

        return parseJson(JSONObject.fromObject(jsonStr),"    ",new StringBuilder(""),false);
    }
    public static String parseJson(JSONObject object,String prex,StringBuilder str,boolean isArray){
//        System.out.print((isArray?prex+"[":"{")+"\n");
        str.append((isArray?prex+"[":"{")+"\n");
        String nowPrex = prex+"    ";
        Iterator it = object.keySet().iterator();
        while(it.hasNext()){
            Object key = it.next();
            Object value = object.get(key);
            if(value.getClass().getName().equals("net.sf.json.JSONObject")){

                str.append(nowPrex+key+":");
//                System.out.print(nowPrex+key+":");
                parseJson((JSONObject) value,nowPrex,str,false);
            }else if(value.getClass().getName().equals("net.sf.json.JSONArray")){
                str.append(nowPrex+key+":[\n");
//                System.out.print(nowPrex+key+":[\n");
                JSONArray v = (JSONArray)value;
                for(Object o :v){
                    parseJson((JSONObject)o,nowPrex,str,true);
                }
            }else {
//                System.out.print(nowPrex + key + ":" + value+",\n");
                str.append(nowPrex + key +":" + value + ",\n");
            }
        }
//        str.delete(str.length()-1,str.length());
//        System.out.print(prex+(isArray?"]":"}")+",\n");
        str.append(prex+(isArray?"]":"}")+",\n");
        return str.toString();
    }
    // 从返回的格式传中中取出所有pod
    public static List<String> getPodsFromJson(String jsonStr){
        JSONArray items = (JSONArray)JSONObject.fromObject(jsonStr).get("items");
        List<String> pods = new ArrayList<>();
        if(items == null || items.size()==0)
            return pods;
        for (Object o : items){
            JSONObject item = (JSONObject)o;
            pods.add(((JSONObject)(item.get("metadata"))).get("name")+"");
        }
        return pods;
    }
}
