
import java.io.*;
import java.util.*;

public class Main {
    static int n;

    @SuppressWarnings("unchecked")
    static List<int[]>[] adj = new List[12001];
    static Set<Integer> childSet = new HashSet<>();
    static int[] dist, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int thisnode = Integer.parseInt(st.nextToken());
            int parnode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            childSet.add(thisnode);
            adj[parnode].add(new int[] { thisnode, weight });
        }

        int root = getRoot();
        // dist[v] := root -> v까지의 거리
        // dfs(v) := v부터 subtree 하위까지의 합
        dist = new int[n + 1];
        dist[root] = 0;
        calcDist(-1, root, 0);

        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dfs(-1, root);

        long ans = getAns(-1, root);
        System.out.println(ans);
    }

    private static long getAns(int prev, int cur) {
        // grade 구함, 모든 힌지의 합을 구해야함
        long result = dp[cur] + dist[cur];
        long max = 0;
        for (int[] edge : adj[cur]) {
            int next = edge[0];
            if (next == prev)
                continue;
            long grade = getAns(cur, next);
            max = Math.max(max, grade);
        }
        return result + max;
    }

    private static int dfs(int prev, int cur) {
        if (dp[cur] != -1) {
            return dp[cur];
        }
        dp[cur] = 0;
        for (int[] edge : adj[cur]) {
            int next = edge[0], weight = edge[1];
            if (prev == next)
                continue;
            dp[cur] += weight + dfs(cur, next);
        }
        return dp[cur];
    }

    private static void calcDist(int prev, int cur, int acDist) {
        for (int[] edge : adj[cur]) {
            int next = edge[0], weight = edge[1];
            if (prev == next)
                continue;
            dist[next] = acDist + weight;
            calcDist(cur, next, acDist + weight);
        }
    }

    private static int getRoot() {
        for (int i = 1; i <= n; i++) {
            if (!childSet.contains(i)) {
                return i;
            }
        }
        assert (false);
        return -1;
    }

}