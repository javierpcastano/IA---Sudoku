package CSudoku.observers;

/**
 * A utility class for counting the number of nodes visited during a search or computation process.
 * Commonly used in algorithms like Minimax to track performance and complexity.
 */
public class NodeCounterObserver {

    private int count; // Tracks the number of nodes visited.

    /**
     * Resets the counter to zero.
     * Typically called before starting a new search or computation.
     */
    public void reset() {
        count = 0;
    }

    /**
     * Increments the counter by one.
     * Should be called whenever a node is visited during the computation.
     */
    public void increment() {
        count++;
    }

    /**
     * Returns the current count of nodes visited.
     *
     * @return The number of nodes visited so far.
     */
    public int getCount() {
        return count;
    }
}