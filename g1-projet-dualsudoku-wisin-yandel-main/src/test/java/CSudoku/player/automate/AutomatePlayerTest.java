package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.MoveStrategy;
import CSudoku.player.automate.AutomatePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AutomatePlayerTest {

    private CSudokuBoard board;
    private MoveStrategy strategy;
    private AutomatePlayer automatePlayer;

    @BeforeEach
    void setUp() {
        // Create a mock of the Sudoku board
        board = mock(CSudokuBoard.class);
        // Create a mock of the strategy
        strategy = mock(MoveStrategy.class);
        // Create an instance of AutomatePlayer with a strategy
        automatePlayer = new AutomatePlayer(strategy);
    }

    @Test
    void testGetMoveWithValidMove() {
        // Simulate a valid move
        Move move = new Move(0, 0, 5);
        when(strategy.selectMove(board, automatePlayer)).thenReturn(move);

        // Call the getMove method
        Move result = automatePlayer.getMove(board);

        // Verify that the returned move is the one we simulated
        assertNotNull(result, "The move should not be null.");
        assertEquals(move, result, "The returned move should match the simulated move.");
    }

    @Test
    void testGetMoveWhenStrategyReturnsNull() {
        // Simulate a situation where the strategy returns null (no valid move)
        when(strategy.selectMove(board, automatePlayer)).thenReturn(null);

        // Call the getMove method
        Move result = automatePlayer.getMove(board);

        // Verify that the move is null (no move selected)
        assertNull(result, "The returned move should be null when no valid move exists.");
    }

    @Test
    void testGetMoveWithMultipleCalls() {
        // Simulate different moves to test multiple calls
        Move firstMove = new Move(0, 0, 5);
        Move secondMove = new Move(1, 1, 3);

        when(strategy.selectMove(board, automatePlayer))
                .thenReturn(firstMove) // First call
                .thenReturn(secondMove); // Second call

        // Call getMove multiple times and check the results
        Move firstResult = automatePlayer.getMove(board);
        Move secondResult = automatePlayer.getMove(board);

        // Verify that the results are different (moves should be distinct)
        assertEquals(firstMove, firstResult, "The first move does not match.");
        assertEquals(secondMove, secondResult, "The second move does not match.");
    }

    @Test
    void testStrategyInteraction() {
        // Create a simulated move
        Move move = new Move(0, 0, 5);

        // Simulate the call to selectMove in the strategy
        when(strategy.selectMove(board, automatePlayer)).thenReturn(move);

        // Call the getMove method
        automatePlayer.getMove(board);

        // Verify that the strategy was called with the correct parameters
        verify(strategy).selectMove(board, automatePlayer);
    }
}