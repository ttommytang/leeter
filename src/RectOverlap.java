public class RectOverlap {
    public static void main(String[] args) {
        int[] rec1 = {3,17,6,20}, rec2 = {3,8,6,20};
        RectOverlap test = new RectOverlap();
        
        boolean t1 = test.isRectangleOverlap(rec1, rec2);
    }
    
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec1 == null || rec2 == null || rec1.length < 4 || rec2.length < 4) return false;
        
        return isOverlap(rec1[0], rec1[2], rec2[0], rec2[2]) && isOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
    }
    
    private boolean isOverlap(int left1, int right1, int left2, int right2) {
        if (left1 < left2 && right1 <= left2) return false;
        if (left1 > left2 && left1 >= right2) return false;
        return true;
    }
}
