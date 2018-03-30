import java.util.*;

public class MeetingRoom {
    public static void main(String[] args) {
        MeetingRoom test = new MeetingRoom();
        Interval[] ints = new Interval[2];
        ints[0] = new Interval(13, 15);
        ints[1] = new Interval(1, 13);

        int need = test.minMeetingRooms(ints);
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        List<RoomStatus> actions = new ArrayList<>();
        for (Interval interval : intervals) {
            actions.add(new RoomStatus(1, interval.start));
            actions.add(new RoomStatus(-1, interval.end));
        }

        Collections.sort(actions, new Comparator<RoomStatus>() {
            @Override
            public int compare(RoomStatus o1, RoomStatus o2) {
                if (o1.time == o2.time) return o1.action - o2.action;
                return o1.time - o2.time;
            }
        });

        int maxNeeded = 0;
        int curNeeded = 0;
        for (RoomStatus stat : actions) {
            curNeeded += stat.action;
            maxNeeded = Math.max(maxNeeded, curNeeded);
        }

        return maxNeeded;
    }

    static class RoomStatus {
        // 1 for taken, -1 for returned
        int action;
        int time;
        RoomStatus(int act, int t) {action = act; time = t;}
    }

    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
