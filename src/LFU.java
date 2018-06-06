import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
    public static void main(String[] args) {
        LFU lfu = new LFU(2);
        lfu.put(1,1);
        lfu.put(2,2);
        assert lfu.get(1) == 1;
        
        lfu.put(3,3);
        assert lfu.get(2) == -1;
        assert lfu.get(3) == 3;
        
        lfu.put(4,4);
        assert lfu.get(1) == -1;
        assert lfu.get(3) == 3;
        assert lfu.get(4) == 4;
    }
    
    private HashMap<Integer, Integer> map;
    private HashMap<Integer, LinkedHashSet<Integer>> cache;
    private HashMap<Integer, Integer> freqMap;
    private int cap;
    private int minFreq;
    
    public LFU(int c) {
        map = new HashMap<>();
        freqMap = new HashMap<>();
        cache = new HashMap<>();
        cap = c;
        minFreq = 0;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        updateFreqOfKey(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {
        if (cap == 0)
            return;
        if (map.size() >= cap) {
            // If the cache is already full, purge the LFR key
            purgeLFR();
        }
        // Put the pair and update the frequency of key
        map.put(key, value);
        updateFreqOfKey(key);
    }
    
    private void updateFreqOfKey(int key) {
        if (!freqMap.containsKey(key)) {
            freqMap.put(key, 1);
            
            cache.putIfAbsent(1, new LinkedHashSet<>());
            cache.get(1).add(key);
            
            // New comer, min frequency must be updated to 1 no matter what
            minFreq = 1;
        } else {
            int freq = freqMap.get(key);
            
            cache.get(freq).remove(key);
            cache.putIfAbsent(freq + 1, new LinkedHashSet<>());
            cache.get(freq + 1).add(key);
            
            freqMap.put(key, freq + 1);
            // update the min frequency
            if (freq == minFreq && cache.get(minFreq).size() == 0)
                minFreq++;
        }
    }
    
    private void purgeLFR() {
        // Remove the first element in the LinkedHashSet of minFreq
        int keyToRemove = cache.get(minFreq).iterator().next();
        
        map.remove(keyToRemove);
        freqMap.remove(keyToRemove);
        cache.get(minFreq).remove(keyToRemove);
    }
}
