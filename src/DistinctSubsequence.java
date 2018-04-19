import java.util.*;

class DistinctSubsequence {
	public static void main(String[] args) {
		DistinctSubsequence test = new DistinctSubsequence();

		String str1 = "rabbbit", str2 = "rabbit";

		int res1 = test.numDistinct(str1, str2);
		System.out.println(res1);
	}

	public int numDistinct(String s, String t) {
		if (s == null || t == null || s.length() == 0 || t.length() == 0) return 0;

		int sLen = s.length(), tLen = t.length();
		int[] dp = new int[tLen+1];

		dp[0] = 1;

		for (int i = 0; i < sLen; i++) {
			for (int j = tLen - 1; j >= 0; j--) {
				if (s.charAt(i) == t.charAt(j)) dp[j+1] += dp[j];
			}
		} 

		return dp[tLen];   	    
    }
}
