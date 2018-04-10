public class RadarNCar {
    class Point {
          int x;
          int y;
          Point() { x = 0; y = 0; }
          Point(int a, int b) { x = a; y = b; }
    }
 
    public String radarDetection(Point[] coordinates, int[] radius) {
        // Write your code here
        if (coordinates == null || coordinates.length == 0) return "NO";
        
        for (int i = 0; i < coordinates.length; i++) {
            int lower = coordinates[i].y - radius[i];
            int upper = coordinates[i].y + radius[i];
            
            if (upper <= 0 || lower >= 1) continue;
            else return "YES";
        }
        return "NO";
    }
}
