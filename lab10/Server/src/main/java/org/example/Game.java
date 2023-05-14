package org.example;

public class Game {
    private String gameId;
    private Board board;
    private Player player1;
    private Player player2;
    private long lastMoveTime;

    public Game(String gameId, Board board, Player player1, Player player2) {
        this.gameId = gameId;
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getGameId() {
        return gameId;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
