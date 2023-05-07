package org.example;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        String ip = "localhost";
        int port = 3000;
        try {
            GameClient client = new GameClient(ip, port);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
