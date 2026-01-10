
import java.io.*;
import java.util.*;

public class Main {
    static int N, T, M, L;
    static List<List<Node>> adj;
    static long[][] dist;

    private static class Node {
        public int next, time, cost;

        public Node(int next, int time, int cost) {
            this.next = next;
            this.time = time;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Node(v, time, cost));
            adj.get(v).add(new Node(u, time, cost));
        }

        // {현재 n에 위치하고, 누적시간이 t일때 최소 코스트}
        dist = new long[N + 1][T + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f3f3f3f3fL);
        }
        // {accost, actime, cur}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return Long.compare(a[2], b[2]);
                }
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[0], b[0]);
        });
        dist[1][0] = 0;
        pq.add(new long[] { 0, 0, 1 });
        while (!pq.isEmpty()) {
            long[] polled = pq.poll();
            long accost = polled[0], actime = polled[1];
            int cur = (int) polled[2];
            if (dist[cur][(int) actime] < accost)
                continue;
            for (Node info : adj.get(cur)) {
                long nc = accost + info.cost;
                long nd = actime + info.time;
                if (nc > M || nd > T || dist[info.next][(int) nd] <= nc)
                    continue;
                dist[info.next][(int) nd] = nc;
                pq.add(new long[] { nc, nd, info.next });
            }
        }
        long ans = Arrays.stream(dist[N]).min().getAsLong();
        if (ans == 0x3f3f3f3f3f3f3f3fL) {
            ans = -1;
        }
        System.out.println(ans);
    }
}