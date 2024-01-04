package server_2clients_chat_application;

import java.io.*;
import java.net.Socket;

public class client2 {
    public static void main(String[] args) {
        try{
            Socket clienSocket=new Socket("localhost", 1704);
            System.out.println("client2 is connected:") ;
            BufferedReader sr = new BufferedReader (new InputStreamReader(clienSocket.getInputStream()));
            BufferedReader kb= new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out= new PrintWriter(clienSocket.getOutputStream(), true);
            try{
                while(true){
                System.out.println("client2:");
                String str=kb.readLine();
                out.println(str);
                if(str.contains("quit")) break;
                String s=sr.readLine();
                System.out.println("server says:"+s);
             }}
                finally{
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
