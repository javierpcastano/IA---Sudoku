package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;

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


        int board_Size = board.getSize();
        List<SimpleEntry<Integer, Integer>> listeDePaires = new ArrayList<>();

        for (int i = 0; i < board_Size; i++){
            for (int j = 0 ;  j < board_Size; j++){
                if ()ni
            }
        }


        int rnd_row = random.nextInt(board_Size);
        int rnd_col = random.nextInt(board_Size);
        int rnd_val = random.nextInt(board_Size) + 1;
        int maxIter = board_Size*board_Size;
        int cpt = 0;


        Move temp_move = new Move(rnd_row, rnd_col, rnd_val);
        while ((!board.isCellEmpty(rnd_row, rnd_col) || !player.isValidMove(board, temp_move)) && cpt < maxIter) {
            rnd_row = random.nextInt(board_Size);
            rnd_col = random.nextInt(board_Size);
            rnd_val = random.nextInt(board_Size) + 1;
            temp_move = new Move(rnd_row, rnd_col, rnd_val);
            cpt += 1;

        }

        Move move = new Move(rnd_row,rnd_col,rnd_val);

        if ( cpt == maxIter || !(player.isValidMove(board, move)) )  return null;


        return move;
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