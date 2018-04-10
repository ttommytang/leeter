import java.util.PriorityQueue;

public class GasStations {
    public static void main(String[] args) {
        GasStations test = new GasStations();
        int[] gas1 = {1,2,3,4,5,6,7,8,9,10};
        double res1 = test.minmaxGasDistBS(gas1, 9);
    }

    static class Segment implements Comparable<Segment> {
        int inter;
        int seg;
        Segment(int left, int right) {
            inter = 1;
            seg = right - left;
        }

        public int compareTo(Segment other) {
            if (this.seg * other.inter == other.seg * this.inter) return 0;
            return this.seg * other.inter > other.seg * this.inter ? -1 : 1;
        }
    }

    public double minmaxGasDist(int[] stations, int K) {
        if (stations == null || stations.length < 2) return 0;

        PriorityQueue<Segment> pq = new PriorityQueue<>();
        for (int i = 1; i < stations.length; i++) {
            pq.offer(new Segment(stations[i-1], stations[i]));
        }

        while(K > 0) {
            Segment longest = pq.poll();
            longest.inter += 1;
            pq.offer(longest);
            K--;
        }
        Segment peek = pq.peek();
        return 1.0 * peek.seg / peek.inter;
    }

    public double minmaxGasDistBS(int[] stations, int K) {
        if (stations == null || stations.length < 2) return 0;

        int[] intervals = new int[stations.length - 1];
        int maxInterval = Integer.MIN_VALUE;
        for (int i = 1; i < stations.length; i++) {
            intervals[i-1] = stations[i] - stations[i-1];
            maxInterval = Math.max(maxInterval, stations[i] - stations[i-1]);
        }

        // Start binary search in the range of [0, maxInterval];
        double left = 0, right = 1.0 * maxInterval;
        while(right - left > 1e-6) {
            double mid = left + (right - left) / 2;

            int breaks = 0;
            for (int interval : intervals)
                breaks += Math.ceil(1.0 * interval / mid - 1);

            if (breaks > K) left = mid;
            else right = mid;
        }

        return left;
    }
}
