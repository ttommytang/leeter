public class MyDivision {
    public int divide(int dividend, int divisor) {
        // Pre-processing the input
        boolean invert = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long dividendL = Math.abs((long)dividend);
        long divisorL = Math.abs((long)divisor);
        
        if (divisor == 0) return dividend > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        if (dividend == 0 || dividendL < divisorL) return 0;
        
        long resL = divideHelper(dividendL, divisorL);
        
        if (resL > Integer.MAX_VALUE) {
            return invert ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else {
            return invert ? -(int)resL : (int)resL;
        }
        
    }
    
    private long divideHelper(long dividend, long divisor) {
        if(divisor > dividend) return 0;
        long times = 1, sum = divisor;
        
        while(sum + sum < dividend) {
            sum += sum;
            times += times;
        }
        
        return times + divideHelper(dividend - sum, divisor);
        
    }
}
