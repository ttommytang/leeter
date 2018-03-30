import java.util.*;

public class FourSumReturnIndices {
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
            if (this.sum == p.sum) return 0;
            return ((this.sum - p.sum) > 0) ? 1 : -1;
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
                
                long hash = hashIndices(indices, len);
                
                if (!hashedIndices.contains(hash)) {
                    List<Integer> candidate = new ArrayList<>();
                    for (int idx : indices) {
                        candidate.add(nums[idx]);
                    }
                    
                    res.add(candidate);
                    hashedIndices.add(hash);
                }
                
                if (left + 1 < biPairs.length && biPairs[left + 1].sum == biPairs[left].sum) {
                    left++;
                    continue;
                }
                
                if (right - 1 >= 0 && biPairs[right - 1].sum == biPairs[right].sum) {
                    right--;
                    continue;
                }
                
                left++;
                right--;
                
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
