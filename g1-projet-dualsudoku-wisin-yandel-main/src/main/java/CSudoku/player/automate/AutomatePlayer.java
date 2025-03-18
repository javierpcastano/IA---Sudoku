package CSudoku.player.automate;

import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import CSudoku.player.Player;
import CSudoku.player.MoveStrategy;

public class AutomatePlayer implements Player {
    private MoveStrategy strategy;

    public AutomatePlayer(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Move getMove(CSudokuBoard board) {
        return strategy.selectMove(board, this);
    }

    public String getMoveStrategyName() {
        return strategy.getName();
    }
}