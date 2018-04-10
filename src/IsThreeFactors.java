public class IsThreeFactors {
    public static void main(String[] args) {
    
    }
    
    public boolean isThreeDisctFactors(long n) {
        // write your code here
        int sqrt = (int)Math.sqrt(n);
        if (n / sqrt != sqrt || n % sqrt != 0) return false;
        
        // Now check is sqrt is prime
        if (sqrt <= 2) return sqrt == 2;
        if (sqrt % 2 == 0) return false;
        for (int i = 3; i * i < sqrt; i += 2) {
            if (sqrt % i == 0)
                return false;
        }
        return true;
    }
}
