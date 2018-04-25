import java.util.*;

class VacationDays {
	public static void main(String[] args) {
		VacationDays test = new VacationDays();

		int[][] F = {{0,1,1}, {1,0,1}, {1,1,0}};
		int[][] D = {{1,3,1}, {6,0,3}, {3,3,3}};

		int v = test.maxVacationDaysDP(F, D);
		System.out.println(v);
	}

	int maxVacation;
	int cities, weeks;

	public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights == null || days == null || flights.length == 0 || flights[0].length == 0 || days.length == 0 || days[0].length == 0) return 0;

        cities = flights.length;
        weeks = days[0].length;
        maxVacation = Integer.MIN_VALUE;

        dfsVacation(flights, days, 0, 0, 0);

        return maxVacation;
    }

    private void dfsVacation(int[][] flights, int[][] days, int atCity, int totalVacation, int week) {
    	if (week == weeks) {
    		maxVacation = Math.max(maxVacation, totalVacation);
    		return;
    	}

    	for (int toCity = 0; toCity < cities; toCity++) {
    		if (toCity == atCity || flights[atCity][toCity] > 0) {
    			dfsVacation(flights, days, toCity, totalVacation + days[toCity][week], week + 1);
    		}
    	}
    }

    private int maxVacationDaysDP(int[][] flights, int[][] days) {
    	if (flights == null || days == null || flights.length == 0 || flights[0].length == 0 || days.length == 0 || days[0].length == 0) return 0;
    	int C = flights.length, W = days[0].length;

    	int[] preWeek;
    	int[] curWeek = new int[C];

    	// Initialize the first week
    	Arrays.fill(curWeek, Integer.MIN_VALUE);
    	for (int c = 0; c < C; c++) {
    		if (c == 0 || flights[0][c] > 0) {
    			curWeek[c] = days[c][0];
    		}
    	}

    	for (int w = 1; w < W; w++) {
    		preWeek = curWeek.clone();
    		for (int c = 0; c < C; c++) {
    			int max = Integer.MIN_VALUE;
    			for (int from = 0; from < C; from++) {
    				if ((c == from || flights[from][c] > 0) && preWeek[from] >= 0) {
    					max = Math.max(max, preWeek[from] + days[c][w]);
    				}
    			}
    			curWeek[c] = max;
    		}
    	}

    	int res = Integer.MIN_VALUE;
    	for (int v : curWeek) {
    		res = Math.max(res, v);
    	}

    	return res;
    }

}