import java.util.List;
import java.util.LinkedList;;
import java.util.ArrayList;
import java.util.Iterator;

public class MissingRanges {
    public static void main(String[] args) {
        int[] arr = {-2147483648,-2147483648,0,2147483647,2147483647};
        
        MissingRanges test = new MissingRanges();
        List<String> res = test.findMissingRanges(arr, -2147483648, 2147483647);
    }
    
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int num : nums)
            list.add(num);
        
        if (list.size() == 0 || list.get(0) > lower)
            list.offerFirst(lower - 1);
        if (list.size() == 0 || list.get(list.size()-1) < upper)
            list.offerLast(upper + 1);
        
        List<String> res = new ArrayList<>();
        Iterator<Integer> it = list.iterator();
        
        int cur = it.next();
        int prev = cur;
        while(it.hasNext()) {
            cur = it.next();
            if (cur > prev + 1)  {
                if (cur == prev + 2) {
                    res.add("" + (prev + 1));
                } else {
                    res.add("" + (prev+1) + "->" + (cur-1));
                }
            }
            prev = cur;
        }
        return res;
    }
}
