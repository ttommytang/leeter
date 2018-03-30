import java.util.*;

public class TreeKSmall {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            value = x;
        }
    }

    public List<Integer> findKSmall(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        while(true) {
            if (res.size() >= k) break;
            TreeNode cur = stack.pop();

            res.add(cur.value);

            cur = cur.right;
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        return res;
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        double minDiff = Double.MAX_VALUE;
        int closestIndex = 0;

        // In-order traverse the BST, also record the index of the closest number to target in the returned array
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            // Before add it into the ordered value array, check for closest criteria
            if (Math.abs((double)cur.value - target) < minDiff) {
                minDiff = Math.abs((double)cur.value - target);
                closestIndex = inOrder.size();
            }

            inOrder.add(cur.value);

            cur = cur.right;
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }

        if (k > inOrder.size()) k = inOrder.size();

        // Now we have a in-order traversed array of value, also the closest number to target
        res.add(inOrder.get(closestIndex));
        k--;
        int left = closestIndex  - 1;
        int right = closestIndex + 1;

        while(k > 0) {
            double leftDiff = left >= 0 ? Math.abs((double)inOrder.get(left) - target) : Double.MAX_VALUE;
            double rightDiff = right < inOrder.size() ? Math.abs((double)inOrder.get(right) - target) : Double.MAX_VALUE;

            if (leftDiff <= rightDiff)
                res.add(inOrder.get(left--));
            else
                res.add(inOrder.get(right++));
            k--;
        }

        return res;

    }

    public void closestKValuesFaster(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;

        // Collect the left subtree nodes first
        closestKValuesFaster(root.left, target, k, res);

        // If collection exceed expected quantity, eliminate the 'weak' ones
        if (res.size() == k) {
            if (Math.abs(target - root.value) < Math.abs(target - res.peekFirst()))
                res.removeFirst();
            else
                return;
        }

        // Little explanation: res must be in-order traversed, therefore the integers should be ascending,
        // If the head(smallest) is closer than the new comer, the  no need to continue checking anymore, the
        // following ones must be even longer distance to target, thus just return, otherwise, eliminate the head
        // just like slide the window of a ascending array

        res.add(root.value);
        closestKValuesFaster(root.right, target, k, res);
    }
}
