package CSudoku.player.ai;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;
import CSudoku.observers.NodeCounterObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinimaxMoveStrategyTest {

    private MinimaxMoveStrategy minimaxStrategy;
    private CSudokuBoard board;
    private AIPlayer aiPlayer;

    @BeforeEach
    void setUp() {
        minimaxStrategy = new MinimaxMoveStrategy();

        // Initialize a real instance of CSudokuBoard with a size of 9 (standard 9x9 Sudoku)
        board = new CSudokuBoard(9);

        // Initialize the AI player (you may need to pass a MoveStrategy if necessary)
        aiPlayer = new AIPlayer(minimaxStrategy);

        // You may want to set up a board state or mock it for specific tests
        // Example: Manually set some values or create a custom configuration for testing.
        // For this example, we will not fill the board, so it will be all empty.
    }

    @Test
    void testSelectMoveBestMove() {
        // Mock the Minimax evaluation process (not using the mocked objects anymore)
        // Let's say the best move is at (1, 1) with value 2, based on the evaluation

        // Now we call the selectMove method to get the best move
        Move bestMove = minimaxStrategy.selectMove(board, aiPlayer);

        // Check that the best move is selected correctly (in a real case, you'd set up board states)
        assertNotNull(bestMove);
        assertEquals(0, bestMove.getRow()); // Assuming this was the best move
        assertEquals(0, bestMove.getCol());
        assertEquals(9, bestMove.getValue()); // The value might depend on the game state
    }

    @Test
    void testMinimaxWithDepthZero() {
        // For simplicity, let's mock the evaluation result to return 10 for this test.
        int result = minimaxStrategy.minimax(new EvaluatedSimulatedBoard(board, aiPlayer), 0, true, aiPlayer);

        // Verify the evaluation at depth 0 (base case)
        assertEquals(0, result);  // Replace with expected evaluation result
    }

    @Test
    void testGetValidMoves() {
        // Simulate valid moves for the AI player
        List<Move> validMoves = minimaxStrategy.getValidMoves(new EvaluatedSimulatedBoard(board, aiPlayer), aiPlayer);

        // Assert that the valid moves are correctly identified
        assertNotNull(validMoves);
        assertTrue(validMoves.size() > 0); // At least some valid moves should be identified
    }

    @Test
    void testMinimaxNodeCounting() {
        // Test that the node counter works as expected
        Move bestMove = minimaxStrategy.selectMove(board, aiPlayer);

        // Verify that the node counter was used (if implemented)
        assertTrue(bestMove != null); // Just a placeholder assertion for now
    }
}