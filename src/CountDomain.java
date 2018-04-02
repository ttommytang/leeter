import java.util.*;

public class CountDomain {
    public static void main(String[] args) {
        CountDomain test = new CountDomain();
        String[] input = {"9001 discuss.leetcode.com"};
        
        List<String> res = test.subdomainVisits(input);
    }
    
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        if (cpdomains == null || cpdomains.length == 0) return res;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String cpdomain : cpdomains) {
            int space = cpdomain.indexOf(' ');
            int times = Integer.parseInt(cpdomain.substring(0, space));
            String url = cpdomain.substring(space+1);
            String[] tokens = url.split("\\.");
            
            StringBuilder domain = new StringBuilder();
            for (int i = tokens.length - 1; i >= 0; i--) {
                if (i != tokens.length - 1) domain.insert(0, ".");
                domain.insert(0, tokens[i]);
                
                map.put(domain.toString(), map.getOrDefault(domain.toString(), 0) + times);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String cp = "" + entry.getValue() + " " + entry.getKey();
            res.add(cp);
        }
        
        return res;
    }
}
