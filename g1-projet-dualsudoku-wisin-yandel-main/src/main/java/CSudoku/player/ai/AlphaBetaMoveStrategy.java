package CSudoku.player.ai;

import CSudoku.observers.AlphaBetaPruningObserver;
import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.MoveStrategy;
import CSudoku.player.Player;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaMoveStrategy implements MoveStrategy {

    private static final int MAX_DEPTH = 3; // Depth of the search tree
    private AlphaBetaPruningObserver observer;

    /**
     * Constructor for AlphaBetaMoveStrategy.
     * Initializes the observer for tracking the Alpha-Beta cuts and node visits.
     */
    public AlphaBetaMoveStrategy() {
        this.observer = new AlphaBetaPruningObserver(); // Initialize the observer
    }

    /**
     * Selects the best move using the Alpha-Beta pruning algorithm.
     *
     * @param board  The current state of the Sudoku board.
     * @param player The AI player making the move.
     * @return The best {@link Move} according to the Alpha-Beta evaluation.
     */
    @Override
    public Move selectMove(CSudokuBoard board, Player player) {
        //TODO
        return null; // Return the best move found
    }

    /**
     * Retrieves all valid moves for the AI player.
     *
     * @param board  The current state of the Sudoku board.
     * @param player The AI player.
     * @return A list of all valid {@link Move} objects.
     */
    private List<Move> getValidMoves(EvaluatedSimulatedBoard board, AIPlayer player) {
      //TODO
        return null;
    }

    /**
     * Implements the Alpha-Beta pruning algorithm recursively to evaluate moves.
     *
     * @param board              The current state of the board.
     * @param depth              The remaining search depth.
     * @param alpha              The best value for the maximizing player so far.
     * @param beta               The best value for the minimizing player so far.
     * @param isMaximizingPlayer True if the current player is the maximizing player.
     * @param player             The AI player.
     * @return The evaluation score of the board state.
     */
    private int alphaBeta(EvaluatedSimulatedBoard board, int depth, int alpha, int beta, boolean isMaximizingPlayer, AIPlayer player) {
        //TODO
        return 0;
    }

    /**
     * Returns the name of the strategy, which in this case is "AlphaBeta".
     * <p>
     * This method is part of the {@link MoveStrategy} interface and is used to retrieve
     * the name of the strategy being implemented. In this case, it returns the string "AlphaBeta",
     * which indicates that the AlphaBeta Move strategy is used for selecting moves.
     * </p>
     *
     * @return The name of the strategy, which is {@code "AlphaBeta"}.
     */
    @Override
    public String getName() {
        return "AlphaBeta";
    }
}