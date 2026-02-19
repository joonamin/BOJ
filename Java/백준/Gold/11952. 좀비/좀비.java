
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, S, p, q;
    static int[] state;
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new List[100001];

    private static final int SAFE = 0;
    private static final int DANGER = 1;
    private static final int ZOMBIE = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        state = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        Queue<Integer> zombies = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int z = Integer.parseInt(br.readLine());
            state[z] = ZOMBIE;
            zombies.add(z);
        }
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 위험 지역 state-marking 용도
        markDangerState(zombies);

        // dijkstra로 1번 ~ N번까지의 최단거리를 구하기
        System.out.println(dijkstra(1, N));
    }

    private static long dijkstra(int src, int dst) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;
        // {누적 거리, 노드}
        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((a, b) -> {
            return Long.compare(a[0], b[0]);
        });
        pq.add(new long[] { 0, src });
        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            long acDist = polled[0];
            int cur = (int) polled[1];

            if (dist[cur] < acDist)
                continue;
            for (int next : adj[cur]) {
                if (state[next] == ZOMBIE)
                    continue;
                long cost = (state[next] == SAFE) ? p : q;
                if (dist[next] <= dist[cur] + cost) {
                    continue;
                }
                dist[next] = dist[cur] + cost;
                pq.add(new long[] { dist[next], next });
            }
        }
        int lastExclusive = (state[N] == SAFE) ? p : q;
        return dist[N] - lastExclusive;
    }

    private static void markDangerState(Queue<Integer> areas) {
        Queue<int[]> q = new ArrayDeque<>();
        while (!areas.isEmpty()) {
            int area = areas.poll();
            q.add(new int[] { area, 0 });
        }
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int area = polled[0], dist = polled[1];
            if (dist + 1 <= S) {
                for (int next : adj[area]) {
                    if (state[next] != SAFE)
                        continue;
                    state[next] = DANGER;
                    q.add(new int[] { next, dist + 1 });
                }
            }
        }
    }

}