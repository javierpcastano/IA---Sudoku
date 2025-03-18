package CSudoku.player.ai;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;

/**
 * A class representing an evaluated and simulated Sudoku board.
 * Extends the functionality of {@link CSudokuBoard} by adding evaluation capabilities
 * for AI strategies and maintaining the state of rows, columns, and subgrids.
 */
public class EvaluatedSimulatedBoard extends CSudokuBoard {

    private int eval; // Evaluation score of the board.
    private Move lastMove; // The last move played on the board.
    private int[] zerosInRows; // Number of empty cells (zeros) in each row.
    private int[] zerosInColumns; // Number of empty cells (zeros) in each column.

    /**
     * Creates an empty evaluated simulated board with the given size.
     *
     * @param size The size of the board (e.g., 4, 9, 16, etc.).
     */
    public EvaluatedSimulatedBoard(int size) {
        super(size);
       //TODO
    }

    /**
     * Creates a deep copy of the given evaluated simulated board.
     *
     * @param board The board to copy.
     */
    public EvaluatedSimulatedBoard(EvaluatedSimulatedBoard board) {
        this(board.getSize());
        //TODO
    }

    /**
     * Creates a simulated board from an existing board, initializing it for evaluation.
     *
     * @param board  The original board.
     * @param player The AI/Automate player making the evaluation.
     */
    public EvaluatedSimulatedBoard(CSudokuBoard board, Player player) {
        this(board.getSize());
        //TODO
    }

    /**
     * Returns the number of empty cells in a specified row.
     *
     * @param row The row index.
     * @return The number of empty cells in the row.
     * @throws IllegalArgumentException If the row index is invalid.
     */
    public int getZerosInRow(int row) {
       //TODO
        return 0;
    }

    /**
     * Returns the number of empty cells in a specified column.
     *
     * @param col The column index.
     * @return The number of empty cells in the column.
     * @throws IllegalArgumentException If the column index is invalid.
     */
    public int getZerosInColumn(int col) {
       //TODO
        return 0;
    }

    /**
     * Checks if the board is completely filled.
     *
     * @return {@code true} if there are no empty cells, {@code false} otherwise.
     */
    public boolean isFull() {
      //TODO
        return false;
    }

    /**
     * Gets the evaluation score of the board.
     *
     * @return The current evaluation score.
     */
    public int getEval() {
        return eval;
    }

    /**
     * Sets the evaluation score of the board.
     *
     * @param eval The new evaluation score.
     */
    public void setEval(int eval) {
        this.eval = eval;
    }

    /**
     * Gets the last move played on the board.
     *
     * @return The last move.
     */
    public Move getLastMove() {
        return lastMove;
    }

    /**
     * Sets the last move played on the board.
     *
     * @param move The move to set.
     */
    public void setLastMove(Move move) {
        lastMove = move;
    }


    /**
     * Sets a value on the board and updates the evaluation score.
     *
     * @param move                The move to apply.
     * @param isMaximisingPlayer  {@code true} if the move is by the maximizing player.
     */
    public void setValue(Move move, boolean isMaximisingPlayer) {
       //TODO
    }

    /**
     * Checks if a row is completely filled.
     *
     * @param row The row index.
     * @return {@code true} if the row is filled, {@code false} otherwise.
     */
    public boolean isRowFilled(int row) {
        //TODO
        return false;
    }

    /**
     * Checks if a column is completely filled.
     *
     * @param col The column index.
     * @return {@code true} if the column is filled, {@code false} otherwise.
     */
    public boolean isColumnFilled(int col) {
        //TODO
        return false;
    }

    /**
     * Checks if the subgrid containing a cell is completely filled.
     *
     * @param row The row index.
     * @param col The column index.
     * @return {@code true} if the subgrid is filled, {@code false} otherwise.
     */
    public boolean isSubgridFilled(int row, int col) {
        //TODO
        return false;
    }

    /**
     * Decreases the count of empty cells in a row.
     *
     * @param row The row index.
     */
    public void decreaseZerosInRows(int row) {
     //TODO
    }

    /**
     * Decreases the count of empty cells in a column.
     *
     * @param col The column index.
     */
    public void decreaseZerosInColumns(int col) {
      //TODO
    }

    /**
     * Checks if a cell is empty.
     *
     * @param row The row index.
     * @param col The column index.
     * @return {@code true} if the cell is empty, {@code false} otherwise.
     */
    public boolean isCellEmpty(int row, int col) {
        //TODO
        return false;
    }

    /**
     * Checks if the subgrid containing a cell is almost filled.
     *
     * @param row The row index.
     * @param col The column index.
     * @return {@code true} if the subgrid is almost filled, {@code false} otherwise.
     */
    private boolean isSubgridAlmostFilled(int row, int col) {
        //TODO
        return false;
    }

   }