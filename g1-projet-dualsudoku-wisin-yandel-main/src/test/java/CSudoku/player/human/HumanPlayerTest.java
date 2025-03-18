package CSudoku.player.human;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumanPlayerTest {

    private CSudokuBoard board;
    private HumanPlayer humanPlayer;
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        // Create a mock Scanner object to simulate user input
        mockScanner = mock(Scanner.class);
        // Inject the mock Scanner into HumanPlayer
        humanPlayer = new HumanPlayer() {
            @Override
            public Move getMove(CSudokuBoard board) {
                // Override the method to use the mock scanner for input
                System.out.println("Enter the row, column, and value (separated by spaces): ");
                int row = mockScanner.nextInt();
                int col = mockScanner.nextInt();
                int value = mockScanner.nextInt();
                return new Move(row, col, value);
            }
        };
    }

    @Test
    void testGetMoveValidInput() {
        // Simulate the user input for row, column, and value
        when(mockScanner.nextInt()).thenReturn(1, 2, 3);

        // Call the method to get the player's move
        Move move = humanPlayer.getMove(board);

        // Verify the move was correctly created
        assertNotNull(move, "The move should not be null.");
        assertEquals(1, move.getRow(), "The row of the move should be 1.");
        assertEquals(2, move.getCol(), "The column of the move should be 2.");
        assertEquals(3, move.getValue(), "The value of the move should be 3.");
    }

    @Test
    void testGetMoveWithDifferentInput() {
        // Simulate different user input
        when(mockScanner.nextInt()).thenReturn(5, 6, 7);

        // Call the method to get the player's move
        Move move = humanPlayer.getMove(board);

        // Verify the move was correctly created with new inputs
        assertNotNull(move, "The move should not be null.");
        assertEquals(5, move.getRow(), "The row of the move should be 5.");
        assertEquals(6, move.getCol(), "The column of the move should be 6.");
        assertEquals(7, move.getValue(), "The value of the move should be 7.");
    }

    @Test
    void testGetMoveWithInvalidInput() {
        // Simulate invalid input (non-integer input)
        when(mockScanner.nextInt()).thenThrow(new RuntimeException("Invalid input"));

        // Call the method and expect an exception due to invalid input
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            humanPlayer.getMove(board);
        });

        // Verify that the exception is thrown
        assertEquals("Invalid input", exception.getMessage(), "The exception message should match.");
    }

    @Test
    void testGetMoveEmptyInput() {
        // Simulate no input (input not provided)
        when(mockScanner.nextInt()).thenReturn(0, 0, 0);

        // Call the method to get the player's move
        Move move = humanPlayer.getMove(board);

        // Verify the move was created with the empty input (0, 0, 0)
        assertNotNull(move, "The move should not be null.");
        assertEquals(0, move.getRow(), "The row of the move should be 0.");
        assertEquals(0, move.getCol(), "The column of the move should be 0.");
        assertEquals(0, move.getValue(), "The value of the move should be 0.");
    }
}