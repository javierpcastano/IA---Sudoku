package CSudoku;

import CSudoku.player.Player;
import CSudoku.player.ai.AIPlayer;
import CSudoku.player.ai.AlphaBetaMoveStrategy;
import CSudoku.player.ai.MinimaxMoveStrategy;
import CSudoku.player.automate.FirstValidMoveStrategy;
import CSudoku.player.automate.RandomMoveNoValidationStrategy;
import CSudoku.player.automate.RandomMoveStrategy;
import CSudoku.player.automate.AutomatePlayer;
import CSudoku.player.human.HumanPlayer;
import CSudoku.referee.Referee;
import CSudoku.board.CSudokuBoard;
import CSudoku.board.Move;
import org.apache.commons.cli.*;

public class CSudokuGame {

    private CSudokuBoard board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Referee referee;

    public CSudokuGame(int boardSize, Player p1, Player p2, String prefilledGridPath) {
        if (prefilledGridPath != null) {
            board = new CSudokuBoard(prefilledGridPath);
        } else {
            board = new CSudokuBoard(boardSize);
        }
        player1 = p1;
        player2 = p2;
        currentPlayer = player1;
        referee = Referee.getInstance();
        referee.init(player1, player2, board);
    }

    private void displayBoard() {
        System.out.println("\n===============================");
        System.out.println("          Game Board           ");
        System.out.println("===============================");
        board.printGrid();
        System.out.println("===============================");
    }

    private void displayScores() {
        System.out.println("\n===============================");
        System.out.println("            Scores              ");
        System.out.println("===============================");

        System.out.println("Player 1: " + getPlayerInfo(player1) + " | Score: " + referee.getScore(player1));
        System.out.println("Player 2: " + getPlayerInfo(player2) + " | Score: " + referee.getScore(player2));

        System.out.println("===============================");
    }

    // Helper method to display player type and strategy
    private static String getPlayerInfo(Player player) {
        if (player instanceof HumanPlayer) {
            return "Human";
        } else if (player instanceof AIPlayer) {
            AIPlayer aiPlayer = (AIPlayer) player;
            return "AI (" + aiPlayer.getMoveStrategyName() + ")";
        } else if (player instanceof AutomatePlayer) {
            AutomatePlayer automatePlayer = (AutomatePlayer) player;
            return "Automate (" + automatePlayer.getMoveStrategyName() + ")";
        }
        return "Unknown Player";
    }

    public void play() {
        while (!referee.isGameOver()) {
            displayBoard();
            displayScores();

            System.out.println("\n=== " + (currentPlayer == player1 ? "Player 1's" : "Player 2's") + " turn ===");

            if (referee.outOfMoves()) {
                System.out.println("No valid moves available.");
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
                continue;
            }

            Move move = currentPlayer.getMove(board);

            if (move != null && referee.isValidMove(move)) {
                referee.applyMove(move);
                referee.addPoints(currentPlayer, move);
            } else {
                System.out.println("Invalid move. Turn skipped.");
                referee.applyPenalty(currentPlayer);
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        displayBoard();
        displayScores();
        referee.declareWinner();
    }

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption(new Option("g", "grid-size", true, "Size of the board (e.g., 4, 9, 16, etc.)"));
        options.addOption(new Option("p1", "player1", true, "Player 1: (1: human, 2: Random No Validation, 3: Random, 4: First valid, 5: Minimax, 6: AlphaBeta)"));
        options.addOption(new Option("p2", "player2", true, "Player 2: (1: human, 2: Random No Validation, 3: Random, 4: First valid, 5: Minimax, 6: AlphaBeta)"));
        options.addOption(new Option("f", "file", true, "Path to a pre-filled grid in .txt format"));

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("SudokuGame", options);
            System.exit(1);
            return;
        }

        int gridSize = cmd.hasOption("g") ? Integer.parseInt(cmd.getOptionValue("g")) : 4;
        String prefilledGridPath = cmd.getOptionValue("f");

        Player p1 = new AutomatePlayer(new FirstValidMoveStrategy());
        Player p2 = new AutomatePlayer(new RandomMoveStrategy());

        CSudokuGame game = new CSudokuGame(gridSize, p1, p2, prefilledGridPath);
        game.play();
    }

    private static Player configurePlayer(CommandLine cmd, String playerOption) {
        String playerType = cmd.hasOption(playerOption) ? cmd.getOptionValue(playerOption) : "1"; // Default to "1" (human)

        // Handle human player case
        if ("1".equals(playerType)) {
            return new HumanPlayer();
        }

        // Handle AI or automate player case with different strategies
        int aiStrategy = Integer.parseInt(playerType);

        return switch (aiStrategy) {
            case 2 -> // Random No Validation
                    new AutomatePlayer(new RandomMoveNoValidationStrategy());
            case 3 -> // Random
                    new AutomatePlayer(new RandomMoveStrategy());
            case 4 -> // First Valid
                    new AutomatePlayer(new FirstValidMoveStrategy());
            case 5 -> // Minimax
                    new AIPlayer(new MinimaxMoveStrategy());
            case 6 -> // AlphaBeta
                    new AIPlayer(new AlphaBetaMoveStrategy());
            default -> new HumanPlayer();  // Default to human player if an invalid option is given
        };
    }
}