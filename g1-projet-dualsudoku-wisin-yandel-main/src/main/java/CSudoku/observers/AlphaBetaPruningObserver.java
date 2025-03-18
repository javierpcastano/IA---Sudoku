package CSudoku.observers;

public class AlphaBetaPruningObserver {

    private int alphaCutCount = 0; // Nombre de coupes Alpha
    private int betaCutCount = 0;  // Nombre de coupes Beta
    private int nodeCount = 0;     // Nombre de nœuds visités

    /**
     * Incrémente le nombre de nœuds visités.
     */
    public void incrementNodeCount() {
        nodeCount++;
    }

    /**
     * Incrémente le nombre de coupes Alpha.
     */
    public void incrementAlphaCut() {
        alphaCutCount++;
    }

    /**
     * Incrémente le nombre de coupes Beta.
     */
    public void incrementBetaCut() {
        betaCutCount++;
    }

    /**
     * Affiche le nombre de nœuds visités et de coupes.
     */
    public void printStats() {
        System.out.println("Alpha cuts: " + alphaCutCount);
        System.out.println("Beta cuts: " + betaCutCount);
        System.out.println("Nodes visited: " + nodeCount);
    }

    /**
     * Réinitialise les compteurs.
     */
    public void reset() {
        alphaCutCount = 0;
        betaCutCount = 0;
        nodeCount = 0;
    }

    // Getters pour accéder aux valeurs si nécessaire
    public int getAlphaCutCount() {
        return alphaCutCount;
    }

    public int getBetaCutCount() {
        return betaCutCount;
    }

    public int getNodeCount() {
        return nodeCount;
    }
}