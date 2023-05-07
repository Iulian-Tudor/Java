package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.server = server;
    }

    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("stop")) {
                    out.println("Server stopped");
                    server.stop();
                    break;
                } else if (inputLine.equals("exit")) {
                    out.println("You have been disconnected");
                    socket.close();
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


