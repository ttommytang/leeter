import java.util.*;
import java.util.LinkedList;

public class Yelp {
    public static void main(String[] args) {
        Yelp test = new Yelp();
//        int[] arr = {100, 4, 200, 1, 3, 2};
//        int len = test.longestConsecutive(arr);
//
//        String[] dict = {"hot","dot","dog","lot","log","cog"};
//        List<String> words = new ArrayList<>(Arrays.asList(dict));
//
//        int jumps = test.ladderLength("hit", "cog", words);
        
//        int[] arr = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
//        int[] res = test.numberOfLines(arr, "abcdefghijklmnopqrstuvwxyz");
//        String[] arr = {"gin", "zen", "gig", "msg"};
//        int res = test.uniqueMorseRepresentations(arr);
        
        int[][] prereq = {{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}};
        boolean res = test.canFinish(4, prereq);
    }
    
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        char[] str = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;
        int from = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(str[i])) {
                maxLen = Math.max(maxLen, i - from + 1);
                set.add(str[i]);
            } else {
                while(str[from] != str[i]) {
                    set.remove(str[from]);
                    from++;
                }
                from++;
            }
        }
        
        return maxLen;
        
    }
    
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);
        
        int maxLen = Integer.MIN_VALUE;
        int curLen = 0;
        
        for (int num : nums) {
            if (set.contains(num - 1)) continue;
            
            curLen = 0;
            int head = num;
            while(set.contains(head)) {
                head++;
                curLen++;
            }
            
            maxLen = Math.max(curLen, maxLen);
            
        }
        
        return maxLen;
    }
    
    public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null || root.right == null) return 1;
        
        int left = countUnivalSubtrees(root.left);
        int right = countUnivalSubtrees(root.right);
        
        if(root.left != null && root.right != null) {
            return (root.val == root.left.val && root.val == root.right.val) ? left + right + 1 : left + right;
        } else if(root.left != null) {
            return (root.val == root.left.val) ? left + right + 1 : left + right;
        } else {
            return (root.val == root.right.val) ? left + right + 1 : left + right;
        }
    }
    
    private int wordDistance(String str1, String str2) {
        int countDiff = 0;
        
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) countDiff++;
        }
        
        return countDiff;
    }
    
    private Map<String, Set<String>> preGroup(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        wordList.add(endWord);
        
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String str : wordList)
            set.add(str);
        
        for (int i = 0; i < wordList.size(); i++) {
            String from = wordList.get(i);
            if (map.containsKey(from)) continue;
            else
                map.put(from, new HashSet<String>());
            
            char[] fromArr = from.toCharArray();
            
            for (int j = 0; j < fromArr.length; j++) {
                char mem = fromArr[j];
                
                for (char rep = 'a'; rep <= 'z'; rep++) {
                    if (rep != mem) {
                        fromArr[j] = rep;
                        String mani = new String(fromArr);
                        if (set.contains(mani))
                            map.get(from).add(mani);
                        
                    }
                }
                fromArr[j] = mem;
            }
        }
        
        return map;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 0;
        
        Map<String, Set<String>> map = preGroup(beginWord, endWord, wordList);
        
        int jumps = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        while(!queue.isEmpty()) {
            int vol = queue.size();
            jumps++;
            while(vol-- > 0) {
                String current = queue.poll();
                Set<String> nexts = map.get(current);
                for (String next : nexts) {
                    if (next.equals(endWord)) return jumps + 1;
                    queue.offer(next);
                }
            }
        }
        return 0;
    }
    
    public int[] numberOfLines(int[] widths, String S) {
        int[] res = new int[2];
        if(S == null || S.length() == 0) return res;
        
        int lines = 1;
        int count = 0;
        for(char ch : S.toCharArray()) {
            int toAdd = widths[ch - 'a'];
            
            if(count + toAdd > 100) {
                lines++;
                count = toAdd;
            } else
                count += toAdd;
        }
        
        res[0] = lines;
        res[1] = count;
        return res;
    }
    
    public int uniqueMorseRepresentations(String[] words) {
        String[] refer = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        Set<String> set = new HashSet<>();
        
        for (String str : words) {
            String encoded = "";
            
            for (char ch : str.toCharArray())
                encoded += refer[ch-'a'];
            
            set.add(encoded);
        }
        
        return set.size();
    }
    
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] topBottom = new int[grid[0].length];
        int[] leftRight = new int[grid.length];
        
        for (int i = 0; i < grid.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < grid[0].length; j++)
                max = Math.max(grid[i][j], max);
            
            leftRight[i] = max;
        }
        
        for (int j = 0; j < grid[0].length; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < grid.length; i++)
                max = Math.max(grid[i][j], max);
            topBottom[j] = max;
        }
        
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += (Math.min(topBottom[j], leftRight[i]) - grid[i][j]);
            }
        }
        
        return sum;
    }
    
    public boolean splitArraySameAverage(int[] A) {
        if (A == null || A.length <= 1) return false;
        return splitHelper(A, 0, 0, 0, 0, 0);
    }
    
    private boolean splitHelper(int[] A, int toPut, int sumB, int countB, int sumC, int countC) {
        if (toPut >= A.length) return false;
        
        // If last element to decide
        if (toPut == A.length - 1) {
            int tail = A[toPut];
            
            if (countB == 0)
                return (sumB + tail) * countC == sumC * (countB + 1);
            if (countC == 0)
                return (sumC + tail) * countB == sumB * (countC + 1);
            else
                return (sumB + tail) * countC == sumC * (countB + 1) || (sumC + tail) * countB == sumB * (countC + 1);
        }
    
        if (splitHelper(A, toPut + 1, sumB + A[toPut], countB + 1, sumC, countC)) return true;
        if (splitHelper(A, toPut + 1, sumB, countB, sumC + A[toPut], countC + 1)) return true;
        return false;
    }
    
    public boolean splitArraySameAverageFaster(int[] A) {
        // Mathematical relation we are searching for: sumB = SUM * countB / COUNT && sumB must be integer
        // -> Iterate through all possible countB -> make sure sumB is integer, then problem become searching for 'countB' sum up to 'sumB' problem
        if (A == null || A.length <= 1) return false;
        
        int SUM = 0, COUNT = A.length;
        for (int num : A) SUM += num;
        Arrays.sort(A);
        
        // Length of splitted array must be 1 <= countB < COUNT
        for (int countB = 1; countB <= COUNT/2; countB++) {
            // If sumB is not integer, this solution must not exist, continue
            if ((SUM * countB) % COUNT != 0) continue;
            
            int sumB = (SUM * countB) / COUNT;
            
            if (checkSumExists(A, sumB, countB, 0)) return true;
        }
        
        return false;
    }
    
    private boolean checkSumExists(int[] A, int targetSum, int targetCount, int start) {
        if (targetCount == 0) return targetSum == 0;
        if (A[start] * targetCount > targetSum) return false;
        
        // Since the array is sorted, we are kind looking for the smallest to put in the target subset, therefore we have to make sure there
        // are at least 'targetCount' number of elements left for us to choose from
        for (int i = start; i < A.length - targetCount + 1; i++) {
            if (checkSumExists(A, targetSum - A[i], targetCount - 1, i + 1)) return true;
        }
        return false;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1 || prerequisites == null || prerequisites.length <= 1) return true;
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        preGroup(prerequisites, graph);
        
        for (int i = 0; i < numCourses; i++) {
            if (!dfsCheckCircle(graph, new HashSet<Integer>(), i)) {
                return false;
            }
        }
        return true;
    }
    
    private void preGroup(int[][] edges, Map<Integer, Set<Integer>> map) {
        for (int[] edge : edges) {
            Set<Integer> vertices = map.getOrDefault(edge[0], new HashSet<Integer>());
            vertices.add(edge[1]);
            map.put(edge[0], vertices);
        }
    }
    
    private boolean dfsCheckCircle(Map<Integer, Set<Integer>> map, Set<Integer> visited, int current) {
        if(!map.containsKey(current)) return true;
        if(visited.contains(current)) return false;
        visited.add(current);
        for (int next : map.get(current)) {
            if (!dfsCheckCircle(map, visited, next)) return false;
        }
        visited.remove(current);
        return true;
    }
    
    public int countComponents(int n, int[][] edges) {
        if(n == 0) return 0;
        DSU dsu = new DSU(n);
        
        for (int[] edge : edges)
            dsu.union(edge[0], edge[1]);
        return dsu.countUnions();
    }
    
    static class DSU {
        int[] parents;
        int[] rank;
        
        public DSU(int size) {
            this.parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
            rank = new int[size];
        }
        
        public int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }
        
        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            
            if (rank[xParent] > rank[yParent])
                parents[yParent] = xParent;
            else if (rank[xParent] < rank[yParent])
                parents[xParent] = yParent;
            else {
                parents[yParent] = xParent;
                rank[xParent]++;
            }
        }
        
        public int countUnions() {
            Set<Integer> set = new HashSet<>();
            
            for (int parent : parents) {
                set.add(find(parent));
            }
            return set.size();
        }
    }
    
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.length == 0) return res;
        
        // Group tickets into graph
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        
        for (String[] ticket : tickets) {
            PriorityQueue<String> edges = graph.getOrDefault(ticket[0], new PriorityQueue<String>());
            edges.offer(ticket[1]);
            graph.put(ticket[0], edges);
        }
        
        dfsUntilAll(graph, res, "JFK");
        
        return res;
    }
    
    private void dfsUntilAll(Map<String, PriorityQueue<String>> graph, List<String> resIt, String current) {
        if (!graph.containsKey(current) || graph.get(current).size() == 0) {
            resIt.add(0, current);
            return;
        }
        
        while (graph.containsKey(current) && !graph.get(current).isEmpty())
            dfsUntilAll(graph, resIt, graph.get(current).poll());
    }
}
