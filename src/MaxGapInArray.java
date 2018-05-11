import java.util.*;

public class MaxGapInArray {
	public static void main(String[] args) {
	    MaxGapInArray test = new MaxGapInArray();
	    
	    int[] input = {18,2,8,3,5,10};
	    int max = test.maxGap(input);

	}

	public int maxGap(int[] arr) {
		if (arr == null || arr.length < 2) return 0;

		// Scan once to get the min and max value
		int len = arr.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int num : arr) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}

		// Build n-1 buckets based on the min and max value, actually, just need something to record the min/max of buckets
		int[][] buckets = new int[2][len-1];
		buckets[0] = new int[len-1];
		buckets[1] = new int[len-1];

		Arrays.fill(buckets[0], Integer.MAX_VALUE);
		Arrays.fill(buckets[1], Integer.MIN_VALUE);

		buckets[0][0] = min;
		buckets[1][len-2] = max;
		
		if (min == max) return 0;

		for (int num : arr) {
			int bucketIndex = (num - min) * (len - 1) / (max - min);
			if (bucketIndex == len - 1) bucketIndex--;
			buckets[0][bucketIndex] = Math.min(num, buckets[0][bucketIndex]);
			buckets[1][bucketIndex] = Math.max(num, buckets[1][bucketIndex]);
		}

		int empty = 0;
		for (; empty < len - 1; empty++) {
			if (buckets[0][empty] == Integer.MAX_VALUE && buckets[1][empty] == Integer.MIN_VALUE) break; 
		}

		// Since empty is assured to be in the middle instead of head or tail bucket
		return buckets[0][empty + 1] - buckets[1][empty - 1];
	}
}