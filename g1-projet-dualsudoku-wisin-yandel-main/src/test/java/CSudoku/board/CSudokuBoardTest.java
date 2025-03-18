package CSudoku.board;

import static org.junit.jupiter.api.Assertions.*;

import CSudoku.board.CSudokuBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class CSudokuBoardTest {

    private CSudokuBoard board;

    @BeforeEach
    void setUp() {
        board = new CSudokuBoard(9); // Initialize a 9x9 board for tests
    }

    // Test the constructor
    @Test
    void testConstructorSize() {
        assertEquals(9, board.getSize(), "The size of the board should be 9.");
    }

    // Test that the board initializes with empty cells (i.e., cells should be 0 initially)
    @Test
    void testIsCellEmpty() {
        assertTrue(board.isCellEmpty(0, 0), "Cell at (0,0) should be empty.");
        board.setValue(0, 0, 5);
        assertFalse(board.isCellEmpty(0, 0), "Cell at (0,0) should not be empty after setting a value.");
    }

    // Test getValue and setValue methods
    @Test
    void testSetGetValue() {
        board.setValue(1, 1, 7);
        assertEquals(7, board.getValue(1, 1), "Value at (1,1) should be 7.");
    }

    // Test clearing the board
    @Test
    void testClear() {
        board.setValue(1, 1, 5);
        board.clear();
        assertEquals(0, board.getValue(1, 1), "After clearing, the value at (1,1) should be 0.");
    }

    // Test adding and retrieving constraints
    @Test
    void testAddAndGetConstraints() {
        CSudokuBoard.Constraint constraint = new CSudokuBoard.Constraint(0, 0, 0, 1);
        board.addConstraint(constraint);
        List<CSudokuBoard.Constraint> constraints = board.getConstraints();
        assertTrue(constraints.contains(constraint), "The constraint should be in the list of constraints.");
    }

    // Test checking consecutive constraints
    @Test
    void testHasConsecutiveConstraint() {
        board.addConsecutiveConstraint(0, 0, 0, 1);
        assertTrue(board.hasConsecutiveConstraint(0, 0, 0, 1), "There should be a consecutive constraint between (0,0) and (0,1).");
        assertFalse(board.hasConsecutiveConstraint(1, 1, 1, 2), "There should be no consecutive constraint between (1,1) and (1,2).");
    }

    // Test the board's full status (empty and full board cases)
    @Test
    void testIsFull() {
        assertFalse(board.isFull(), "The board should not be full initially.");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.setValue(i, j, 1); // Fill the board
            }
        }
        assertTrue(board.isFull(), "The board should be full after filling all cells.");
    }

    // Test loading from a file (with a mock or real file for testing)
    @Test
    void testFromFile() {
        String validFilePath = "./src/main/resources/csudoku4_1.txt"; // A valid test file
        assertTrue(board.fromFile(validFilePath), "The board should load successfully from the file.");

        String invalidFilePath = "./src/main/resources/csudoku4_0.txt"; // An invalid test file
        assertFalse(board.fromFile(invalidFilePath), "The board should fail to load from an invalid file.");
    }

    // Test constraints for consecutive cells
    @Test
    void testConsecutiveConstraint() {
        board.addConsecutiveConstraint(0, 0, 0, 1);
        CSudokuBoard.Constraint constraint = board.getConstraints().get(0);
        assertTrue(constraint.isConsecutive(5, 6), "5 and 6 are consecutive.");
        assertFalse(constraint.isConsecutive(5, 7), "5 and 7 are not consecutive.");
    }
}