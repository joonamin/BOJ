
import java.io.*;
import java.util.*;

public class Main {
    static int A, B, N;
    static StringTokenizer st;

    private static class Job implements Comparable<Job> {
        public int startTime;
        public char color;

        public Job(int startTime, char color) {
            this.startTime = startTime;
            this.color = color;
        }

        @Override
        public int compareTo(Job job) {
            if (this.startTime == job.startTime) {
                return Character.compare(this.color, job.color);
            }
            return Integer.compare(this.startTime, job.startTime);
        }

        @Override
        public String toString() {
            return String.format("startTime: %d, color: %s\n", startTime, color);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        List<Job> jobs = new ArrayList<>();
        int aStartTime = 0, bStartTime = 0;
        for (int i = 0; i < N; i++) {
            int t, m;
            char c;
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            c = st.nextToken().charAt(0);
            m = Integer.parseInt(st.nextToken());

            if (c == 'B') {
                int startTime = Math.max(t, aStartTime);
                for (int j = 0; j < m; j++) {
                    jobs.add(new Job(startTime, 'B'));
                    startTime += A;
                }
                aStartTime = startTime;
            } else {
                int startTime = Math.max(t, bStartTime);
                for (int j = 0; j < m; j++) {
                    jobs.add(new Job(startTime, 'R'));
                    startTime += B;
                }
                bStartTime = startTime;
            }
        }
        Collections.sort(jobs);
        List<Integer> aList = new ArrayList<>(), bList = new ArrayList<>();
        for (int j = 0; j < jobs.size(); j++) {
            // System.out.printf("jobs: %s\n", jobs.get(j));
            if (jobs.get(j).color == 'B') {
                aList.add(j + 1);
            } else {
                bList.add(j + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(aList.size()).append("\n");
        aList.stream().forEach(e -> sb.append(e).append(" "));
        sb.append("\n");

        sb.append(bList.size()).append("\n");
        bList.stream().forEach(e -> sb.append(e).append(" "));

        System.out.println(sb);

    }

}