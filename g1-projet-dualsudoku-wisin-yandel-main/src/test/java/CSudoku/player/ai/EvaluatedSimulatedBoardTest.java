package CSudoku.player.ai;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.ai.EvaluatedSimulatedBoard;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatedSimulatedBoardTest {

    private EvaluatedSimulatedBoard board;

    @BeforeEach
    void setUp() {
        board = new EvaluatedSimulatedBoard(9); // Initialize a 9x9 board
    }

    @Test
    void testInitialState() {
        // Verify initial values after board creation
        assertEquals(9, board.getSize());  // Board size
        for (int i = 0; i < board.getSize(); i++) {
            assertEquals(9, board.getZerosInRow(i));  // 9 zeros per row
            assertEquals(9, board.getZerosInColumn(i));  // 9 zeros per column
        }
    }

    // Parameterized test for the isFull method with different configurations
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 9}) // Test different grid sizes
    void testIsFull(int zerosInRow) {
        // Fill certain rows to test if the board is full
        for (int i = 0; i < board.getSize() - zerosInRow; i++) {
            for (int j = 0; j < board.getSize(); j++) {
                board.setValue(new Move(i, j, 1), true); // Fill with 1s
            }
        }
        assertEquals(zerosInRow == 0, board.isFull());
    }

    // Test the setValue method using different move scenarios
    @ParameterizedTest
    @MethodSource("provideMovesForSetValue")
    void testSetValue(Move move, int expectedEval) {
        // Apply a move and verify the evaluation
        board.setValue(move, true);
        assertEquals(expectedEval, board.getEval());
    }

    // Method to provide test parameters for setValue
    static Stream<Arguments> provideMovesForSetValue() {
        return Stream.of(
                Arguments.of(new Move(0, 0, 1), 1),   // Move 1
                Arguments.of(new Move(0, 1, 2), 2),   // Move 2
                Arguments.of(new Move(1, 0, 3), 3)    // Move 3
        );
    }

    // Test the getZerosInRow and getZerosInColumn methods with different values
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 8})
    void testGetZerosInRow(int row) {
        assertEquals(9, board.getZerosInRow(row));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 8})
    void testGetZerosInColumn(int col) {
        assertEquals(9, board.getZerosInColumn(col));
    }

    // Verify the copy method of the board
    @Test
    void testCopyBoard() {
        EvaluatedSimulatedBoard copiedBoard = new EvaluatedSimulatedBoard(board);

        // Verify if the board copy is correct
        assertEquals(board.getSize(), copiedBoard.getSize());
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                assertEquals(board.getValue(i, j), copiedBoard.getValue(i, j));
            }
        }
    }

    @Test
    void testIsRowFilled() {
        assertFalse(board.isRowFilled(0)); // An empty row should not be filled
        board.setValue(new Move(0, 0, 1), true); // Fill one cell
        assertFalse(board.isRowFilled(0)); // The row is still not filled
        for (int i = 1; i < board.getSize(); i++) {
            board.setValue(new Move(0, i, 1), true); // Fill the entire row
        }
        assertTrue(board.isRowFilled(0)); // The row should now be filled
    }

    // Test evaluation behavior for filled rows and columns
    @Test
    void testRowAndColumnFilledEval() {
        Move move = new Move(0, 0, 1); // A move to fill the cell
        board.setValue(move, true);
        int evalBefore = board.getEval();

        // Fill an entire row
        for (int i = 1; i < board.getSize(); i++) {
            move = new Move(0, i, 1);
            board.setValue(move, true);
            move = new Move(i, 0, 1);
            board.setValue(move, true);
        }

        assertTrue(board.isRowFilled(0)); // Verify that the row is filled
        assertTrue(board.isColumnFilled(0)); // Verify that the column is filled
    }
}