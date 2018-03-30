import java.util.*;

public class MedianTwoSortedArrays {
    public static void main(String[] args) {
        MedianTwoSortedArrays testy = new MedianTwoSortedArrays();
        // Test for #004
        int[] arrayA = {1,3};
        int[] arrayB = {2,4};

        assert (testy.findMedianSortedArrays(arrayA, arrayB) == 2.5);

        int[] arrayC = {};
        int[] arrayD = {2,4};
        assert (testy.findMedianSortedArrays(arrayC, arrayD) == 3);

        int[] arrayE = {5};
        int[] arrayF = {2,4,7,9};
        assert (testy.findMedianSortedArrays(arrayE, arrayF) == 5);
    }
    
    // Solution for LEETCODE #004 - Median of two sorted array
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1 != null ? nums1.length : 0;
        int n = nums2 != null ? nums2.length : 0;
        
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        
        return left == right ? findKthElementInMergedArray(nums1, 0, nums2, 0, left) : ((findKthElementInMergedArray(nums1, 0, nums2, 0, left) + findKthElementInMergedArray(nums1, 0, nums2, 0, right)) / 2.0);
    }
    
    private int findKthElementInMergedArray(int[] arrayA, int startA, int[] arrayB, int startB, int k) {
        // When either of the cursor has exceed the array
        if(startA >= arrayA.length) {
            return arrayB[startB + k - 1];
        }
        if(startB >= arrayB.length) {
            return arrayA[startA + k - 1];
        }
        
        // End case - searching for the first element in merged array
        if(k == 1) {
            return Math.min(arrayA[startA], arrayB[startB]);
        }
        
        int medianA = startA + k/2 - 1 < arrayA.length ? arrayA[startA + k/2 - 1] : Integer.MAX_VALUE;
        int medianB = startB + k/2 - 1 < arrayB.length ? arrayB[startB + k/2 - 1] : Integer.MAX_VALUE;
        
        if(medianA > medianB) {
            return findKthElementInMergedArray(arrayA, startA, arrayB, startB + k/2, k - k/2);
        } else {
            return findKthElementInMergedArray(arrayA, startA + k/2, arrayB, startB, k - k/2);
        }
    }
}
