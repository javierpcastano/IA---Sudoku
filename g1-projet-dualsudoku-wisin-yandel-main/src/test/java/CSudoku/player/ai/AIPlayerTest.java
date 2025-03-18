package CSudoku.player.ai;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.MoveStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AIPlayerTest {
    private AIPlayer aiPlayer;
    private MoveStrategy mockStrategy;
    private CSudokuBoard mockBoard;

    @BeforeEach
    void setUp() {
        mockStrategy = mock(MoveStrategy.class);
        aiPlayer = new AIPlayer(mockStrategy);
        mockBoard = mock(CSudokuBoard.class);
    }

    @Test
    void testConstructorSetsStrategy() {
        assertNotNull(aiPlayer);
        assertEquals(mockStrategy, aiPlayer.getMoveStrategy(), "Move strategy should be set during construction.");
    }

    @Test
    void testSetMoveStrategy() {
        MoveStrategy newStrategy = mock(MoveStrategy.class);
        aiPlayer.setMoveStrategy(newStrategy);
        assertEquals(newStrategy, aiPlayer.getMoveStrategy(), "Move strategy should be updated.");
    }

    @Test
    void testGetMoveDelegatesToStrategy() {
        Move expectedMove = new Move(0, 0, 1);
        when(mockStrategy.selectMove(mockBoard, aiPlayer)).thenReturn(expectedMove);

        Move move = aiPlayer.getMove(mockBoard);

        assertNotNull(move, "The move should not be null.");
        assertEquals(expectedMove, move, "The move should be the one selected by the strategy.");
        verify(mockStrategy).selectMove(mockBoard, aiPlayer);
    }

    @Test
    void testIsValidMoveValidCase() {
        Move move = new Move(0, 0, 1);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(anyInt(), anyInt())).thenReturn(0);
        when(mockBoard.hasConsecutiveConstraint(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false);

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertTrue(result, "The move should be valid.");
    }

    @Test
    void testIsValidMoveRowConflict() {
        Move move = new Move(0, 0, 1);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(0, 1)).thenReturn(1); // Conflict in the same row

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertFalse(result, "The move should be invalid due to row conflict.");
    }

    @Test
    void testIsValidMoveColumnConflict() {
        Move move = new Move(0, 0, 1);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(1, 0)).thenReturn(1); // Conflict in the same column

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertFalse(result, "The move should be invalid due to column conflict.");
    }

    @Test
    void testIsValidMoveSubgridConflict() {
        Move move = new Move(1, 1, 5);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(0, 0)).thenReturn(5); // Conflict in the same subgrid

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertFalse(result, "The move should be invalid due to subgrid conflict.");
    }

    @Test
    void testIsValidMoveConsecutiveConstraintConflict() {
        Move move = new Move(0, 0, 5);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(0, 1)).thenReturn(7); // Adjacent cell has a value
        when(mockBoard.hasConsecutiveConstraint(0, 0, 0, 1)).thenReturn(true);

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertFalse(result, "The move should be invalid due to consecutive constraint conflict.");
    }

    @Test
    void testIsValidMoveNoConsecutiveConstraint() {
        Move move = new Move(0, 0, 5);
        when(mockBoard.getSize()).thenReturn(9);
        when(mockBoard.getValue(anyInt(), anyInt())).thenReturn(0);
        when(mockBoard.hasConsecutiveConstraint(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false);

        boolean result = aiPlayer.isValidMove(mockBoard, move);

        assertTrue(result, "The move should be valid as there are no consecutive constraints.");
    }

}