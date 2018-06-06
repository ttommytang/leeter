import java.util.TreeMap;

public class GroupCards {
    public static void main(String[] args) {
        int[] cards = {1,2,3,6,2,3,4,7,8};
        GroupCards test = new GroupCards();
        
        boolean res = test.isNStraightHand(cards, 3);
    }
    
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0 || hand.length % W != 0)
            return false;
    
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : hand) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
    
        int head = map.ceilingKey(0);
        while(map.size() > 0) {
            for (int i = 0; i < W; i++) {
                if (!map.containsKey(head+i))
                    return false;
                else if (map.get(head+i) <= 1)
                    map.remove(head+i);
                else
                    map.put(head+i, map.get(head+i)-1);
            }
            if (map.size() > 0)
                head = map.ceilingKey(head);
        }
        return true;
    }
}
