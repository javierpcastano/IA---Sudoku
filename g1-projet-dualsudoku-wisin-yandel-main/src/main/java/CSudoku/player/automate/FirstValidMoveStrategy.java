package CSudoku.player.automate;

import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;
import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;

import java.util.Random;

/**
 * Implements a simple strategy for selecting the first valid move on a Sudoku board.
 * This strategy iterates over the board cells and selects the first valid move that
 * can be made, considering the rules of Sudoku.
 */
public class FirstValidMoveStrategy implements MoveStrategy {

    /**
     * Selects the first valid move available on the given Sudoku board.
     *
     * @param board  The current state of the Sudoku board.
     * @param player The AI player attempting to make a move.
     * @return A {@link Move} object representing the first valid move found,
     *         or {@code null} if no valid moves are available.
     */
    @Override
    public Move selectMove(CSudokuBoard board, Player player) {
        int boardSize = board.getSize();
        for (int i = 0 ; i < boardSize; i++){
            for (int j = i ; j < boardSize; j++){
                if (board.isCellEmpty(i,j)) {
                    for (int v = 1 ; v <= boardSize; v++){
                        Move move1 = new Move(i, j, v);
                        if (player.isValidMove(board, move1)){
                            return move1;
                        }
                    }
                }
            }
            for (int k = 1 ; k < boardSize; k++){
                if (board.isCellEmpty(k,i)) {
                    for (int w = 1 ; w <= boardSize; w++){
                        Move move2 = new Move(k, i , w);
                        if (player.isValidMove(board, move2)){
                            return move2;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the name of the strategy, which in this case is "First valid".
     * <p>
     * This method is part of the {@link MoveStrategy} interface and is used to retrieve
     * the name of the strategy being implemented. In this case, it returns the string "First valid",
     * which indicates that the First Valid Move strategy is used for selecting moves.
     * </p>
     *
     * @return The name of the strategy, which is {@code "First valid"}.
     */
    @Override
    public String getName() {
        return "First valid";
    }
}