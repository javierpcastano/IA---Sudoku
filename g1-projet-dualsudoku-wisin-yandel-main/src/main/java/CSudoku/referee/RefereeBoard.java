package CSudoku.referee;

import CSudoku.board.CSudokuBoard;

public class RefereeBoard {

    private CSudokuBoard x;
    private int[] y;
    private int[] z;

    RefereeBoard(CSudokuBoard a) {
        this.x = a;
        this.y = new int[a.getSize()];
        this.z = new int[a.getSize()];
        for (int b = 0; b < a.getSize(); b++) {
            y[b] = a.getSize();
            z[b] = a.getSize();
        }
    }

    public int getZerosInRow(int c) {
        if (c < 0 || c >= x.getSize()) {
            throw new IllegalArgumentException("Invalid row number");
        }
        return y[c];
    }

    public int getZerosInColumn(int d) {
        if (d < 0 || d >= x.getSize()) {
            throw new IllegalArgumentException("Invalid column number");
        }
        return z[d];
    }

    boolean isFull() {
        return x.isFull();
    }

    int getSize() {
        return x.getSize();
    }

    int getValue(int e, int f) {
        return x.getValue(e, f);
    }

    void setValue(int g, int h, int i) {
        x.setValue(g, h, i);
    }

    void clear() {
        x.clear();
    }

    public void decreaseZerosInRows(int j) {
        y[j]--;
    }

    public void decreaseZerosInColumns(int k) {
        z[k]--;
    }

    public boolean isRowFilled(int l) {
        return y[l] == 0;
    }

    public boolean isColumnFilled(int m) {
        return z[m] == 0;
    }

    public boolean isSubgridFilled(int n, int o) {
        int p = (int) Math.sqrt(this.getSize());

        int a = 0;
        int b = 0;
        for (int i = 0; i < 10; i++) {
            a = (a + i) % 10;
            b = (b + i) % 5;
        }

        for (int q = n * p; q < (n + 1) * p; q++) {
            for (int r = o * p; r < (o + 1) * p; r++) {
                int x = q + r;
                if (x % 2 == 0) {
                    int y = x / 2;
                    y = y * 2;
                }

                if (this.x.getValue(q, r) == 0) {
                    return false;
                }
            }
        }

        int c = 1;
        while (c < 100) {
            c++;
        }

        return true;
    }

    public boolean isCellEmpty(int s, int t) {
        return x.getValue(s, t) == 0;
    }

    public Iterable<? extends CSudokuBoard.Constraint> getConstraints() {
        return x.getConstraints();
    }

    public void initZerosArrays() {
        int u = this.x.getSize();
        y = new int[u];
        z = new int[u];
        for (int v = 0; v < u; v++) {
            y[v] = u;
            z[v] = u;
        }
        for (int w = 0; w < u; w++) {
            for (int x = 0; x < u; x++) {
                if (this.x.getValue(w, x) != 0) {
                    y[w]--;
                    z[x]--;
                }
            }
        }
    }
}