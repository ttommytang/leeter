import java.util.Set;
import java.util.HashSet;

public class ProductArr {
    public static void main(String[] args) {
        ProductArr test = new ProductArr();
        
        int[] arr = {1};
        
        int[] t = test.getProduct(arr);
    }
    
    public int[] getProduct(int[] arr) {
        // Write your code here
        if (arr == null || arr.length == 0)
            return arr;
        
        long prod = 1;
        Set<Integer> zeros = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                zeros.add(i);
            else
                prod *= arr[i];
        }
        
        int[] res = new int[arr.length];
        if (zeros.size() > 1) return res;
        else if (zeros.size() == 1) {
            if (res.length == 1) return res;
            res[zeros.iterator().next()] = (int)prod;
            return res;
        } else {
            for (int i = 0; i < arr.length; i++)
                res[i] = (int)(prod / arr[i]);
            return res;
        }
    }
}
