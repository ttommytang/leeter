import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        
        assert lfu.get(2) == -1;
        lfu.put(2, 6);
        assert lfu.get(1) == -1;
        lfu.put(1, 5);
        lfu.put(1, 2);
        assert lfu.get(1) == 2;
        int l = lfu.get(2);
        assert l == -1;
    }
    
    class Node {
        int key, val, cnt;
        Node prev, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            cnt = 1;
        }
    }
    
    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, LinkedHashSet<Integer>> countMap;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;
        Node node = nodeMap.get(key);
        update(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            update(node);
        }
        else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) {
                LinkedHashSet<Integer> set = countMap.get(min);
                int last = set.iterator().next();
                nodeMap.remove(last);
                set.remove(last);
                size--;
            }
            size++;
            min = 1;
            LinkedHashSet<Integer> newSet = countMap.getOrDefault(node.cnt, new LinkedHashSet<>());
            newSet.add(node.key);
            countMap.put(node.cnt, newSet);
        }
    }
    
    private void update(Node node) {
        LinkedHashSet<Integer> set = countMap.get(node.cnt);
        set.remove(node.key);
        if (node.cnt == min && set.size() == 0) min++;
        node.cnt++;
        LinkedHashSet<Integer> newSet = countMap.getOrDefault(node.cnt, new LinkedHashSet());
        newSet.add(node.key);
        countMap.put(node.cnt, newSet);
    }
}
