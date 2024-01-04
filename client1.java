package server_2clients_chat_application;

import java.io.*;
import java.net.Socket;

public class client1 {
    public static void main(String[] args) {
        try{
        Socket clienSocket=new Socket("localhost",1704);
        System.out.println("u r connected to server:");
        PrintWriter out=new PrintWriter(clienSocket.getOutputStream(),true);
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader sr=new BufferedReader(new InputStreamReader(clienSocket.getInputStream()));
        try{
            while(true){
            System.out.println("client1:");
           String str=kb.readLine();
           out.println(str);
           if(str.contains("quit")) break;
         String serverresopose=sr.readLine();
         System.out.println("server says:"+serverresopose);
        } 
    }finally{
         clienSocket.close();
         out.close();
         sr.close();
    }

    }
    catch(IOException e){
        e.printStackTrace();
    }
    }
    
}
