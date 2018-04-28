public class UnionFindWithPathCompression {
    // TODO: OOP design DSU
    public static void main(String[] args) {}
    
    // Use the technique of Union find with Rank and Path compression for un-directed graph circle detection
    static class DSU {
        int[] parent;
        int[] rank;
    
        /**
         * Return the very root of the target, also compress the path along searching
         * @param x - target vertex
         * @return root of the target
         */
        int find(int x) {
            if (parent[x] == x) return x;
            
            // This is a super clean code to do find as well as path compression -> link the target with the root
            return parent[x] = find(parent[x]);
        }
    
        /**
         * Union two vertices by rank, return false is already belong to same union, true if successfully union two separated unions
         * @param x - target 1
         * @param y - target 2
         * @return T - unions originally separated, F - vice versa
         */
        boolean union(int x, int y) { return true; }
        
        
        DSU(int v) {
            parent = new int[v];
            for (int i = 0; i < v; i++) parent[i] = i;
            rank = new int[v];
        }
    }
}
