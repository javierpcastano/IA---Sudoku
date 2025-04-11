package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a strategy for selecting a random move in the Sudoku game.
 * The strategy selects an empty cell at random and assigns a random value.
 * No validation is performed to check if the move complies with Sudoku rules.
 */
public class RandomMoveNoValidationStrategy implements MoveStrategy {

    /**
     * Selects a random move for the AI player by choosing an empty cell
     * and assigning a random value, without checking for move validity.
     *
     * @param board The current state of the Sudoku board.
     * @param player The AI player who is making the move.
     * @return A randomly selected {@link Move} object, or null if no empty cells are available.
     */
    @Override
    public Move selectMove(CSudokuBoard board, Player player) {
        Random random = new Random();
        int boardSize = board.getSize();

        // Liste pour stocker les coordonnées des cases vides
        List<int[]> emptyCells = new ArrayList<>();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board.isCellEmpty(row, col)) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        // Si aucune case vide n'est trouvée
        if (emptyCells.isEmpty()) {
            return null;
        }

        // Choisir une case vide au hasard
        int[] coords = emptyCells.get(random.nextInt(emptyCells.size()));
        int row = coords[0];
        int col = coords[1];

        // Choisir une valeur aléatoire (entre 1 et boardSize)
        int value = random.nextInt(boardSize) + 1;

        return new Move(row, col, value);
    }

    /**
     * Returns the name of the strategy, which in this case is "Random No Validation".
     * <p>
     * This method is part of the {@link MoveStrategy} interface and is used to retrieve
     * the name of the strategy being implemented. In this case, it returns the string "Random No Validation",
     * which indicates that the Random No Validation Move strategy is used for selecting moves.
     * </p>
     *
     * @return The name of the strategy, which is {@code "Random"}.
     */
    @Override
    public String getName() {
        return "Random No Validation";
    }
}
