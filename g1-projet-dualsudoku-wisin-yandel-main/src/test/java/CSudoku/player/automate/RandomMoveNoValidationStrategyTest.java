package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.ai.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomMoveNoValidationStrategyTest {

    private CSudokuBoard board;
    private AIPlayer player;
    private RandomMoveNoValidationStrategy strategy;

    @BeforeEach
    void setUp() {
        // Create a mock for CSudokuBoard
        board = mock(CSudokuBoard.class);
        // Create a mock for AIPlayer
        player = mock(AIPlayer.class);
        // Initialize the strategy
        strategy = new RandomMoveNoValidationStrategy();
    }

    @Test
    void testSelectMoveWhenEmptyCellsExist() {
        // Mocking board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that the move is not null
        assertNotNull(move, "The move should not be null.");
        // Verify that the cell is indeed empty
        verify(board, times(1)).isCellEmpty(move.getRow(), move.getCol());
        // Verify that the move value is valid
        assertTrue(move.getValue() >= 1 && move.getValue() <= 9, "The move value should be between 1 and 9.");
    }

    @Test
    void testSelectMoveWhenNoEmptyCells() {
        // Mocking board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(false); // No empty cells

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that the move is null (no empty cells)
        assertNull(move, "The move should be null as the board is full.");
    }

    @Test
    void testSelectMoveWithRandomValues() {
        // Mocking board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Call the selectMove method multiple times to check the random value
        for (int i = 0; i < 100; i++) {
            Move move = strategy.selectMove(board, player);

            // Verify that the move value is always within the valid range (1 to 9)
            assertTrue(move.getValue() >= 1 && move.getValue() <= 9, "The move value should be between 1 and 9.");
        }
    }

    @Test
    void testSelectMoveWithValidRowAndCol() {
        // Mocking board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Call the selectMove method and check that the move is within the board's bounds
        Move move = strategy.selectMove(board, player);

        assertNotNull(move, "The move should not be null.");
        assertTrue(move.getRow() >= 0 && move.getRow() < 9, "The move row should be valid.");
        assertTrue(move.getCol() >= 0 && move.getCol() < 9, "The move column should be valid.");
    }
}