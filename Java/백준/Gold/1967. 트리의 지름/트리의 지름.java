import java.util.*;
import java.io.*;

public class Main {
    static int n, h[], parent[][];
    static List<int[]>[] adj;
    static int[] dest = new int[2];

    static void init(int cur, int depth) {
        h[cur] = depth;
        for (int[] edge : adj[cur]) {
            if (edge[0] == parent[cur][0])
                continue;
            init(edge[0], depth + 1);
        }
    }

    private static int getDist(int u, int v) {
        int uh = h[u], vh = h[v];

        int result = 0;
        while (uh != vh) {
            if (uh < vh) {
                result += parent[v][1];
                v = parent[v][0];
                vh--;
            } else {
                result += parent[u][1];
                u = parent[u][0];
                uh--;
            }
        }

        while (u != v) {
            result += parent[v][1];
            v = parent[v][0];
            vh--;
            result += parent[u][1];
            u = parent[u][0];
            uh--;
        }
        return result;
    }

    private static int solve() {
        init(1, 0);
        dest[0] = dfs(-1, 1, 0)[0];
        dest[1] = dfs(-1, dest[0], 0)[0];
        return getDist(dest[0], dest[1]);
    }

    private static int[] dfs(int prev, int current, int acsum) {
        int[] result = new int[] { current, acsum };
        for (int[] edge : adj[current]) {
            if (edge[0] == prev)
                continue;
            int[] sub = dfs(current, edge[0], acsum + edge[1]);
            if (sub[1] > result[1]) {
                result = sub;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        h = new int[n + 1];
        parent = new int[n + 1][2];
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            parent[c][0] = p;
            parent[c][1] = l;
            adj[p].add(new int[] { c, l });
            adj[c].add(new int[] { p, l });
        }
        System.out.println(solve());
    }
}