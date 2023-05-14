package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class GameServer {
    private int port;
    private ServerSocket serverSocket;
    private ConcurrentHashMap<String, Game> games;


    public GameServer(int port) {
        this.port = port;
        games = new ConcurrentHashMap<>();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(socket, this);
            clientThread.start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        System.out.println("Server stopped");
    }


    public void createGame(String gameId, Player player) {
        Game game = new Game(gameId, new Board(), player, null);
        games.put(gameId, game);
    }

    public void joinGame(String gameId, Player player) {
        Game game = games.get(gameId);
        if (game != null && game.getPlayer2() == null) {
            game.setPlayer2(player);
        }
    }

    public boolean submitMove(String gameId, Player player, int row, int col) {
        Game game = games.get(gameId);
        if (game != null) {
            int playerNumber = game.getPlayer1().equals(player) ? 2 : 1;
            Board board = game.getBoard();
            if (board.placePiece(row, col, playerNumber)) {
                if (board.checkWin(row, col, playerNumber)) {
                    System.out.println("Player " + playerNumber + " wins!");
                    games.remove(gameId);
                }
                return true;
            }
        }
        return false;
    }


    public Game getGame(String gameId) {
        return games.get(gameId);
    }
}

