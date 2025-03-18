package CSudoku.board;

/**
 * Represents a move in a Sudoku game, including the row, column, and value to be placed.
 */
public class Move {

    private int row;    // The row index of the move
    private int col;    // The column index of the move
    private int value;  // The value to be placed in the specified cell

    /**
     * Constructs a Move with the specified row, column, and value.
     *
     * @param row   The row index of the move (0-based).
     * @param col   The column index of the move (0-based).
     * @param value The value to be placed in the cell.
     */
    public Move(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * Copy constructor for creating a new Move instance based on another Move.
     *
     * @param move The Move object to be copied.
     */
    public Move(Move move) {
        this.row = move.row;
        this.col = move.col;
        this.value = move.value;
    }

    /**
     * Gets the row index of the move.
     *
     * @return The row index (0-based).
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column index of the move.
     *
     * @return The column index (0-based).
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the value to be placed in the cell.
     *
     * @return The value of the move.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of the Move.
     * This includes the row, column, and value for debugging purposes.
     *
     * @return A string in the format: "Move{row=..., col=..., value=...}".
     */
    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", col=" + col +
                ", value=" + value +
                '}';
    }
}