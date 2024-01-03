package Chess;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;

import static Chess.ChessType.PIECE;

public class GameObject {
    private final Pieces piece;
    private final boolean isWhite;
    private ChessType type;
    private boolean isX = false;

    public boolean isX() {
        return isX;
    }

    public void setX(boolean x) {
        isX = x;
    }

    public ChessType getType() {
        return type;
    }

    public void setType(ChessType type) {
        this.type = type;
    }

    public GameObject(ChessType type, Pieces piece, boolean isWhite) {
        this.type = type;
        this.piece = piece;
        this.isWhite = isWhite;
    }

    public GameObject(ChessType type) {
        this.type = type;
        piece = null;
        isWhite = false;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Pieces getPiece() {
        return piece;
    }

    public ArrayList<String> calculateMoves(GameObject[][] board, byte x, byte y) {
        ArrayList<String> moves = new ArrayList<>(28);
        switch (piece) {
            case KING -> {
                if (x > 0) {
                    if (y > 0) {
                        if (board[y - 1][x - 1].type == PIECE) {
                            if (board[y - 1][x - 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 1) + String.valueOf(y - 1));
                            }
                        }
                    }
                    if (board[y][x - 1].type == PIECE) {
                        if (board[y][x - 1].isWhite != this.isWhite) {
                            moves.add(String.valueOf(x - 1) + String.valueOf(y));
                        }
                    } else
                    if (y < 7) {
                        if (board[y + 1][x - 1].type == PIECE) {
                            if (board[y + 1][x - 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 1) + String.valueOf(y + 1));
                            }
                        }
                    }
                }
                if (x < 7) {
                    if (y > 0) {
                        if (board[y - 1][x + 1].type == PIECE) {
                            if (board[y - 1][x + 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 1) + String.valueOf(y - 1));
                            }
                        }
                    }
                    if (board[y][x + 1].type == PIECE) {
                        if (board[y][x + 1].isWhite != this.isWhite) {
                            moves.add(String.valueOf(x + 1) + String.valueOf(y));
                        }
                    }
                    if (y < 7) {
                        if (board[y + 1][x + 1].type == PIECE) {
                            if (board[y + 1][x + 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 1) + String.valueOf(y + 1));
                            }
                        }
                    }
                }
                if (y > 0) {
                    if (board[y - 1][x].type == PIECE) {
                        if (board[y - 1][x].isWhite != this.isWhite) {
                            moves.add(String.valueOf(x) + String.valueOf(y - 1));
                        }
                    }
                }
                if (y < 7) {
                    if (board[y + 1][x].type == PIECE) {
                        if (board[y + 1][x].isWhite != this.isWhite) {
                            moves.add(String.valueOf(x) + String.valueOf(y + 1));
                        }
                    }
                }
                return moves;
            }
            case QUEEN -> {
                for (int i = y - 1; i >= 0; i--) {
                    if (board[i][x].type == ChessType.SPACE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                    } else if (board[i][x].isWhite != isWhite &&
                            board[i][x].type == PIECE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                        break;
                    } else break;
                }
                for (int i = x + 1; i <= 7; i++) {
                    if (board[y][i].type == ChessType.SPACE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                    } else if (board[y][i].isWhite != isWhite &&
                            board[y][i].type == PIECE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                        break;
                    } else break;
                }
                for (int i = y + 1; i <= 7; i++) {
                    if (board[i][x].type == ChessType.SPACE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                    } else if (board[i][x].isWhite != isWhite &&
                            board[i][x].type == PIECE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                        break;
                    } else break;
                }
                for (int i = x - 1; i >= 0; i--) {
                    if (board[y][i].type == ChessType.SPACE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                    } else if (board[y][i].isWhite != isWhite &&
                            board[y][i].type == PIECE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                        break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y - i >= 0) {
                        if (board[y - i][x + i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y - i));
                        } else if (board[y - i][x + i].isWhite != isWhite &&
                                board[y - i][x + i].type == PIECE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y - i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y + i <= 7) {
                        if (board[y + i][x + i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y + i));
                        } else if (board[y + i][x + i].isWhite != isWhite &&
                                board[y + i][x + i].type == PIECE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y + i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y + i <= 7) {
                        if (board[y + i][x - i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y + i));
                        } else if (board[y + i][x - i].isWhite != isWhite &&
                                board[y + i][x - i].type == PIECE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y + i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y - i >= 0) {
                        if (board[y - i][x - i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y - i));
                        } else if (board[y - i][x - i].isWhite != isWhite &&
                                board[y - i][x - i].type == PIECE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y - i));
                            break;
                        } else break;
                    } else break;
                }
            }
            case ROOK -> {
                for (int i = y - 1; i >= 0; i--) {
                    if (board[i][x].type == ChessType.SPACE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                    } else if (board[i][x].isWhite != isWhite &&
                            board[i][x].type == PIECE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                        break;
                    } else break;
                }
                for (int i = x + 1; i <= 7; i++) {
                    if (board[y][i].type == ChessType.SPACE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                    } else if (board[y][i].isWhite != isWhite &&
                            board[y][i].type == PIECE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                        break;
                    } else break;
                }
                for (int i = y + 1; i <= 7; i++) {
                    if (board[i][x].type == ChessType.SPACE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                    } else if (board[i][x].isWhite != isWhite &&
                            board[i][x].type == PIECE) {
                        moves.add(String.valueOf(x) + String.valueOf(i));
                        break;
                    } else break;
                }
                for (int i = x - 1; i >= 0; i--) {
                    if (board[y][i].type == ChessType.SPACE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                    } else if (board[y][i].isWhite != isWhite &&
                            board[y][i].type == PIECE) {
                        moves.add(String.valueOf(i) + String.valueOf(y));
                        break;
                    } else break;
                }
            }
            case BISHOP -> {
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y - i >= 0) {
                        if (board[y - i][x + i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y - i));
                        } else if (board[y - i][x + i].isWhite != isWhite &&
                                board[y - i][x + i].type == PIECE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y - i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x + i <= 7 && y + i <= 7) {
                        if (board[y + i][x + i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y + i));
                        } else if (board[y + i][x + i].isWhite != isWhite &&
                                board[y + i][x + i].type == PIECE) {
                            moves.add(String.valueOf(x + i) + String.valueOf(y + i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y + i <= 7) {
                        if (board[y + i][x - i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y + i));
                        } else if (board[y + i][x - i].isWhite != isWhite &&
                                board[y + i][x - i].type == PIECE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y + i));
                            break;
                        } else break;
                    } else break;
                }
                for (int i = 1; i <= 7; i++) {
                    if (x - i >= 0 && y - i >= 0) {
                        if (board[y - i][x - i].type == ChessType.SPACE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y - i));
                        } else if (board[y - i][x - i].isWhite != isWhite &&
                                board[y - i][x - i].type == PIECE) {
                            moves.add(String.valueOf(x - i) + String.valueOf(y - i));
                            break;
                        } else break;
                    } else break;
                }
            }
            case KNIGHT -> {
                if (y > 0) {
                    if (x > 1) {
                        if (board[y - 1][x - 2].type == PIECE) {
                            if (board[y - 1][x - 2].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 2) + String.valueOf(y - 1));
                            }
                        } else moves.add(String.valueOf(x - 2) + String.valueOf(y - 1));
                    }
                    if (x < 6) {
                        if (board[y - 1][x + 2].type == PIECE) {
                            if (board[y - 1][x + 2].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 2) + String.valueOf(y - 1));
                            }
                        } else moves.add(String.valueOf(x + 2) + String.valueOf(y - 1));
                    }
                }
                if (y < 7) {
                    if (x > 1) {
                        if (board[y + 1][x - 2].type == PIECE) {
                            if (board[y + 1][x - 2].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 2) + String.valueOf(y + 1));
                            }
                        } else moves.add(String.valueOf(x - 2) + String.valueOf(y + 1));
                    }
                    if (x < 6) {
                        if (board[y + 1][x + 2].type == PIECE) {
                            if (board[y + 1][x + 2].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 2) + String.valueOf(y + 1));
                            }
                        } else moves.add(String.valueOf(x + 2) + String.valueOf(y + 1));
                    }
                }
                if (y > 1) {
                    if (x > 0) {
                        if (board[y - 2][x - 1].type == PIECE) {
                            if (board[y - 2][x - 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 1) + String.valueOf(y - 2));
                            }
                        } else moves.add(String.valueOf(x - 1) + String.valueOf(y - 2));
                    }
                    if (x < 7) {
                        if (board[y - 2][x + 1].type == PIECE) {
                            if (board[y - 2][x + 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 1) + String.valueOf(y - 2));
                            }
                        } else moves.add(String.valueOf(x + 1) + String.valueOf(y - 2));
                    }
                }
                if (y < 6) {
                    if (x > 0) {
                        if (board[y + 2][x - 1].type == PIECE) {
                            if (board[y + 2][x - 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x - 1) + String.valueOf(y + 2));
                            }
                        } else moves.add(String.valueOf(x - 1) + String.valueOf(y + 2));
                    }
                    if (x < 7) {
                        if (board[y + 2][x + 1].type == PIECE) {
                            if (board[y + 2][x + 1].isWhite != this.isWhite) {
                                moves.add(String.valueOf(x + 1) + String.valueOf(y + 2));
                            }
                        } else moves.add(String.valueOf(x + 1) + String.valueOf(y + 2));
                    }
                }
            }
            case PAWN -> {
                if (isWhite) {
                    if (y > 0) {
                        if (board[y - 1][x].type == ChessType.SPACE)
                            moves.add(String.valueOf(x) + String.valueOf(y - 1));
                        if (x > 0) {
                            if (!board[y - 1][x - 1].isWhite && board[y - 1][x - 1].type == PIECE)
                                moves.add(String.valueOf(x - 1) + String.valueOf(y - 1));
                        }
                        if (x < 7) {
                            if (!board[y - 1][x + 1].isWhite && board[y - 1][x + 1].type == PIECE)
                                moves.add(String.valueOf(x + 1) + String.valueOf(y - 1));
                        }
                    }
                    if (y == 6) {
                        if (board[y - 2][x].type == ChessType.SPACE)
                            moves.add(String.valueOf(x) + String.valueOf(y - 2));
                    }
                } else {
                    if (y < 7) {
                        if (board[y + 1][x].type == ChessType.SPACE)
                            moves.add(String.valueOf(x) + String.valueOf(y + 1));
                        if (x > 0)
                            if (!board[y + 1][x - 1].isWhite && board[y + 1][x - 1].type == PIECE)
                                moves.add(String.valueOf(x - 1) + String.valueOf(y + 1));
                    }
                    if (x < 7) {
                        if (!board[y + 1][x + 1].isWhite && board[y + 1][x + 1].type == PIECE)
                            moves.add(String.valueOf(x + 1) + String.valueOf(y + 1));
                    }
                }
                if (y == 1) {
                    if (board[y + 2][x].type == ChessType.SPACE)
                        moves.add(String.valueOf(x) + String.valueOf(y + 2));
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        if (!isX) {
            if (type == PIECE) {
                if (isWhite) {
                    return switch (piece) {
                        case KING -> "♚";
                        case QUEEN -> "♛";
                        case ROOK -> "♜";
                        case BISHOP -> "♝";
                        case KNIGHT -> "♞";
                        case PAWN -> "♟";
                    };
                } else {
                    return switch (piece) {
                        case KING -> "♔";
                        case QUEEN -> "♕";
                        case ROOK -> "♖";
                        case BISHOP -> "♗";
                        case KNIGHT -> "♘";
                        case PAWN -> "♙";
                    };
                }
            } else return "☐";
        } else return "⛶";
    }

    private static String numToChar(byte number) {
        return switch (number) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> throw new IllegalStateException("Unexpected value: " + number);
        };
    }
}