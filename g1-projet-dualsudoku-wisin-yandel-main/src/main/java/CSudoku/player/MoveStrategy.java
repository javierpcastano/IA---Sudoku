package CSudoku.player;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;

/**
 * Represents a strategy for selecting a move in a Sudoku game.
 * <p>
 * Implementations of this interface define different ways an AI can decide on its next move.
 * The strategy can be applied to various types of AI players, influencing how they select moves.
 * </p>
 *
 * @see Player
 * @see Move
 * @see CSudokuBoard
 */
public interface MoveStrategy {

    /**
     * Selects the next move for the AI player based on the current state of the Sudoku board.
     * <p>
     * This method is called to let the AI player decide which move to make, given the current state of the board.
     * The method should return a {@link Move} object representing the action to take.
     * If no valid move is available, it should return {@code null}.
     * </p>
     *
     * @param board  The current state of the Sudoku board.
     * @param player The AI player making the move. This may be used to factor in player-specific strategies.
     * @return A {@link Move} representing the selected action, or {@code null} if no valid move is available.
     * @see Move
     */
    Move selectMove(CSudokuBoard board, Player player);

    /**
     * Returns the name of the strategy, which helps identify the type of move selection algorithm.
     * <p>
     * The name could be used for debugging or logging purposes to track which strategy the AI is using.
     * </p>
     *
     * @return The name of the strategy.
     */
    String getName();
}