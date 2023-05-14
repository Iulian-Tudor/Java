package org.example;

public class Board {
    private static final int BOARD_SIZE = 15;
    private static final int EMPTY = 0;
    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;

    private int[][] board;

    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public boolean placePiece(int row, int col, int player) {
        if (board[row][col] == EMPTY) {
            board[row][col] = player;
            printBoard();
            return true;
        }
        return false;
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkWin(int row, int col, int player) {
        return checkHorizontal(row, player) || checkVertical(col, player) || checkDiagonal(row, col, player);
    }

    private boolean checkHorizontal(int row, int player) {
        int count = 0;
        for (int col = 0; col < BOARD_SIZE; col++) {
            count = (board[row][col] == player) ? count + 1 : 0;
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(int col, int player) {
        int count = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            count = (board[row][col] == player) ? count + 1 : 0;
            if (count == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal(int row, int col, int player) {
        return checkDiagonalUp(row, col, player) || checkDiagonalDown(row, col, player);
    }

    private boolean checkDiagonalUp(int row, int col, int player) {
        int count = 0;
        while (row > 0 && col > 0) {
            row--;
            col--;
        }
        while (row < BOARD_SIZE && col < BOARD_SIZE) {
            count = (board[row][col] == player) ? count + 1 : 0;
            if (count == 5) {
                return true;
            }
            row++;
            col++;
        }
        return false;
    }

    private boolean checkDiagonalDown(int row, int col, int player) {
        int count = 0;
        while (row < BOARD_SIZE - 1 && col > 0) {
            row++;
            col--;
        }
        while (row >= 0 && col < BOARD_SIZE) {
            count = (board[row][col] == player) ? count + 1 : 0;
            if (count == 5) {
                return true;
            }
            row--;
            col++;
        }
        return false;
    }
}
