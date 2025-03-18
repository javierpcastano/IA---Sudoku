package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.automate.FirstValidMoveStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FirstValidMoveStrategyTest {

    private CSudokuBoard board;
    private Player player;
    private FirstValidMoveStrategy strategy;

    @BeforeEach
    void setUp() {
        // Create a mock of the Sudoku board
        board = mock(CSudokuBoard.class);
        // Create a mock of the player
        player = mock(Player.class);
        // Create an instance of FirstValidMoveStrategy
        strategy = new FirstValidMoveStrategy();
    }



    @Test
    void testSelectMoveWhenNoValidMovesExist() {
        // Simulate the board size
        when(board.getSize()).thenReturn(9);

        // Mock the behavior of the board and the player
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // All cells are empty
        when(player.isValidMove(eq(board), any(Move.class))).thenReturn(false); // No valid moves

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that no valid move was selected (should return null)
        assertNull(move, "No valid move should be selected when no valid moves exist.");
    }


    @Test
    void testSelectMoveWithEmptyBoard() {
        // Simulate the board size
        when(board.getSize()).thenReturn(9);

        // Mock the behavior of the board and the player
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(false); // No empty cells

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that no move is selected (board is full)
        assertNull(move, "No move should be selected as the board is full.");
    }
}