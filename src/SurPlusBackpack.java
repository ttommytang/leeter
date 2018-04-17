import java.util.*;

public class SurPlusBackpack {
    public static void main(String[] args) {
        SurPlusBackpack test = new SurPlusBackpack();
        
        int[] a = {4,3};
        int[] b = {1,3,2};
        
        long res = test.getMaxValue(3, 2, 7, 2, 3, a, b);
    }
    /**
     * @param k1: The coefficient of A
     * @param k2: The  coefficient of B
     * @param c: The volume of backpack
     * @param n: The amount of A
     * @param m: The amount of B
     * @param a: The volume of A
     * @param b: The volume of B
     * @return: Return the max value you can get
     */
    public long getMaxValue(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
        // Write your code here
        List<Item> list = new ArrayList<>();
        
        for (int vol : a) list.add(new Item(vol, k1));
        for (int vol : b) list.add(new Item(vol, k2));
        Collections.sort(list);
        
        long[] dp = new long[c + 1];
        dp[0] = 0; dp[1] = 0;
    
        for (Item aList : list) {
            for (int v = c; v >= 0; v--) {
                if (v > aList.size)
                    dp[v] = Math.max(dp[v], dp[v - aList.size] + aList.param * (v - aList.size));
            }
        }
        
        return dp[c];
    }
    
    static class Item implements Comparable<Item>{
        int size;
        int param;
    
        public Item(int size, int param) {
            this.size = size;
            this.param = param;
        }
        
        public int compareTo(Item other) {
            if (this.size == other.size) return this.param - other.param;
            else return other.size - this.size;
        }
    }
    
    
}
