
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static List<Integer>[] adj = new List[100001];
    static {
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    static int counter = 1;
    static int[] order, depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        order = new int[N + 1];
        Arrays.fill(order, -1);
        depth = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i], (a, b) -> -Integer.compare(a, b));
        }

        order[R] = 1;
        depth[R] = 0;
        dfs(R, 0);

        long ans = 0L;
        for (int i = 1; i <= N; i++) {
            ans += 1L * order[i] * depth[i];
        }
        System.out.println(ans);
    }

    private static void dfs(int cur, int d) {
        for (int nxt : adj[cur]) {
            if (order[nxt] != -1)
                continue;
            order[nxt] = ++counter;
            depth[nxt] = d + 1;
            dfs(nxt, d + 1);
        }
    }

}