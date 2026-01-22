import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int N, M, Q;
    static int[][] edges;
    static int[] queries;
    static boolean[] check;

    static int[] rank, parent;
    static long[] size;

    static int find(int u) {
        return (u == parent[u]) ? u : (parent[u] = find(parent[u]));
    }

    static long union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) {
            return 0;
        }

        if (rank[u] > rank[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        long result = size[u] * size[v];

        parent[u] = v;
        size[v] += size[u];

        if (rank[u] == rank[v]) {
            rank[v]++;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        edges = new int[M + 1][];
        check = new boolean[M + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            edges[i] = new int[] { X, Y };
        }

        queries = new int[Q];
        for (int i = Q - 1; i >= 0; i--) {
            queries[i] = Integer.parseInt(br.readLine());
            check[queries[i]] = true;
        }

        rank = new int[N + 1];
        parent = IntStream.rangeClosed(0, N + 1).toArray();
        size = new long[N + 1];
        Arrays.fill(size, 1);

        for (int i = 1; i <= M; i++) {
            if (!check[i]) {
                union(edges[i][0], edges[i][1]);
            }
        }

        long ans = 0;
        for (int i = 0; i < Q; i++) {
            int q = queries[i];
            ans += query(q);
        }
        System.out.println(ans);
    }

    private static long query(int idx) {
        int[] edge = edges[idx];
        return union(edge[0], edge[1]);
    }
}