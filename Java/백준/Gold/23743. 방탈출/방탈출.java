
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int N, M;
    static int[] parent, rank;

    static int find(int u) {
        return (parent[u] == u) ? u : (parent[u] = find(parent[u]));
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;

        if (rank[u] > rank[v]) {
            int temp = rank[u];
            rank[u] = rank[v];
            rank[v] = temp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        parent = IntStream.range(0, N + 1).toArray();
        rank = new int[N + 1];
        // {a, b, c}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[2], b[2]);
        });
        for (int i = 0; i < M; i++) {
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            int c = Integer.parseInt(tmp[2]);
            pq.add(new int[] { a, b, c });
        }
        tmp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int cost = Integer.parseInt(tmp[i]);
            pq.add(new int[] { 0, i + 1, cost });
        }
        long ans = 0L;
        int counter = 0;
        while (counter <= N && !pq.isEmpty()) {
            int[] edge = pq.poll();
            if (union(edge[0], edge[1])) {
                ans += edge[2];
                counter++;
            }
        }
        System.out.println(ans);
    }

}