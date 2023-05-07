package org.example;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        int port = 3000;
        try {
            GameServer server = new GameServer(port);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
