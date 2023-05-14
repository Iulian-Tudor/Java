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
    private boolean disconnected = false;

    public ClientThread(Socket socket, GameServer server) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.server = server;
    }

    public void run() {
        try {
            String inputLine;
            while (!disconnected && (inputLine = in.readLine()) != null) {
                String[] command = inputLine.split(" ");

                switch (command[0].toLowerCase()) {
                    case "stop":
                        out.println("Server stopped");
                        server.stop();
                        break;
                    case "exit":
                        out.println("You have been disconnected");
                        disconnected = true;
                        break;
                    case "create":
                        server.createGame(command[1], new Player(command[2]));
                        out.println("Game created: " + command[1]);
                        break;
                    case "join":
                        server.joinGame(command[1], new Player(command[2]));
                        out.println("Joined game: " + command[1]);
                        break;
                    case "submit":
                        boolean success = server.submitMove(command[1], new Player(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
                        out.println(success ? "Move submitted" : "Invalid move");
                        break;
                    default:
                        out.println("Server received the request: " + inputLine);
                        break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



