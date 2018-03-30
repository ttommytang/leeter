import java.util.*;

public class Mar6th {
    public static void main(String[] args) {
        // Test cases go here
        
        Mar6th testy = new Mar6th();
        
//        // ==========================================================
//        // Test for #004
//        int[] arrayA = {1,3};
//        int[] arrayB = {2,4};
//
//        assert (testy.findMedianSortedArrays(arrayA, arrayB) == 2.5);
//
//        int[] arrayC = {};
//        int[] arrayD = {2,4};
//        assert (testy.findMedianSortedArrays(arrayC, arrayD) == 3);
//
//        int[] arrayE = {5};
//        int[] arrayF = {2,4,7,9};
//        assert (testy.findMedianSortedArrays(arrayE, arrayF) == 5);
//        // ==========================================================
//
//        int x = testy.mySqrtFast(2147395599);
//        // System.out.print("Sqrt of 2147395599 by my function is " + x);
//
//        // ==========================================================
//        int[] arrayToFindPeakA = {1};
//        int[] arrayToFindPeakB = {1, 2};
//        int[] arrayToFindPeakC = {3, 2, 1, 8};
//        int[] arrayToFindPeakD = {1, 3, 2};
//
//        assert (testy.findPeakElement(arrayToFindPeakA) == 0);
//        assert (testy.findPeakElement(arrayToFindPeakB) == 1);
//        assert (testy.findPeakElement(arrayToFindPeakC) == 0);
//        assert (testy.findPeakElement(arrayToFindPeakD) == 1);
        
//        int[] arr = {1, 0, -1, 0, -2, 2};
//
//        List<List<Integer>> res = testy.fourSum(arr, 0);
//
//        for (List<Integer> candidate : res) {
//            System.out.println(candidate);
//        }
        
//        int[] arr = {3, 4, -1, 1};
//
//        int res =  testy.firstMissingPositive(arr);
        
//        String input = "#6a7ca7";
//        String output = testy.similarRGB(input);
//        System.out.print(output);
        
        int[][] arr = {{1,0,0,0}, {1,1,1,0}};
        boolean ass1 = testy.checkBrick(arr, 1, 0);
        boolean ass2 = testy.checkBrick(arr, 1, 3);
        
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
    
    // Solution for LEETCODE #050 - Power
    public double myPow(double x, int n) {
        long N = n;
        if (n < 0) {
            x = 1/x;
            N = -N;
        }
    
        double ans = 1;
        double currentProduct = x;
    
        for (long i = N; i > 0; i = i/2) {
            if (i % 2 == 1) {
                ans = ans * currentProduct;
            }
            currentProduct *= currentProduct;
        }
        return ans;
    }
    
    // Solution for LEETCODE #153 - MIN IN ROTATED ARRAY
    public int findMin(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;
    
        int left = 0, right = nums.length - 1, mid = 0;
        while(left < right - 1) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[left] && nums[mid] > nums[right]) left = mid;
            else right = mid;
        }
        return Math.min(nums[left], nums[right]);
    }
    
    // Solution for LEETCODE #033 - SEARCH IN ROTATED ARRAY
    public int searchInRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length <= 0) return -1;
        
        // Find the rotate pivot first (min)
        int left = 0, right = nums.length - 1, mid;
        while(left < right) {
            mid = left + (right - left)/2;
            if (nums[mid] > nums[left]) left = mid + 1;
            else right = mid;
        }
        int pivot = left;
        
        left = 0;
        right = nums.length - 1;
        while(left <= right) {
            mid = left + (right - left)/2;
            int unrotatedMid = mid + pivot;
            if (nums[unrotatedMid] == target) return mid;
            if (nums[unrotatedMid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
    
    // Solution for LEETCODE #029. Divide Two Integers
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
    
    // Solution for LEETCODE #287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length < 2) return -1;
        int left = 1, right = nums.length - 1, mid;
        while(left < right) {
            mid = left + (right - left) / 2;
            int count = 0;
            for (int i : nums) {
                if ( i <= mid) count++;
            }
            
            if (count > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
    
    //
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
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return false;
        
        
        int row = 0, col = matrix[0].length - 1;
        
        while(row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }
    
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 0) return -1;
        
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            
            if (mid - 1 >= 0 && mid + 1 < nums.length) {
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
                else if(nums[mid] >= nums[mid - 1] && nums[mid + 1] >= nums[mid]) left = mid + 1;
                else right = mid - 1;
            } else if (mid + 1 < nums.length) {
                return nums[mid] > nums[right] ? mid : right;
            } else if (mid - 1 >= 0){
                return nums[mid] > nums[left]  ? mid : left;
            } else {
                return mid;
            }
        }
        
        return left;
    }
    
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length <= 0 || nums2.length <= 0)
            return new int[0];
        
        // Put numbers in nums1 array into a hashmap, recording the frequency as well
        java.util.Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Then for numbers in nums2 array, searching in the hashmap then add into the result array if find
        // also need to reduce the frequency to make sure that no more than what's presented in nums1 array
        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            if (freqMap.containsKey(num)) {
                result.add(num);
                
                if (freqMap.get(num) - 1 <= 0)
                    freqMap.remove(num);
                else
                    freqMap.put(num, freqMap.get(num) - 1);
            }
        }
        
        int[] res = new int[result.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
    
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(graph == null || graph.length == 0) return res;
        int numOfNodes = graph.length;
        
        List<Integer> path = new ArrayList<>();
        
        path.add(0);
        
        findPath(graph, 0, path, res);
        
        return res;
    }
    
    private void findPath(int[][] graph, int standAt, List<Integer> path, List<List<Integer>> res) {
        if (standAt == graph.length - 1) {
            res.add(new ArrayList<>(path));
        }
        
        for(int next : graph[standAt]) {
            path.add(next);
            findPath(graph, next, path, res);
            path.remove(path.size() - 1);
        }
        
    }
    
    public boolean rotateString(String A, String B) {
        if(A == null || B == null || A.length() != B.length()) return false;
        
        String concat = A + A;
        return concat.contains(B);
    }
//
//    public double champagneTower(int poured, int query_row, int query_glass) {
//        double[][] matrix = new double[query_row][query_glass];
//
//        for (int i = 1; i <= poured; i++) {
//            updateMatrix(matrix, 0, 0, 1.0);
//        }
//
//        return matrix[query_row][query_glass];
//    }
//
//    private void updateMatrix(double[][] matrix, int row, int col, double volume) {
//        if (row >= matrix.length || col >= matrix[0].length) return;
//
//        double extra = (matrix[row][col] + volume) - 1.0;
//
//        if (extra > 0) {
//            matrix[row][col] = 1.0;
//            updateMatrix(matrix, row + 1, col, 0.5*extra);
//            updateMatrix(matrix, row + 1, col + 1, 0.5*extra);
//
//        } else {
//            matrix[row][col] = matrix[row][col] + volume;
//        }
//    }
    
    public double champagneTower(int poured, int query_row, int query_glass) {
        if(query_glass > query_row) return 0;
        
        double[][] mem = new double[query_row + 1][query_row + 1];
        
        mem[0][0] = 1.0 * poured;
        
        for (int row = 0; row < query_row; row++) {
            for(int col = 0; col <= row; col++) {
                if(mem[row][col] > 1.0) {
                    double extra = mem[row][col] - 1.0;
                    mem[row][col] = 1.0;
                    
                    mem[row + 1][col] += 0.5 * extra;
                    mem[row + 1][col + 1] += 0.5 * extra;
                }
            }
        }
        
        return mem[query_row][query_glass] > 1.0 ? 1.0 : mem[query_row][query_glass];
    }
    
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int[] maxOnLeft = new int[arr.length];
        int[] minOnRight = new int[arr.length];
        
        maxOnLeft[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxOnLeft[i] = Math.max(maxOnLeft[i-1], arr[i]);
        }
        
        minOnRight[arr.length - 1] = arr[arr.length - 1];
        
        for (int j = arr.length - 2; j >= 0; j--) {
            minOnRight[j] = Math.min(minOnRight[j+1], arr[j]);
        }
        
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (maxOnLeft[i] <= minOnRight[i])
                count++;
        }
        
        return count + 1;
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
    
    private int partition(int[] nums) {
        // Helper method to seperate negative and positive numbers
        int left = 0, right = nums.length - 1;
        
        while(left <= right) {
            if (nums[left] > 0) left++;
            else nums[left] = nums[right--];
        }
        
        return left;
    }
    
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int numOfPositives = partition(nums);
        
        for(int i = 0; i < numOfPositives; i++) {
            int cursor = Math.abs(nums[i]);
            if(cursor <= numOfPositives && nums[cursor - 1] > 0) {
                nums[cursor - 1] *= -1;
            }
        }
        
        int i = 0;
        for(; i < numOfPositives; i++) {
            if(nums[i] > 0) break;
        }
        
        return i+1;
    }
    
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
    
    public boolean isSubSequence(String shortStr, String longStr) {
        if (shortStr.length() > longStr.length()) return false;
        
        int i = 0;
        for (char ch : longStr.toCharArray()) {
            if (i < shortStr.length() && ch == shortStr.charAt(i))
                i++;
        }
        
        return i == shortStr.length();
    }
    
    public String similarRGB(String color) {
        if(color == null || color.length() != 7) return "";
        char[] colorCharArray = color.toCharArray();
        
        String res = "#";
        for(int i = 0; i < 3; i++) {
            char high = colorCharArray[2*i + 1];
            char low = colorCharArray[2*(i + 1)];
            
            int highDigit = (high >= '0' && high <= '9') ? high - '0' : high - 'a' + 10;
            int lowDigit = (low >= '0' && low <= '9') ? low - '0' : low - 'a' + 10;
            
            int colorValue = highDigit * 16 + lowDigit;
            
            int mod = colorValue % 17;
            int div = colorValue / 17;
            if (div < 9)
                high = (char) ('0' + div + 1);
            else if (div == 9)
                high = 'a';
            else
                high = (char) ('a' + div - 9);
            
            if (mod >= 9)
                res += high;
            else
                res += high == 'a' ? '9' : (char)(high - 1);
        }
        
        return res;
        
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        
        for(int i = 0; i < graph.length; i++) {
            if(helper(graph, i))
                res.add(i);
        }
        
        return res;
        
    }
    
    private boolean helper(int[][] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while(queue.size() > 0) {
            int cur = queue.poll();
            if (cur == start) return false;
            
            for(int n : graph[cur]) {
                if(n == start) return false;
                queue.offer(n);
            }
        }
        
        return true;
        
    }
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] res = new int[hits.length];
        
        for(int[] hit : hits) {
            // For a single hit, update the grid as well as increment the count;
        }
        
        return res;
    }
    
    boolean checkBrick(int[][] grid, int row, int col) {
        if (grid[row][col] == 0) return false;
        if (grid[row][col] == 1 && row == 0) return true;
        
        boolean up = row - 1 >= 0 && checkBrick(grid, row - 1, col);
        boolean down = row + 1 < grid.length && checkBrick(grid, row + 1, col);
        boolean left = col >= 1 && checkBrick(grid, row, col - 1);
        boolean right = (col < grid[0].length - 1) && checkBrick(grid, row, col + 1);
        
        return up || down || left || right;
    }
    
    
    
    
}
