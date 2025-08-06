
import java.io.*;
import java.util.*;

public class Main {

    static int n, m, t, s, g, h, cand[];
    static List<int[]>[] adj = new List[2001];
    static int[][] dist;

    private static void init() {
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        cand = new int[t];
        dist = new int[2][n + 1];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
        }
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        dist[0][s] = 0;
        // {prev, cur, acDist, state}
        pq.add(new int[] { 0, s, 0, 0 });
        while (!pq.isEmpty()) {
            int prev = pq.peek()[0], cur = pq.peek()[1], state = pq.peek()[3];
            pq.poll();
            for (int[] edge : adj[cur]) {
                int next = edge[0], cost = edge[1], nstate = state;
                if (next == prev)
                    continue;

                if ((cur == g && next == h) || (cur == h && next == g)) {
                    nstate = 1;
                }

                if (dist[nstate][next] <= dist[state][cur] + cost) {
                    continue;
                }
                dist[nstate][next] = dist[state][cur] + cost;
                pq.add(new int[] { cur, next, dist[nstate][next], nstate });
            }
        }
    }

    private static boolean check(int tgt) {
        return dist[1][tgt] != 0x3f3f3f3f && dist[1][tgt] <= dist[0][tgt];
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int T = readUInt();
        while (T-- > 0) {
            n = readUInt();
            m = readUInt();
            t = readUInt();
            s = readUInt();
            g = readUInt();
            h = readUInt();
            init();
            for (int i = 0; i < m; i++) {
                int a = readUInt(), b = readUInt(), d = readUInt();
                adj[a].add(new int[] { b, d });
                adj[b].add(new int[] { a, d });
            }
            for (int i = 0; i < t; i++) {
                cand[i] = readUInt();
            }

            Arrays.sort(cand);

            dijkstra();
            for (int i = 0; i < t; i++) {
                if (check(cand[i]))
                    sb.append(cand[i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int readUInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}