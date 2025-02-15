import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K, c[];
    static boolean[] visited;
    static int groupCount = 0, groupInfo[][];
    static List<Integer>[] adj;

    private static void grouping(final int group, int current) {
        for (int next : adj[current]) {
            if (visited[next])
                continue;
            visited[next] = true;
            groupInfo[group][0] += c[next];
            groupInfo[group][1] += 1;
            grouping(group, next);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        adj = new List[N];
        for (int i = 0; i < N; i++)
            adj[i] = new ArrayList<>();

        visited = new boolean[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        groupInfo = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                groupInfo[++groupCount] = new int[] { c[i], 1 };
                grouping(groupCount, i);
            }
        }

        int[] dp = new int[K];
        for (int i = 1; i <= groupCount; i++) {
            for (int j = K - 1; j >= 0; j--) {
                if (j < groupInfo[i][1])
                    continue;
                dp[j] = Math.max(dp[j], dp[j - groupInfo[i][1]] + groupInfo[i][0]);
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}