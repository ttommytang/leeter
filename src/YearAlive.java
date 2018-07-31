public class YearAlive {
    public static void main(String[] args) {
        YearAlive t = new YearAlive();
        
        int[][] y = {{1975, 1981},{1976, 1977},{1976, 1978}};
        System.out.println(t.findMostAlive(y));
    }
    
    public int findMostAlive(int[][] years) {
        if (years == null || years.length == 0)
            return -1;
        
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] year : years) {
            min = Math.min(min, Math.min(year[0], year[1]));
            max = Math.max(max, Math.max(year[0], year[1]));
        }
        
        int[] count = new int[max - min + 1];
        int len = max - min + 1;
        for (int[] year : years) {
            count[year[0] - min] += 1;
            if (year[1] - min + 1 < len)
                count[year[1] - min + 1] -= 1;
        }
        
        int most = 0, res = min;
        for (int i = 1; i < len; i++) {
            count[i] += count[i - 1];
            if (count[i] > most) {
                most = count[i];
                res = min + i;
            }
        }
        
        System.out.println("People alive: " + most);
        return res;
    }
}
