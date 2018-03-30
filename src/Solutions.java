import apple.laf.JRSUIUtils;

import java.util.*;

public class Solutions {
    public static void main(String[] args) {
        Solutions test = new Solutions();

        int[] arr = {1, 0, -1, 0, -2, 2};

        List<List<Integer>> res = test.fourSum(arr, 0);

        assert res.size() == 3;
    }

    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);

        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSumSmaller(nums, i+1, nums.length-1, target-nums[i]);
        }
        return count;

    }

    private static int twoSumSmaller(int[] nums, int left, int right, int target) {
        int count = 0;

        while(left < right) {
            int curSum = nums[left] + nums[right];

            if (curSum < target) {
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    class Pair implements Comparable<Pair>{
        int indexA;
        int indexB;
        long sum;

        public Pair(int left, int right, long sum) {
            this.indexA = left;
            this.indexB = right;
            this.sum = sum;
        }

        public boolean isOverlapper(Pair p) {
            return p.indexA == this.indexA || p.indexA == this.indexB || p.indexB == this.indexA || p.indexB == this.indexB;
        }

        public int compareTo(Pair p) {
            return (this.sum - p.sum) > 0 ? 1 : -1;
        }
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return res;

        // Initialize the bi-sum pair array
        int len = nums.length;
        Pair[] biPairs = new Pair[len*(len - 1)/2];
        int cursor = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                Pair newPair = new Pair(i, j, (long)nums[i] + (long)nums[j]);
                biPairs[cursor++] = newPair;
            }
        }

        // Sort the bi-sums according to the sum value
        Arrays.sort(biPairs);

        // Then leverage the thought of 2-sum to solve the problem
        // Note to exclude the overlapping pairs
        int left = 0, right = biPairs.length - 1;
        Set<Long> hashedIndices = new HashSet<>();
        while(left < right) {
            long curSum = biPairs[left].sum + biPairs[right].sum;

            if ((long)target - curSum == 0 && !biPairs[left].isOverlapper(biPairs[right])) {
                List<Integer> indices = new ArrayList<>();

                indices.add(biPairs[left].indexA);
                indices.add(biPairs[left].indexB);
                indices.add(biPairs[right].indexA);
                indices.add(biPairs[right].indexB);

                Collections.sort(indices);

                if (!hashedIndices.contains(hashIndices(indices, len)))
                    res.add(indices);
            } else if ((long)target - curSum > 0)
                left++;
            else
                right--;

        }
        return res;
    }

    private long hashIndices(List<Integer> indices, int len) {
        long hash = 0;
        for (Integer indice : indices) hash = hash * len + indice;
        return hash;
    }
}
