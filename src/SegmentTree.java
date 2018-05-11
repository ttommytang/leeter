public class SegmentTree {
    public static void main(String[] args) {
    
    }
    
    static class SegNode {
        int left, right, count;
        SegNode leftChild, rightChild;
        public SegNode(int min, int max) {
            left = min;
            right = max;
            count = 0;
        }
    }
}
