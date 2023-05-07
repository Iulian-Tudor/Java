package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;
        while ((inputLine = inputReader.readLine()) != null) {
            out.println(inputLine);
            if (inputLine.equals("exit")) {
                break;
            }
            System.out.println(in.readLine());
        }
        socket.close();
    }
}

