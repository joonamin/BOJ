import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long ans = -1;
    static int[][] dist = new int[10][10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        N = Integer.parseInt(line);
        
        line = br.readLine();
        M = Integer.parseInt(line);

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            if (u == v) continue;
            dist[u][v] = Math.max(dist[u][v], d);
        }

        dfs(1, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int mask, int cur, long totalDist) {
        if (mask == (1 << (N + 1)) - 1) {
            if (dist[cur][0] != -1) {
                ans = Math.max(ans, totalDist + dist[cur][0]);
            }
            return;
        }

        for (int next = 1; next <= N; next++) {
            if ((mask & (1 << next)) == 0 && dist[cur][next] != -1) {
                dfs(mask | (1 << next), next, totalDist + dist[cur][next]);
            }
        }
    }
}
