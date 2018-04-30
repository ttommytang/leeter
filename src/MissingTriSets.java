public class MissingTriSets {
    public static void main(String[] args) {
        MissingTriSets test = new MissingTriSets();
        
        int[] arr1 = {2,2};
        int[] arr2 = {2,2,3,3,3};
        int[] arr3 = {3,3,3,2,2};
        int[] arr4 = {2,2,3,3,3,4,4,4};
        int[] arr5 = {2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9};
        
        int m1 = test.missingNumber(arr1);
        int m2 = test.missingNumber(arr2);
        int m3 = test.missingNumber(arr3);
        int m4 = test.missingNumber(arr4);
        int m5 = test.missingNumber(arr5);
    }
    /**
     * Given a contiguous sequence of numbers in which each number repeats thrice, there is exactly one missing number. Find the missing number.
     * eg: 11122333 : Missing number 2
     * 11122233344455666 Missing number 5
     */
    
    public int missingNumber(int[] arr) {
        if (arr == null || arr.length < 2 || arr.length % 3 != 2) return -1;
        
        int left = 0, right = arr.length - 1;
        
        while(left < right) {
            int L = arr[left], R = arr[right], M = L + (R-L) / 2;
            int expectMStart = left + 3*(M - L);
            
            if (expectMStart+2 <= right && arr[expectMStart] == M && arr[expectMStart+1] == M && arr[expectMStart+2] == M)
                left = expectMStart + 3;
            else if (arr[expectMStart] == M && arr[expectMStart+1] == M && (expectMStart+2 > right || arr[expectMStart+2] != M)) {
                // Missing number is < = M
                if (expectMStart == 0 || arr[expectMStart-1] == M-1) return M;
                else {
                    right = expectMStart - 2;
                }
            }
        }
        
        return arr[left];
    }
}
