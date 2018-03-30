import java.util.Arrays;

public class JobSequencing {
    public static void main(String[] args) {
        Job joba = new Job("a", 4, 20);
        Job jobb = new Job("b", 1, 10);
        Job jobc = new Job("c", 1, 40);
        Job jobd = new Job("d", 1, 30);
        
        Job[] arr = {joba, jobb, jobc, jobd};
        
        printJobSequencingDS(arr, 4);
    }
    
    static class Job implements Comparable<Job>{
        String id;
        int deadline;
        int profit;
        
        public Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
        
        public int compareTo(Job otherJob) {
            if (this.profit == otherJob.profit) return 0;
            return otherJob.profit - this.profit;
        }
    }
    
    static class DisjointSet {
        int[] parent;
        
        DisjointSet(int n) {
            this.parent = new int[n+1];
            for (int i = 0; i <= n; i++) {
                this.parent[i] = i;
            }
        }
        
        int find(int s) {
            if (s == parent[s]) return s;
            return parent[s] = find(parent[s]);
        }
        
        void merge(int u, int v) {
            parent[v] = u;
        }
    }
    
    public static void printJobSequencing(Job[] arr, int n) {
        // Sort the input job array first
        Arrays.sort(arr);
        
        String[] res = new String[n];
        boolean[] occupied = new boolean[n];
        Arrays.fill(occupied, false);
        int profitTotal = 0;
        
        for (Job job: arr) {
            // Start from the deadline(or last time slot) to find the latest slot to squeeze in the job
            for (int i = Math.min(n, job.deadline) - 1; i >= 0; i--) {
                if (!occupied[i]) {
                    occupied[i] = true;
                    res[i] = job.id;
                    profitTotal += job.profit;
                    break;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (occupied[i]) {
                System.out.println(res[i]);
            }
        }
        System.out.print("Total profit earned: " + profitTotal);
    }
    
    static int findLatestDDL(Job[] arr) {
        int latest = 0;
        for (Job job: arr) {
            latest = Math.max(latest, job.deadline);
        }
        
        return latest;
    }
    
    public static void printJobSequencingDS(Job[] arr, int n) {
        Arrays.sort(arr);
        
        int latestDDL = findLatestDDL(arr);
        DisjointSet ds = new DisjointSet(latestDDL);
        
        int profitTotal = 0;
        for (Job job: arr) {
            int ddl = job.deadline;
            int availbleSlot = ds.find(ddl);
            if (availbleSlot > 0) {
                System.out.println(job.id);
                profitTotal += job.profit;
                ds.merge(ds.find(availbleSlot - 1), availbleSlot);
            }
        }
    
        System.out.print("Total profit earned: " + profitTotal);
    }
}
