
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<int[]>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int A = input[0], B = input[1], C = input[2];
            adj.get(A).add(new int[] { B, C });
            adj.get(B).add(new int[] { A, C });
        }

        // 모든 간선의 크기가 0이상이므로, 최단거리 k를 만족하는 경로가 여러개 존재할 경우 먼저 도달한 것이 최소 간선의 수로 도달한 것이다.
        // 해 공간은 다익스트라로 구성된 경로 상에 존재한다.
        // {current, acdist}
        // 현재 최단 경로를 구성하는 이전 edge를 표기한다.
        // dist[] = {dist, a, b}
        int[][] dist = new int[N + 1][3];
        for (int i = 1; i <= N; i++)
            Arrays.fill(dist[i], 0x3f3f3f3f);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        pq.add(new int[] { 0, 0, 1 });
        dist[1] = new int[] { 0, 0, 1 };
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int acdist = polled[0], prev = polled[1], cur = polled[2];
            if (dist[cur][0] < acdist)
                continue;
            for (int[] edge : adj.get(cur)) {
                int next = edge[0], cost = edge[1];
                if (next == prev)
                    continue;
                if (dist[next][0] <= acdist + cost)
                    continue;
                dist[next] = new int[] { acdist + cost, cur, next };
                pq.add(new int[] { acdist + cost, cur, next });
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N - 1).append("\n");
        for (int i = 2; i <= N; i++) {
            sb.append(String.format("%d %d\n", dist[i][1], dist[i][2]));
        }
        System.out.println(sb);
    }

}