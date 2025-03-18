package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;

import java.util.Random;

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
        //TODO
        Random random = new Random();
        int boardSize = board.getSize();

        /** Número aleatorio entre 0 et la taille de la grille - 1*/
        int random_row = random.nextInt(boardSize) ;
        int random_col = random.nextInt(boardSize) ;

        /** Número aleatorio entre  et la taille de la grille */
        int random_val = random.nextInt(boardSize) + 1;

        int maxIter = boardSize*boardSize ;
        int cpt = 0;

        while (!(board.isCellEmpty(random_row,random_col )) && cpt < maxIter){
            random_row = random.nextInt(boardSize) ;
            random_col = random.nextInt(boardSize) ;
            cpt += 1;
        }
        Move move = new Move(random_row,random_col,random_val);

        if ( cpt == maxIter) return null;

        return move;

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