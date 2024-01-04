package server_2clients_chat_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int clientCount = 0;

    public static void main(String[] args) {
        try {
           System.out.println("Waiting for clients...");
            ServerSocket listener = new ServerSocket(1704);
            while (true) {
                Socket client = listener.accept();
              System.out.println("Connection established with Client " + (++clientCount));
                 Thread clientThread = new ClientHandler(client, clientCount);
              clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private int clientNumber;

    public ClientHandler(Socket clientSocket, int clientNumber) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
    }

    @Override
    public void run() { 
        try {
            PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader sr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            try {
                while (true) {
                    String str = sr.readLine();
                    System.out.println("Client " + clientNumber + " says: " + str);
                    String kd = kb.readLine();
                    out.println(kd);
                    if (kd.contains("quit")) break;
                }
            } finally {
                out.close();
                clientSocket.close();
                sr.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
