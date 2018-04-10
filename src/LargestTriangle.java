public class LargestTriangle {
    public static void main(String[] args) {
        int[][] mat = {{0,0}, {0,1}, {1,0}, {0,2}, {2,0}};
        LargestTriangle test = new LargestTriangle();
        
        double l = test.largestTriangleArea(mat);
    }
    public double largestTriangleArea(int[][] points) {
        if (points == null || points.length < 3) return 0;
        int len = points.length;
        double max = 0.0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    double area = getArea(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1]);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }
    
    private double getArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        double area = 0.5 * Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        return area;
    }
}
