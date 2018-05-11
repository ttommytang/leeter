import java.util.*;

public class AddOperators {
    public static void main(String[] args) {
        AddOperators test = new AddOperators();
        
        String num = "2147483648";
        List<String> res = test.addOperators(num, -2147483648);
        
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        
        HashSet<String> set = new HashSet<>();
        addOperatorsUtil(num, 0, target, "", set, 0, 0);
        
        res.addAll(set);
        return res;
    }
    
    private void addOperatorsUtil(String num, int pos, int target, String cur, HashSet<String> res, long multi, long value) {
        if (pos >= num.length() && value == target) {
            res.add(cur);
            return;
        }
        
        long operand = 0;
        for (int i = pos; i < num.length(); i++) {
            operand *= 10;
            operand += num.charAt(i) - '0';
            
            // '06' kind of operand is invalid
            if (num.charAt(pos) == '0' && i > pos) break;
            
            if (pos == 0) {
                // Append the very first operand and go on
                addOperatorsUtil(num, i+1, target, cur + operand, res, operand, operand);
            } else {
                // Not first operand, need to consider different operations
                addOperatorsUtil(num, i+1, target, cur + '+' + operand, res, operand, value + operand);
                addOperatorsUtil(num, i+1, target, cur + '-' + operand, res, -operand, value - operand);
                addOperatorsUtil(num, i+1, target, cur + '*' + operand, res, multi * operand, value - multi + operand * multi);
            }
        }
    }
    
    /* Seeems right but cannot handle the multiply situation, cause it's not looking for a target anymore*/
//    private Set<String> addOperatorsUtil(String num, int lastNum, int target, HashMap<String, HashSet<String>> memo) {
//        String memoKey = "" + lastNum + "=" + target;
//        if (memo.containsKey(memoKey))
//            return memo.get(memoKey);
//
//        HashSet<String> res = new HashSet<>();
//        for (int i = 0; i < lastNum; i++) {
//            String lastOp = num.substring(i, lastNum);
//            int lastOpV = Integer.parseInt(lastOp);
//
//            if (String.valueOf(lastOpV).length() != lastOp.length()) continue;
//
//            if (i == 0) {
//                if (target == lastOpV)
//                    res.add(lastOp);
//                continue;
//            }
//
//            Set<String> plus = addOperatorsUtil(num, i, target - lastOpV, memo);
//            for (String pre : plus) {
//                String exp = pre + "+" + lastOp;
//                res.add(exp);
//            }
//
//            Set<String> minus = addOperatorsUtil(num, i, target + lastOpV, memo);
//            for (String pre : minus) {
//                String exp = pre + "-" + lastOp;
//                res.add(exp);
//            }
//
//            if ((lastOpV == 0 && target == 0) || target % lastOpV == 0) {
//                Set<String> multi = addOperatorsUtil(num, i, target / lastOpV, memo);
//                for (String pre : multi) {
//                    String exp = pre + "*" + lastOp;
//                    res.add(exp);
//                }
//            }
//        }
//
//        memo.put(memoKey, res);
//        return res;
//    }
}
