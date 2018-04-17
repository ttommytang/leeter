import java.util.*;

public class LinkedListUnion {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    public int numComponents(ListNode head, int[] G) {
        if (head == null) return 0;

        Set<Integer> nodes = new HashSet<>();
        for (int g : G) nodes.add(g);

        int islands = 0;
        while(head != null) {
            if (nodes.contains(head.val) && (head.next == null || !nodes.contains(head.next.val)))
                islands++;

            head = head.next;
        }
        return islands;
    }
}
