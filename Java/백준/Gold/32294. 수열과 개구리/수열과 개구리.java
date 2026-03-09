
import java.io.*;
import java.util.*;

public class Main {
    static int N, a[], b[];
    static long[] dist;
    static List<Integer>[] nexts = new List[200_005];
    static {
        for (int i = 1; i <= 200_004; i++) {
            nexts[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N + 1];
        b = new int[N + 1];
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (Long.compare(a[1], b[1]) == 0) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            if (i - a[i] < 1 || i + a[i] > N) {
                dist[i] = b[i];
                pq.add(new long[] { i, dist[i] });
            } else {
                int[] temp = new int[] { i - a[i], i + a[i] };
                for (int j = 0; j < 2; j++) {
                    nexts[temp[j]].add(i);
                }
            }
        }

        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            int cur = (int) polled[0];
            long acdist = polled[1];
            // System.out.printf("[polled] cur: %d, acdist: %d\n", cur, acdist);
            if (acdist > dist[cur])
                continue;
            for (int next : nexts[cur]) {
                if (next < 1 || next > N)
                    continue;
                if (dist[next] <= dist[cur] + b[next]) {
                    continue;
                }
                dist[next] = dist[cur] + b[next];
                pq.add(new long[] { next, dist[next] });
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);
    }

}