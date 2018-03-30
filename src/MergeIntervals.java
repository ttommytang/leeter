import java.util.*;

public class MergeIntervals {
    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static void main(String[] args) {

    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() <= 1) return intervals;

        intervals.sort((Interval o1, Interval o2) -> {
            if (o1.start == o2.start) return 0;
            else return o1.start - o2.start;
        });

        int curHead = intervals.get(0).start;
        int curTail = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); i++) {
            Interval next = intervals.get(i);

            if (next.start <= curTail) curTail = Math.max(curTail, next.end);
            else {
                res.add(new Interval(curHead, curTail));
                curHead = next.start;
                curTail = next.end;
            }
        }
        res.add(new Interval(curHead, curTail));

        return res;
    }
}
