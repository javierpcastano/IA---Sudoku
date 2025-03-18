package CSudoku.board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Sudoku board, with methods to initialize, print, and manage constraints between cells.
 */
public class CSudokuBoard {
    int[][] grid;
    private int size;
    private List<Constraint> constraints; // List of adjacency constraints (Consecutive)

    /**
     * Constructs a new Sudoku board with the specified size.
     *
     * @param size The size of the Sudoku grid (e.g., 9 for a 9x9 grid).
     */
    public CSudokuBoard(int size) {
        this.size = size;
        this.grid = new int[size][size];
        this.constraints = new ArrayList<>();
    }

    /**
     * Constructs a new Sudoku board by loading a prefilled grid from a file.
     *
     * @param prefilledGridPath The path to the file containing the prefilled grid.
     */
    public CSudokuBoard(String prefilledGridPath) {
        fromFile(prefilledGridPath);
    }

    /**
     * Checks whether a specific cell is empty.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return {@code true} if the cell is empty, otherwise {@code false}.
     */
    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    /**
     * Prints the current state of the Sudoku grid to the console.
     * The grid is displayed with borders and separators between cells.
     */
    public void printGrid() {
        String cornerTopLeft = "┌", cornerTopRight = "┐", cornerBottomLeft = "└", cornerBottomRight = "┘";
        String horizontal = "─", vertical = "│";
        String intersectionTop = "┬", intersectionBottom = "┴";

        int cellWidth = size > 9 ? 3 : 2; // Adjust for larger numbers
        String horizontalLine = horizontal.repeat(cellWidth);

        // Build the top and bottom borders of the grid
        StringBuilder topBorder = new StringBuilder(cornerTopLeft);
        StringBuilder bottomBorder = new StringBuilder(cornerBottomLeft);

        for (int col = 0; col < size; col++) {
            topBorder.append(horizontalLine).append(horizontalLine).append(col == size - 1 ? cornerTopRight : intersectionTop);
            bottomBorder.append(horizontalLine).append(horizontalLine).append(col == size - 1 ? cornerBottomRight : intersectionBottom);
        }

        System.out.println(topBorder);

        // Print grid row by row
        String verticalPrint = vertical;
        for (int i = 0; i < size; i++) {
            StringBuilder line = new StringBuilder(vertical);

            for (int j = 0; j < size; j++) {
                verticalPrint = (j < size - 1 && hasConsecutiveConstraint(i, j, i, j + 1)) ? " " : vertical;

                String cellValue = grid[i][j] == 0 ? "-" : String.valueOf(grid[i][j]);
                line.append(" ").append(String.format("%-" + cellWidth + "s", cellValue)).append(" ").append(verticalPrint);
            }
            System.out.println(line);

            // Print intermediate line or bottom line
            if (i < size - 1) {
                printMidBorder(i);
            } else {
                System.out.println(bottomBorder);
            }
        }
    }

    /**
     * Prints a middle border for the grid between rows.
     *
     * @param row The current row.
     */
    private void printMidBorder(int row) {
        StringBuilder midBorder = new StringBuilder("├");
        String horizontal = "─", intersectionMid = "┼";
        int cellWidth = size > 9 ? 3 : 2;
        String horizontalLine = horizontal.repeat(cellWidth);

        for (int col = 0; col < size; col++) {
            horizontalLine = hasConsecutiveConstraint(row, col, row + 1, col) ? " ".repeat(cellWidth) : horizontal.repeat(cellWidth);
            midBorder.append(horizontalLine).append(horizontalLine).append(col == size - 1 ? "┤" : intersectionMid);
        }
        System.out.println(midBorder);
    }

    /**
     * Retrieves the size of the Sudoku board.
     *
     * @return The size of the board.
     */
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the list of constraints on the board.
     *
     * @return The list of constraints.
     */
    public List<Constraint> getConstraints() {
        return constraints;
    }

    /**
     * Adds a new constraint to the board.
     *
     * @param constraint The constraint to add.
     */
    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    /**
     * Loads the Sudoku grid from a file.
     *
     * @param fileName The file containing the board data.
     * @return {@code true} if the board was successfully loaded, {@code false} otherwise.
     */
    public boolean fromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Read the first line to get the size of the grid
            if ((line = br.readLine()) == null) {
                System.out.println("The file is empty.");
                return false;
            }

            // Set the size of the board and initialize the grid
            this.size = Integer.parseInt(line.trim());
            this.grid = new int[size][size];
            this.constraints = new ArrayList<>();

            int row = 0;
            while ((line = br.readLine()) != null && row < size) {
                String[] cells = line.split("\\s+");
                int col = 0;

                // Fill the grid with values
                for (int i = 0; i < cells.length; i++) {
                    String cell = cells[i].trim();
                    if (cell.equals("+") || cell.equals("-")) {
                        if (cell.equals("+"))
                            this.addConsecutiveConstraint(row, col, row, col - 1);
                    } else {
                        if (!cell.isEmpty()) {
                            int value = Integer.parseInt(cell);
                            this.setValue(row, col, value);
                            col++;
                        }
                    }
                }

                // Add consecutive constraints for the horizontal separators ("+")
                if (row < size - 1) {
                    String nextLine = br.readLine();
                    if (nextLine != null) {
                        String[] nextCells = nextLine.split("\\s+");
                        for (int i = 0; i < nextCells.length; i++) {
                            if (nextCells[i].equals("+")) {
                                addConsecutiveConstraint(row, i, row + 1, i);
                            }
                        }
                    }
                }

                row++;
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading the file or processing the data.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds a consecutive constraint between two adjacent cells.
     *
     * @param row1 The row of the first cell.
     * @param col1 The column of the first cell.
     * @param row2 The row of the second cell.
     * @param col2 The column of the second cell.
     */
    public void addConsecutiveConstraint(int row1, int col1, int row2, int col2) {
        constraints.add(new Constraint(row1, col1, row2, col2));
    }

    /**
     * Checks if there is a consecutive constraint between two cells.
     *
     * @param row1 The row of the first cell.
     * @param col1 The column of the first cell.
     * @param row2 The row of the second cell.
     * @param col2 The column of the second cell.
     * @return {@code true} if a consecutive constraint exists, {@code false} otherwise.
     */
    public boolean hasConsecutiveConstraint(int row1, int col1, int row2, int col2) {
        for (Constraint constraint : constraints) {
            if (constraint.affectsCells(row1, col1, row2, col2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the board is full (i.e., no empty cells).
     *
     * @return {@code true} if the board is full, {@code false} otherwise.
     */
    public boolean isFull() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retrieves the value of a specific cell.
     *
     * @param i The row of the cell.
     * @param j The column of the cell.
     * @return The value of the cell.
     */
    public int getValue(int i, int j) {
        return grid[i][j];
    }

    /**
     * Sets the value of a specific cell.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param value The value to set in the cell.
     */
    public void setValue(int row, int col, int value) {
        grid[row][col] = value;
    }

    /**
     * Clears the board by resetting all cells to 0 (empty).
     */
    public void clear() {
        grid = new int[size][size];
    }

    /**
     * Represents a consecutive constraint between two cells.
     */
    public static class Constraint {
        public int row1;
        public int col1;
        public int row2;
        public int col2;

        /**
         * Constructs a new consecutive constraint.
         *
         * @param row1 The row of the first cell.
         * @param col1 The column of the first cell.
         * @param row2 The row of the second cell.
         * @param col2 The column of the second cell.
         */
        public Constraint(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }

        /**
         * Checks if this constraint affects the specified cells.
         *
         * @param row1 The row of the first cell.
         * @param col1 The column of the first cell.
         * @param row2 The row of the second cell.
         * @param col2 The column of the second cell.
         * @return {@code true} if this constraint affects the specified cells, {@code false} otherwise.
         */
        public boolean affectsCells(int row1, int col1, int row2, int col2) {
            return (this.row1 == row1 && this.col1 == col1 && this.row2 == row2 && this.col2 == col2) ||
                    (this.row1 == row2 && this.col1 == col2 && this.row2 == row1 && this.col2 == col1);
        }

        /**
         * Checks if the two values are consecutive (i.e., differ by exactly 1).
         *
         * @param value1 The value of the first cell.
         * @param value2 The value of the second cell.
         * @return {@code true} if the values are consecutive, {@code false} otherwise.
         */
        public boolean isConsecutive(int value1, int value2) {
            return Math.abs(value1 - value2) == 1;
        }
    }
}