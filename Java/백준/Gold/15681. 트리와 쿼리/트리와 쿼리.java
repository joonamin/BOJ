import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q, dp[];
    static List<Integer>[] adj;

    private static int dfs(int prev, int current) {
        if (dp[current] != -1) {
            return dp[current];
        }
        dp[current] = 1;
        for (int next : adj[current]) {
            if (next == prev)
                continue;
            dp[current] += dfs(current, next);
        }
        return dp[current];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(-1, R);

        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(dp[u]).append("\n");
        }
        System.out.println(sb.toString());
    }
}