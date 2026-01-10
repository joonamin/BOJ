
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, dp[][];
    static char[] colors;
    static char[][] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        colors = new char[N];
        for (int i = 0; i < N; i++) {
            colors[i] = st.nextToken().charAt(0);
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        edges = new char[M + 1][M + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            edges[u][v] = edges[v][u] = c;
        }

        dp[0][1] = 0;
        for (int i = 1; i <= N; i++) {
            // 새로운 노드
            for (int j = 1; j <= M; j++) {
                // 이전노드
                for (int k = 1; k <= M; k++) {
                    if (j == k)
                        continue; // 반드시 카드를 내야함
                    if (dp[i - 1][k] != -1 && edges[k][j] != '\0') {
                        int added = (edges[k][j] == colors[i - 1]) ? 10 : 0;
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + added);
                    }
                }
            }
        }
        System.out.println(Math.max(0, Arrays.stream(dp[N]).max().getAsInt()));
    }
}