import java.util.*;

public class KSpacedSorting {
    public static void main(String[] args) {
        KSpacedSorting test = new KSpacedSorting();
        
        int[] arr = {4,0,5,3,10};
        
        // int[] res = test.getSortedArray(arr, 2);
        String[] res = test.dataSegmentation("##s");
    }
    /**
     * @param arr: The K spaced array
     * @param k: The param k
     * @return: Return the sorted array
     */
    public int[] getSortedArray(int[] arr, int k) {
        // Write your code here
        if (arr == null || arr.length == 0 || k <= 1 || arr.length <= k) return arr;
        
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.value - o2.value;
            }
        });
        
        // Initialize the heap
        for (int i = 0; i < k; i++) minHeap.offer(new Entry(arr[i], i));
        
        int[] res = new int[arr.length];
        int idx = 0;
        
        while(!minHeap.isEmpty() && idx < arr.length) {
            Entry cur = minHeap.poll();
            res[idx++] = cur.value;
            if (cur.index + k < arr.length)
                minHeap.offer(new Entry(arr[cur.index + k], cur.index + k));
        }
        
        return res;
    }
    
    static class Entry {
        int value;
        int index;
        
        public Entry(int val, int idx) {
            value = val;
            index = idx;
        }
    }
    
    /**
     * @param str: The input string
     * @return: The answer
     */
    public String[] dataSegmentation(String str) {
        // Write your code here
        if (str == null || str.length() == 0) return new String[0];
        
        char[] strCh = str.toCharArray();
        int fast = 0, len = str.length();
        StringBuilder token = new StringBuilder();
        List<String> res = new ArrayList<>();
    
        do {
            while (fast < len && strCh[fast] == ' ') fast++;
            while (fast < len && (strCh[fast] < 'a' || strCh[fast] > 'z')) {
                res.add(Character.toString(strCh[fast++]));
            }
            while (fast < str.length() && strCh[fast] >= 'a' && strCh[fast] <= 'z') {
                token.append(strCh[fast++]);
            }
            if (token.length() != 0) {
                res.add(token.toString());
                token.setLength(0);
            }
        } while (fast != len);
        
        return res.toArray(new String[res.size()]);
    }
    
    /**
     * @param stones: a list of stones' positions in sorted ascending order
     * @return: true if the frog is able to cross the river or false
     */
    public boolean canCross(int[] stones) {
        // write your code here
        if (stones == null || stones.length < 2 || stones[1] - stones[0] > 1) return false;
        
        Map<Integer, HashSet<Integer>> jumps = new HashMap<>();
        for (int stone : stones) {
            jumps.put(stone, new HashSet<>());
        }
        
        jumps.get(stones[0]).add(1);
        for (int stone : stones) {
            Set<Integer> canJumps = jumps.get(stone);
            
            for (int jump : canJumps) {
                if (stone + jump == stones[stones.length - 1]) return true;
                
                int arrival = stone + jump;
                if (jumps.containsKey(arrival)) {
                    if (jump > 1)
                        jumps.get(arrival).add(jump - 1);
                    jumps.get(arrival).add(jump);
                    jumps.get(arrival).add(jump + 1);
                }
            }
        }
        
        return false;
    }
}
