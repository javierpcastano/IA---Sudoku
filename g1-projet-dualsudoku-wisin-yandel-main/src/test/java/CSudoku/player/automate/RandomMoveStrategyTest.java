package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.ai.AIPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomMoveStrategyTest {

    private CSudokuBoard board;
    private AIPlayer player;
    private RandomMoveStrategy strategy;

    @BeforeEach
    void setUp() {
        // Create mocks for CSudokuBoard and AIPlayer
        board = mock(CSudokuBoard.class);
        player = mock(AIPlayer.class);
        strategy = new RandomMoveStrategy();
    }

    @Test
    void testSelectMoveWhenValidMovesExist() {
        // Mock board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Mock the player to always return true for valid moves
        when(player.isValidMove(any(CSudokuBoard.class), any(Move.class))).thenReturn(true);

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that a move is selected
        assertNotNull(move, "A valid move should be selected.");
        // Verify the move's row and column are within valid range
        assertTrue(move.getRow() >= 0 && move.getRow() < 9, "The move row should be valid.");
        assertTrue(move.getCol() >= 0 && move.getCol() < 9, "The move column should be valid.");
        // Verify the move value is within the valid range
        assertTrue(move.getValue() >= 1 && move.getValue() <= 9, "The move value should be between 1 and 9.");
    }

    @Test
    void testSelectMoveWhenNoValidMovesExist() {
        // Mock board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Mock the player to always return false for valid moves
        when(player.isValidMove(any(CSudokuBoard.class), any(Move.class))).thenReturn(false);

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that no move is selected
        assertNull(move, "No valid moves should be selected when all moves are invalid.");
    }

    @Test
    void testSelectMoveWithRandomSelection() {
        // Mock board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Mock the player to return true for all valid moves
        when(player.isValidMove(any(CSudokuBoard.class), any(Move.class))).thenReturn(true);

        // Call the selectMove method multiple times to test random selection
        Move firstMove = strategy.selectMove(board, player);
        Move secondMove = strategy.selectMove(board, player);

        // Ensure that the two moves are selected randomly
        assertNotNull(firstMove, "First move should be selected.");
        assertNotNull(secondMove, "Second move should be selected.");
        assertNotEquals(firstMove, secondMove, "The two moves should be different as they are selected randomly.");
    }

    @Test
    void testSelectMoveWithValidRowAndCol() {
        // Mock board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(true); // Assume all cells are empty

        // Mock the player to always return true for valid moves
        when(player.isValidMove(any(CSudokuBoard.class), any(Move.class))).thenReturn(true);

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that the move's row and column are within valid bounds
        assertNotNull(move, "A valid move should be selected.");
        assertTrue(move.getRow() >= 0 && move.getRow() < 9, "The move row should be valid.");
        assertTrue(move.getCol() >= 0 && move.getCol() < 9, "The move column should be valid.");
    }

    @Test
    void testSelectMoveWithEmptyBoard() {
        // Mock board methods
        when(board.getSize()).thenReturn(9); // 9x9 board size
        when(board.isCellEmpty(anyInt(), anyInt())).thenReturn(false); // No empty cells

        // Call the selectMove method
        Move move = strategy.selectMove(board, player);

        // Verify that no move is selected (board is full)
        assertNull(move, "No move should be selected as the board is full.");
    }
}