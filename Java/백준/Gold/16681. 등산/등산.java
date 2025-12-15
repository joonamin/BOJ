
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D, E, h[];
    static List<List<int[]>> adj = new ArrayList<>();
    static long[][] dists = new long[2][];
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static void dijkstra(final int start, final int dIndex) {
        final long[] dist = dists[dIndex];
        // {cur, acdist}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            return Long.compare(a[1], b[1]);
        });
        dist[start] = 0;
        pq.add(new long[] { start, 0 });

        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            int cur = (int) polled[0];
            long acdist = polled[1];

            if (dist[cur] < acdist) {
                continue;
            }

            for (int[] edge : adj.get(cur)) {
                int next = edge[0], cost = edge[1];
                if (h[next] <= h[cur])
                    continue;
                if (dist[next] <= dist[cur] + cost)
                    continue;
                dist[next] = dist[cur] + cost;
                pq.add(new long[] { next, dist[next] });
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        D = input[2];
        E = input[3];
        h = new int[N + 1];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= N; i++) {
            h[i] = input[i - 1];
        }

        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = input[0], v = input[1], c = input[2];
            adj.get(u).add(new int[] { v, c });
            adj.get(v).add(new int[] { u, c });
        }

        for (int i = 0; i < 2; i++) {
            dists[i] = new long[N + 1];
            Arrays.fill(dists[i], 0x3f3f3f3f3f3f3f3fL);
        }

        for (int i = 0; i < 2; i++) {
            int start = (i == 0) ? 1 : N;
            dijkstra(start, i);
        }

        long ans = Long.MIN_VALUE;
        for (int t = 2; t < N; t++) {
            // h[t] * E - (dists[0][t] + dists[1][t]) * D
            if (dists[0][t] >= 0x3f3f3f3f3f3f3f3fL || dists[1][t] >= 0x3f3f3f3f3f3f3f3fL)
                continue;
            long res = (long) E * h[t] - D * (dists[0][t] + dists[1][t]);
            ans = Math.max(ans, res);
        }

        if (ans == Long.MIN_VALUE) {
            System.out.println("Impossible");
        } else {
            System.out.println(ans);
        }

    }

}