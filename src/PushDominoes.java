import java.util.Stack;

public class PushDominoes {
    public static void main(String[] args) {
        String d = ".L..R..";
        
        PushDominoes test = new PushDominoes();
        String t = test.pushDominoes(d);
    }
    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() <= 1) return dominoes;
        
        char[] dom = dominoes.toCharArray();
        int prev = 0, cur = 0;
        Stack<Integer> stack = new Stack<>();
        
        while(cur < dom.length) {
            if (dom[cur] == 'L') {
                if (stack.isEmpty()) {
                    int i = cur;
                    while(i >= prev) dom[i--] = 'L';
                } else {
                    int right = stack.pop();
                    int i = cur;
                    while(i > right) {
                        dom[i--] = 'L';
                        dom[right++] = 'R';
                    }
                }
                prev = cur + 1;
            } else if (dom[cur] == 'R'){
                if (!stack.isEmpty()) {
                    int right = stack.pop();
                    while(right < cur) {
                        dom[right++] = 'R';
                    }
                    prev = cur + 1;
                }
                stack.push(cur);
            }
            cur++;
        }
        
        if (!stack.isEmpty()) {
            int right = stack.pop();
            while(right < dom.length)
                dom[right++] = 'R';
        }
        
        return new String(dom);
    }
}
