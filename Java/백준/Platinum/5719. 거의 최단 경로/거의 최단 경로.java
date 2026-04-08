
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, S, D;
    static int[] dist = new int[501];
    static int[][] edge = new int[501][501];
    @SuppressWarnings("unchecked")
    static List<Integer>[] prev = new List[501];
    static {
        for (int i = 0; i < prev.length; i++) {
            prev[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0)
                break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            Arrays.fill(dist, 0x3f3f3f3f);
            for (int i = 0; i < N; i++) {
                prev[i] = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    edge[i][j] = 0x3f3f3f3f;
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                edge[u][v] = p;
            }

            // 1. 최단 경로 구하기
            calc();
            // 2. 역추적하면서 edge 비활성화 시키기
            invalidateEdge(D);
            // 3. 다시 최단 경로 구하기
            calc();
            int res = dist[D] == 0x3f3f3f3f ? -1 : dist[D];
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static void calc() {
        for (int i = 0; i < N; i++) {
            dist[i] = 0x3f3f3f3f;
        }
        // A -> B 까지의 최단 경로를 구하며, 동일한 cost로 방문하는 경우 모두 prev에 저장
        // 더 짧은 cost로 도달할 수 있을 경우 prev 초기화 후 cur 저장
        // {acdist, cur}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        dist[S] = 0;
        pq.add(new int[] { 0, S });
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int acdist = polled[0], cur = polled[1];
            if (acdist > dist[cur])
                continue;
            for (int nxt = 0; nxt < N; nxt++) {
                if (cur == nxt || edge[cur][nxt] == 0x3f3f3f3f)
                    continue;
                if (dist[nxt] == dist[cur] + edge[cur][nxt]) {
                    prev[nxt].add(cur);
                } else if (dist[nxt] > dist[cur] + edge[cur][nxt]) {
                    prev[nxt].clear();
                    prev[nxt].add(cur);
                    dist[nxt] = dist[cur] + edge[cur][nxt];
                    pq.add(new int[] { dist[nxt], nxt });
                }
            }
        }
    }

    private static void invalidateEdge(int cur) {
        // System.out.printf("cur: %d\n", cur);
        if (prev[cur].isEmpty())
            return;
        for (int p : prev[cur]) {
            if (edge[p][cur] == 0x3f3f3f3f)
                continue;
            edge[p][cur] = 0x3f3f3f3f;
            invalidateEdge(p);
        }
    }
}