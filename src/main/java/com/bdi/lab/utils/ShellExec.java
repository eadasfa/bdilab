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
//        String ip = "192.168.0.153";
//        String username = "root";
//        String passwd = "123456";
//        String cmd = "kubectl get pods -n k8s-test ";
//        StringBuilder result = execute(username,passwd,ip,cmd);
//        System.out.println(result.toString());
//        Arrays.stream(getEndPoints(result)).forEach(n->{
//            System.out.println(n);
//        });
        for (int i=0;i<10;i++){
            System.out.println(i+": "+getRecoverTime("task")+"ms");
        }
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
    /**
     * 故障恢复
    * */
    public static String getRecoverTime(String name){

        String cmd1 = "sh /home/script/recover-task.sh"+" "+name;
        String cmd2 = " kubectl delete pods -l app="+name;
        ExeThread thread1 = new ExeThread(cmd1);
        ExeThread thread2 = new ExeThread(cmd2);
        boolean flag=false;

        thread1.start();
        if(thread1.isAlive()){
            flag=true;
            System.out.println("线程1启动");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(flag) {
            thread2.start();
            System.out.println("线程2启动");
        }
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = thread1.getReturn().toString().trim();
//        System.out.println(res);
//        System.out.println(Integer.parseInt(res));
        return result;
    }
}
class ExeThread extends Thread{
    private StringBuilder res = null;
    private String cmd = "";
    public ExeThread(String cmd){
        this.cmd = cmd;
    }
    public StringBuilder getReturn(){
        return res;
    }
    public void run(){
        this.res=ShellExec.execute(cmd);
//        while (true){
//            try {
//                Thread.sleep(1000);
//                System.out.println(this.cmd);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
