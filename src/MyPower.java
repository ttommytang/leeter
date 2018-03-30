import java.util.*;

public class MyPower {
    public static void main(String[] args) {
        MyPower test = new MyPower();
        
        assert test.myPow(20, 3) == 8000;
    }
    
    // Solution for LEETCODE #050 - Power
    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) {
            x = 1/x;
            N = -N;
        }
        
        double ans = 1;
        double currentProduct = x;
        
        for (long i = N; i > 0; i = i/2) {
            if (i % 2 == 1) {
                ans = ans * currentProduct;
            }
            currentProduct *= currentProduct;
        }
        return ans;
    }
}
