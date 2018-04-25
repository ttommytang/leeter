import java.util.*;

class BinaryFactorTrees {
	public static void main(String[] args) {

	}

    public int numFactoredBinaryTrees(int[] A) {
        if (A == null || A.length == 0) {
        	return 0;
        }

        long res = 0;
        int MOD = 1_000_000_007;
        Arrays.sort(A);
        HashMap<Integer, Long> memo = new HashMap<>();

        for (int num : A) {
        	if (memo.containsKey(num)) {
        		continue;
        	}
        	long subs = 1;
        	for (int child : memo.keySet()) {
        		if (num % child == 0 && memo.containsKey(num / child)) {
        			subs += memo.get(num/child) * memo.get(child);
        		}
        	}
        	res += subs;
        	memo.put(num, subs);
        }

        return (int)res % MOD;
    }

}