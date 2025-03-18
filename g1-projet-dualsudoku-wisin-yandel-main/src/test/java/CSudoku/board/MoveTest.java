package CSudoku.board;

import static org.junit.jupiter.api.Assertions.*;

import CSudoku.board.Move;
import org.junit.jupiter.api.Test;

class MoveTest {

    // Test the primary constructor
    @Test
    void testConstructor() {
        Move move = new Move(3, 4, 5);
        assertEquals(3, move.getRow(), "The row should be 3.");
        assertEquals(4, move.getCol(), "The column should be 4.");
        assertEquals(5, move.getValue(), "The value should be 5.");
    }

    // Test the copy constructor
    @Test
    void testCopyConstructor() {
        Move originalMove = new Move(2, 2, 8);
        Move copiedMove = new Move(originalMove);

        // Ensure the copied move has the same values as the original
        assertEquals(originalMove.getRow(), copiedMove.getRow(), "The row should be copied correctly.");
        assertEquals(originalMove.getCol(), copiedMove.getCol(), "The column should be copied correctly.");
        assertEquals(originalMove.getValue(), copiedMove.getValue(), "The value should be copied correctly.");
    }

    // Test the getRow() method
    @Test
    void testGetRow() {
        Move move = new Move(5, 6, 9);
        assertEquals(5, move.getRow(), "getRow should return the correct row index.");
    }

    // Test the getCol() method
    @Test
    void testGetCol() {
        Move move = new Move(5, 6, 9);
        assertEquals(6, move.getCol(), "getCol should return the correct column index.");
    }

    // Test the getValue() method
    @Test
    void testGetValue() {
        Move move = new Move(5, 6, 9);
        assertEquals(9, move.getValue(), "getValue should return the correct value.");
    }

    // Test the toString() method
    @Test
    void testToString() {
        Move move = new Move(7, 8, 3);
        String expectedString = "Move{row=7, col=8, value=3}";
        assertEquals(expectedString, move.toString(), "The toString method should return the correct string representation.");
    }
}