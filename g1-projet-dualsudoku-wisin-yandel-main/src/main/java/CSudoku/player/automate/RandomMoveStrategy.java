package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Represents a strategy for selecting a random valid move in the Sudoku game.
 * The strategy generates a list of all valid moves and randomly selects one.
 */
public class RandomMoveStrategy implements MoveStrategy {

    /**
     * Selects a valid move for the AI player by randomly choosing from all possible valid moves.
     * A valid move is one that satisfies the Sudoku rules.
     *
     * @param board The current state of the Sudoku board.
     * @param player The AI player who is making the move.
     * @return A randomly selected {@link Move} object, or null if no valid moves are available.
     */
    @Override
    public Move selectMove(CSudokuBoard board, Player player) {
        Random random = new Random();
        int boardSize = board.getSize();

        List<Move> validMoves = new ArrayList<>();

        // Parcourt toutes les cellules vides
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board.isCellEmpty(row, col)) {
                    for (int value = 1; value <= boardSize; value++) {
                        Move move = new Move(row, col, value);
                        if (player.isValidMove(board, move)) {
                            validMoves.add(move);
                        }
                    }
                }
            }
        }

        // Aucun coup valide disponible
        if (validMoves.isEmpty()) {
            return null;
        }

        // Choisit un coup valide au hasard
        return validMoves.get(random.nextInt(validMoves.size()));
    }



    /**
     * Returns the name of the strategy, which in this case is "Random".
     * <p>
     * This method is part of the {@link MoveStrategy} interface and is used to retrieve
     * the name of the strategy being implemented. In this case, it returns the string "Random",
     * which indicates that the Random Move strategy is used for selecting moves.
     * </p>
     *
     * @return The name of the strategy, which is {@code "Random"}.
     */
    @Override
    public String getName() {
        return "Random";
    }
}
