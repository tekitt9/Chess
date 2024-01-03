package Chess;

public enum Pieces {
    KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;
// ♔ ♕	♖ ♗ ♘	♙ ♚ ♛	♜ ♝ ♞	♟

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}