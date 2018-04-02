import java.util.Arrays;

public class MaxProfitTree {
    public static void main(String[] args) {
        int[] x = {0,1,2,3,3,3,1,7,1}, y = {1,2,3,4,5,6,7,8,9}, cost = {47,48,44,95,84,61,51,86,43}, profit = {77,41,27,19,71,17,35,84,61,2};
        
        MaxProfitTree test = new MaxProfitTree();
        int res = test.getMaxScore(x,y,cost,profit);
    }
    /**
     * @param x: The vertex of edge
     * @param y: The another vertex of edge
     * @param cost: The cost of edge
     * @param profit: The profit of vertex
     * @return: Return the max score
     */
    public int getMaxScore(int[] x, int[] y, int[] cost, int[] profit) {
        // Write your code here
        if (x == null || y == null || x.length != y.length || x.length != cost.length || profit == null) return 0;
        
        int[] profitToNode = new int[profit.length];
        boolean[] reached = new boolean[profit.length];
        
        for (int i = 0; i < x.length; i++) {
            int from = reached[x[i]] ? profitToNode[x[i]] : profit[x[i]];
            profitToNode[y[i]] = from + profit[y[i]] - cost[i];
            reached[y[i]] = true;
        }
        
        for (int from : x)
            profitToNode[from] = Integer.MIN_VALUE;
        
        int maxRes = Integer.MIN_VALUE;
        for (int profitToLeaf : profitToNode) {
            maxRes = Math.max(maxRes, profitToLeaf);
        }
        
        return maxRes;
    }
}
