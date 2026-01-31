
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M, K;
    @SuppressWarnings("unchecked")
    static List<Integer>[] adj = new List[100001];
    static int[] tgt = new int[2];
    static int[] parent, rank;

    private static int find(int u) {
        return (u == parent[u]) ? u : (parent[u] = find(parent[u]));
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (rank[u] > rank[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = IntStream.rangeClosed(0, N).toArray();
        rank = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (i == K - 1) {
                tgt[0] = u;
                tgt[1] = v;
            }
            adj[u].add(v);
            adj[v].add(u);
        }
        bfs(tgt[0]);
        bfs(tgt[1]);

        long ans = 0L;
        if (find(tgt[0]) == find(tgt[1])) {
            ans = 0L;
        } else {
            long first = 0, second = 0;
            for (int i = 1; i <= N; i++) {
                if (find(i) == find(tgt[0])) {
                    first++;
                } else if (find(i) == find(tgt[1])) {
                    second++;
                }
            }
            ans = first * second;
        }
        System.out.println(ans);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if ((cur == tgt[0] && next == tgt[1]) || (cur == tgt[1] && next == tgt[0]))
                    continue;
                if (!union(cur, next)) {
                    continue;
                }
                q.add(next);
            }
        }
    }

}