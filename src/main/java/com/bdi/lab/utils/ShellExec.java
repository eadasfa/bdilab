package com.bdi.lab.utils;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class ShellExec {
    public static void main(String[] args) {
        String ip = "192.168.0.153";
        String username = "root";
        String passwd = "123456";
        String cmd = "kubectl get pods -n k8s-test ";
        StringBuilder result = execute(username,passwd,ip,cmd);
        System.out.println(result.toString());
//        Arrays.stream(getEndPoints(result)).forEach(n->{
//            System.out.println(n);
//        });
    }
    public static StringBuilder execute(String cmd) {
        return execute(Common.USERNAME,Common.PASSWORD,Common.IP, cmd);
    }
    public static StringBuilder execute(String userName, String password, String ip, String cmd) {
        int port = 22;
        StringBuilder result = new StringBuilder("");
        JSch jsch = null;
        Session session = null;
        InputStream in = null;
        BufferedReader reader = null;
        ChannelExec channelExec = null;
        try {
            jsch = new JSch();
            session = jsch.getSession(userName, ip, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channelExec = (ChannelExec)session.openChannel("exec");
            channelExec.setCommand(cmd);
            channelExec.connect();
            in = channelExec.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));
            String buf = null;
            while((buf = reader.readLine())!=null){
                result.append(buf+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(channelExec.isClosed()&&channelExec!=null){
                channelExec.disconnect();
            }
            if(session!=null&&session.isConnected()){
                session.disconnect();
            }
        }
      return result;
    }
    public static String[] getEndPoints(StringBuilder s){
        int start = s.indexOf("Endpoints:")+"Endpoints:".length();
        int end = s.indexOf("Session Affinity");
        String[] s2 = s.substring(start,end).replaceAll("\\s*","").split(",");
        return s2;
    }
    public static String[] getState(StringBuilder s){
        int start = s.indexOf("Endpoints:")+"Endpoints:".length();
        int end = s.indexOf("Session Affinity");
        String[] s2 = s.substring(start,end).replaceAll("\\s*","").split(",");
        return s2;
    }
}
