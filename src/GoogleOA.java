import java.util.*;

public class GoogleOA {
    public static void main(String[] args) {
        GoogleOA test = new GoogleOA();
        
        // int t1 = test.repeatedStringMatch("a", "aa");
        // String t2 = test.nextClosestTime("23:59");
        String t3 = test.licenseKeyFormatting("2-5g-3-J", 2);
        
    }
    
    public int repeatedStringMatch(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) return 0;
        //if (A.length() >= B.length()) return A.contains(B) ? 1 : -1;
        int count = 1;
        StringBuilder S = new StringBuilder(A);
        while(S.length() < B.length()) {
            S.append(A);
            count++;
        }
        
        if (S.indexOf(B) > 0) return count;
        S.append(A);
        if (S.indexOf(B) > 0) return count + 1;
        else return -1;
    }
    
    public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length < 2) return -1;
        Set<Integer> bloosom = new HashSet<>();
        bloosom.add(flowers[0]);
        
        for (int day = 2; day <= flowers.length; day++) {
            int bloom = flowers[day - 1];
            bloosom.add(bloom);
            
            for (int i = 0; i < day - 1; i++) {
                if (Math.abs(bloom - flowers[i]) - 1 == k && checkRangeGarden(flowers[i], bloom, bloosom)) return day;
            }
        }
        return -1;
    }
    
    private boolean checkRangeGarden(int range1, int range2, Set<Integer> bloosom) {
        int min = Math.min(range1, range2), max = Math.max(range1, range2);
        for (int i = min + 1; i < max; i++) {
            if (bloosom.contains(i)) return false;
        }
        return true;
    }
    
    public int kEmptySlotsSW(int[] flowers, int k) {
        // Sliding window version of the algorithm
        if (flowers == null || flowers.length < 2) return -1;
        // First construct the days array which stores the date each flower bloom
        int[] dates = new int[flowers.length];
        
        for (int i = 0; i < flowers.length; i++) {
            dates[flowers[i] - 1] = i + 1;
        }
        
        // start a sliding window of inner length k;
        int ans = Integer.MAX_VALUE;
        int left = 0, right = k + 1;
        sliding: while(right < dates.length) {
            int leftDate = dates[left], rightDate = dates[right];
            
            for (int i = left + 1; i < right; i++) {
                if (dates[i] < Math.max(leftDate, rightDate)) {
                    left = i;
                    right = left + k + 1;
                    continue sliding;
                }
            }
            
            ans = Math.min(ans, Math.max(dates[left], dates[right]));
            left = right;
            right = left + k + 1;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public String nextClosestTime(String time) {
        // Assume input time string is always valid
        Set<Integer> digits = new HashSet<>();
        int[] originalTime = new int[4];
        int i = 0;
        for (char ch : time.toCharArray()) {
            if (ch != ':') {
                digits.add(ch - '0');
                originalTime[i++] = ch - '0';
            }
        }
        
        if (digits.size() == 1) return time;
        
        int[] exploreTime = new int[4];
        closetTime = "";
        minTimeDiff = Integer.MAX_VALUE;
        
        shuffleTime(exploreTime, originalTime, 0, digits);
        
        return closetTime;
    }
    
    private int minTimeDiff;
    private String closetTime;
    
    private void shuffleTime(int[] explore, int[] original, int shuffled, Set<Integer> digits) {
        if (shuffled >= 4) {
            int curDiff = timeDiff(original, explore);
            if (curDiff != 0 && curDiff < minTimeDiff) {
                minTimeDiff = curDiff;
                closetTime = toStringTime(explore);
            }
            return;
        }
        
        // Shuffle hour only 0,1,2 possible
        if (shuffled == 0) {
            for (int i = 0; i < 3; i++) {
                if (digits.contains(i)) {
                    explore[shuffled] = i;
                    shuffleTime(explore, original, shuffled + 1, digits);
                }
            }
        }
        
        if (shuffled == 1) {
            if (explore[0] == 2) {
                for (int i = 0; i < 4; i++) {
                    if (digits.contains(i)) {
                        explore[shuffled] = i;
                        shuffleTime(explore, original, shuffled + 1, digits);
                    }
                }
            } else {
                for (int digit : digits) {
                    explore[shuffled] = digit;
                    shuffleTime(explore, original, shuffled + 1, digits);
                }
            }
        }
    
        if (shuffled == 2) {
            for (int i = 0; i < 6; i++) {
                if (digits.contains(i)) {
                    explore[shuffled] = i;
                    shuffleTime(explore, original, shuffled + 1, digits);
                }
            }
        }
        
        if (shuffled == 3) {
            for (int digit : digits) {
                explore[shuffled] = digit;
                shuffleTime(explore, original, shuffled + 1, digits);
            }
        }
    }
    
    private String toStringTime(int[] time) {
        return "" + time[0] + time[1] + ":" + time[2] + time[3];
    }
    
    private int timeDiff(int[] original, int[] time) {
        int originalAsMin = 60 * (original[0] * 10 + original[1]) + (original[2] * 10 + original[3]);
        int timeAsMin = 60 * (time[0] * 10 + time[1]) + (time[2] * 10 + time[3]);
        
        if (originalAsMin > timeAsMin)
            return 1440 - originalAsMin + timeAsMin;
        else
            return timeAsMin - originalAsMin;
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    
    private int longestUniPath;
    public int longestUnivaluePath(TreeNode root) {
        longestUniPath = 0;
        longestUnivaluePathHelper(root);
        return longestUniPath;
    }
    
    private int longestUnivaluePathHelper(TreeNode root) {
        if (root == null) return 0;
        int left = longestUnivaluePathHelper(root.left);
        int right = longestUnivaluePathHelper(root.right);
        
        int leftPath = 0, rightPath = 0;
        if (root.left != null && root.val == root.left.val) leftPath = left + 1;
        if (root.right != null && root.val == root.right.val) rightPath = right + 1;
        
        longestUniPath = Math.max(longestUniPath, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
    
    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.length() == 0) return S;
        
        StringBuilder res = new StringBuilder();
        int sCursor = S.length() - 1, chunkCursor = 0;
        while(sCursor >= 0) {
            char sChar = S.charAt(sCursor--);
            if (sChar == '-') continue;
            
            if (chunkCursor == K) {
                res.insert(0, '-');
                res.insert(0, Character.toUpperCase(sChar));
                chunkCursor = 1;
            } else {
                res.insert(0, Character.toUpperCase(sChar));
                chunkCursor++;
            }
        }
        return res.toString();
    }
}
