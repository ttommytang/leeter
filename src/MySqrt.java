public class MySqrt {
    public static void main(String[] args) {
    
    }
    
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int left = 1, right = x / 2;
        while(left < right - 1) {
            int mid = left + (right - left) / 2;
            long currentPower = ((long)mid) * ((long)mid);
            
            if (currentPower == x) return mid;
            else if (currentPower < x) left = mid;
            else right = mid;
        }
        
        return ((long)right) * ((long)right) <= x ? right : left;
        
    }
    
    // A much faster version, get rid of all the casting between int and long
    public int mySqrtFast(int x) {
        if (x <= 0) return 0;
        int left = 1, right = Integer.MAX_VALUE;
        
        while(true) {
            int mid = left + (right - left) / 2;
            
            // In avoid that result of multiplying exceeds the MAX VALUE of int, we use division
            if (mid > x / mid) right = mid - 1;
            else {
                // Not looking for exact equal, just looking for a lower range
                if ((mid + 1) > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
}
