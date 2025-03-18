package CSudoku.player.human;

import CSudoku.player.Player;
import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;

import java.util.Scanner;

/**
 * Represents a human player in the Sudoku game.
 * This class allows a human player to input their move interactively through the console.
 */
public class HumanPlayer implements Player {

    /**
     * Prompts the human player to input their move.
     * The player is asked to enter the row, column, and value of their move,
     * separated by spaces. The input is then used to create a {@link Move} object.
     *
     * @param board The current state of the Sudoku board.
     * @return A {@link Move} object containing the player's input.
     */
    @Override
    public Move getMove(CSudokuBoard board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row, column, and value (separated by spaces): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int value = scanner.nextInt();
        return new Move(row, col, value);
    }
}