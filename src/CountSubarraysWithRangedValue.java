public class CountSubarraysWithRangedValue {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int count = 0, exc = 0, inc = 0;
        
        for(int num : A) {
            if (num > R) {
                count += (countSubarrays(inc) - countSubarrays(exc));
                inc = 0;
                exc = 0;
            } else if (num < L) {
                inc++; exc++;
            } else {
                count -= countSubarrays(exc);
                inc++;
                exc = 0;
            }
        }
        count = count + countSubarrays(inc) - countSubarrays(exc);
        
        return count;
    }
    
    private int countSubarrays(int len) {
        // Inclusive
        return (len + 1) * len / 2;
    }
}
