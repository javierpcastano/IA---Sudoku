package CSudoku.player;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.ai.EvaluatedSimulatedBoard;

/**
 * Represents a player in the Sudoku game.
 * This interface defines the behavior for any type of player,
 * whether human or AI, by specifying how they select their moves.
 * <p>
 * Implementing classes should provide the logic for how players make decisions during the game,
 * including how they generate and validate moves based on the current state of the board.
 * </p>
 */
public interface Player {

    /**
     * Retrieves the next move chosen by the player.
     * <p>
     * This method is used to ask the player for their next move given the current state of the Sudoku board.
     * The move is returned as a {@link Move} object, which contains the row, column, and value chosen by the player.
     * </p>
     *
     * @param board The current state of the Sudoku board, represented by the {@link CSudokuBoard} object.
     *              The player will use this board to determine their next move.
     * @return The move selected by the player as a {@link Move} object.
     *         The {@link Move} includes the row, column, and value that the player has chosen.
     * @see Move
     */
    Move getMove(CSudokuBoard board);

    /**
     * Validates if a given move is valid on a simulated board state.
     * <p>
     * This method checks if a move is valid based on the current state of a simulated version of the Sudoku board.
     * This is typically used by AI players to evaluate possible moves during the decision-making process.
     * </p>
     *
     * @param board The simulated board state represented by an {@link EvaluatedSimulatedBoard} object.
     *              The simulated board reflects the potential outcomes of different moves.
     * @param move  The {@link Move} object representing the move that is being validated.
     * @return {@code true} if the move is valid according to the simulated board, {@code false} otherwise.
     * @see EvaluatedSimulatedBoard
     * @see Move
     */
    /**
     * Checks whether a move is valid on the given game board.
     *
     * @param board The current game board.
     * @param move  The move to validate.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    default boolean isValidMove(CSudokuBoard board, Move move) {

        int nb_row = move.getRow();
        int nb_col  = move.getCol();
        int val_mov = move.getValue();

        int size_board = board.getSize();
        int size_case = (int) Math.sqrt(size_board);

        int coin_row = (nb_row/size_case)*size_case;
        int coin_col = (nb_col/size_case)*size_case;


        /* Vérification valeurs du bloc */
        for(int r = coin_row; r<  coin_row + size_case; r++ ){
            for (int c = coin_col; c < coin_col + size_case; c++) {
                int val_comp = board.getValue(r, c);
                if (val_comp == val_mov){
                    return false;
                }
            }
        }

        /* Vérification valeurs de la ligne et de la colonne */
        for (int i = 0; i < size_board; i++) {
            int val_comp_col = board.getValue(i, nb_col);
            int val_comp_row = board.getValue(nb_row, i);

            if (val_comp_col == val_mov || val_comp_row == val_mov)
                return false;
        }

        // Vérification des contraintes consécutives
        // Vérification des cases adjacentes sur la ligne
        int[] adj_rows = {nb_row - 1, nb_row + 1};
        for (int adj_row : adj_rows) {
            if (adj_row >= 0 && adj_row < size_board) {
                int val_constr = board.getValue(adj_row, nb_col);
                if (board.hasConsecutiveConstraint(nb_row, nb_col, adj_row, nb_col) &&
                        Math.abs(val_constr - val_mov) != 1) {
                    return false;
                }
            }
        }

        // Vérification des cases adjacentes sur la colonne
        int[] adj_cols = {nb_col - 1, nb_col + 1};
        for (int adj_col : adj_cols) {
            if (adj_col >= 0 && adj_col < size_board) {
                int val_constr = board.getValue(nb_row, adj_col);
                if (board.hasConsecutiveConstraint(nb_row, nb_col, nb_row, adj_col) &&
                        Math.abs(val_constr - val_mov) != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}