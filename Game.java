package Chess;

import java.util.ArrayList;
import java.util.Scanner;

import static Chess.ChessType.PIECE;
import static Chess.ChessType.SPACE;
import static Chess.Pieces.*;
import static Chess.Pieces.ROOK;

public class Game {
    private final GameObject[][] board = new GameObject[8][8];
    private byte gameState = 0;
    private final Scanner scanner;
    private int turn = 1;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        fillBoard();
    }

    public void start() {
        while (gameState == 0) makeMove();
        printBoard(false);
        if (gameState == 1) System.out.println("Black's king was defeated! White wins");
        if (gameState == -1) System.out.println("White's king was defeated! Black wins");
    }

    private void makeMove() {
        printBoard(false);
        String s;
        byte x = -1;
        byte y = -1;
        byte x1 = -1;
        byte y1 = -1;
        boolean correct = false;
        boolean correct1 = false;
        boolean whiteKing = false;
        boolean blackKing = false;
        ArrayList<String> moves = null;
        while (!correct) {
            System.out.print("Choose a piece to move(Example: b1): ");
            s = scanner.nextLine();
            if (s.length() >= 2) {
                y = isY(s.charAt(1));
                x = isX(s.charAt(0));
            }
            if (x != -1 && y != -1) {
                if (board[y][x].getType() == PIECE) {
                    if (board[y][x].isWhite()) {
                        if (turn % 2 != 0) {
                            correct = true;
                            moves = board[y][x].calculateMoves(board, x, y);
                            if (moves.isEmpty()) {
                                correct = false;
                                System.out.println(board[y][x].getPiece().toString().substring(0, 1).toUpperCase() +
                                        board[y][x].getPiece().toString().substring(1) + " can't move");
                            }
                        } else System.out.println("Now is black's turn");
                    } else {
                        if (turn % 2 == 0) {
                            correct = true;
                            moves = board[y][x].calculateMoves(board, x, y);
                            if (moves.isEmpty()) {
                                correct = false;
                                System.out.println(board[y][x].getPiece().toString().substring(0, 1).toUpperCase() +
                                        board[y][x].getPiece().toString().substring(1) + " can't move");
                            }
                        } else System.out.println("Now is white's turn");
                    }
                } else System.out.println("There is no piece on this position");
            } else System.out.println("Illegal position");
        }
        for (String pos : moves) {
            board[Integer.parseInt(String.valueOf(pos.charAt(1)))]
                    [Integer.parseInt(String.valueOf(pos.charAt(0)))].setX(true);
        }
        printBoard();
        System.out.printf("Choose where to move your %s: ", board[y][x].getPiece());
        while (!correct1) {
            s = scanner.nextLine();
            if (s.length() >= 2) {
                y1 = isY(s.charAt(1));
                x1 = isX(s.charAt(0));
            }
            if (x1 != -1 && y1 != -1) {
                for (String string : moves) {
                    if (string.charAt(0) == String.valueOf(x1).charAt(0) &&
                            string.charAt(1) == String.valueOf(y1).charAt(0)) {
                        correct1 = true;
                        break;
                    }
                }
            } else System.out.println("Illegal position");
            if (!correct1) {
                System.out.printf("Your %s can't move there%n", board[y][x].getPiece());
            }
        }
        if (board[y1][x1].getPiece() == KING && !board[x1][y1].isWhite()) gameState = 1;
        if (board[y1][x1].getPiece() == KING && board[x1][y1].isWhite()) gameState = -1;
        board[y1][x1] = board[y][x];
        board[y][x] = new GameObject(SPACE);
        turn++;
    }

    private void fillBoard() {
        board[0][0] = new GameObject(PIECE, ROOK, false);
        board[0][1] = new GameObject(PIECE, KNIGHT, false);
        board[0][2] = new GameObject(PIECE, BISHOP, false);
        board[0][3] = new GameObject(PIECE, QUEEN, false);
        board[0][4] = new GameObject(PIECE, KING, false);
        board[0][5] = new GameObject(PIECE, BISHOP, false);
        board[0][6] = new GameObject(PIECE, KNIGHT, false);
        board[0][7] = new GameObject(PIECE, ROOK, false);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new GameObject(PIECE, PAWN, false);
        }
        board[7][0] = new GameObject(PIECE, ROOK, true);
        board[7][1] = new GameObject(PIECE, KNIGHT, true);
        board[7][2] = new GameObject(PIECE, BISHOP, true);
        board[7][3] = new GameObject(PIECE, QUEEN, true);
        board[7][4] = new GameObject(PIECE, KING, true);
        board[7][5] = new GameObject(PIECE, BISHOP, true);
        board[7][6] = new GameObject(PIECE, KNIGHT, true);
        board[7][7] = new GameObject(PIECE, ROOK, true);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new GameObject(PIECE, PAWN, true);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null){
                    board[i][j] = new GameObject(ChessType.SPACE);
                }
            }
        }
    }

    public void printBoard() {
        for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                System.out.print(go + " ");
            }
            System.out.println();
        }
    }

    public void printBoard(boolean setX) {
        for (GameObject[] gos : board) {
            for (GameObject go : gos) {
                go.setX(setX);
                System.out.print(go + " ");
            }
            System.out.println();
        }
    }
    
    private static byte isY(char value) {
        if (value == '1' || value == '2' || value == '3' || value == '4' ||
                value == '5' || value == '6' || value == '7' || value == '8') {
            return (byte) (8 - Byte.parseByte(String.valueOf(value)));
        } else return -1;
    }

    private static byte isX(char value) {
        return switch (value) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> -1;
        };
    }
}
