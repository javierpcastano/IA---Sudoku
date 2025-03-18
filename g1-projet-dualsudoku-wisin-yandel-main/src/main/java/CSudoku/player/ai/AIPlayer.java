package CSudoku.player.ai;

import CSudoku.player.MoveStrategy;
import CSudoku.player.Player;
import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;

/**
 * Represents an AI player for the Sudoku game.
 * This player uses a move selection strategy and optionally a board evaluation strategy
 * to estimate the value of game boards and make decisions.
 */
public class AIPlayer implements Player {

    private MoveStrategy moveStrategy;

    /**
     * Creates an AI player with a move selection strategy.
     *
     * @param strategy The strategy used to select moves.
     */
    public AIPlayer(MoveStrategy strategy) {
        this.moveStrategy = strategy;
    }


    /**
     * Sets a new move selection strategy for the AI player.
     *
     * @param strategy The new strategy to select moves.
     */
    public void setMoveStrategy(MoveStrategy strategy) {
        this.moveStrategy = strategy;
    }

    /**
     * Sets a new board evaluation strategy for the AI player.
     *
     * @param strategy The new strategy used to evaluate game boards.
     */
    public void setEvalStrategy(MoveStrategy strategy) {
        this.moveStrategy = strategy;
    }

    /**
     * Gets the next move to play based on the move selection strategy.
     *
     * @param board The current game board.
     * @return The move chosen by the AI player.
     */
    @Override
    public Move getMove(CSudokuBoard board) {
        return moveStrategy.selectMove(board, this);
    }



    /**
     * Retrieves the current move selection strategy used by the AI player.
     *
     * @return The {@link MoveStrategy} instance currently assigned to this AI player.
     *         This strategy determines how the AI selects its moves during the game.
     */
    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public String getMoveStrategyName() {
        return moveStrategy.getName();
    }

    /**
     * Retrieves the board evaluation strategy used by the AI player.
     *
     * @return The current board evaluation strategy.
     */
}