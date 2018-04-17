import java.util.*;

public class BigBusiness {
    public static void main(String[] args) {
        int[] a = {3,1,5};
        int[] b = {4,3,100};
        
        BigBusiness test = new BigBusiness();
        
        int money = test.bigBusiness(a,b,10);
    }
    /**
     * @param a: The cost of the film
     * @param b: The price of the selling of the film
     * @param k: The principal
     * @return: The answer
     */
    public int bigBusiness(int[] a, int[] b, int k) {
        // Write your code here
        if (a == null || b == null || a.length == 0 || b.length == 0 || a.length != b.length) return 0;
        
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            list.add(new Movie(a[i], b[i]));
        
        Collections.sort(list);
        
        int money = k, i = 0;
        while(i < list.size()) {
            Movie candidate = list.get(i);
            
            if (candidate.royalty > money) break;
            if (candidate.sell > candidate.royalty)
                money += (candidate.sell - candidate.royalty);
            i++;
        }
        
        return money;
    }
    
    static class Movie implements Comparable<Movie>{
        int royalty;
        int sell;
        
        public Movie(int r, int s) {
            royalty = r;
            sell = s;
        }
        
        public int compareTo(Movie other) {
            if (this.royalty == other.royalty) return this.sell - other.sell;
            else return this.royalty - other.royalty;
        }
    }
}
