
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, dist[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dist[i], 0x3f3f3f3f);
            dist[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dist[u][v] = dist[v][u] = 1;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minSum = Integer.MAX_VALUE, f = -1, s = -1;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int sum = 0;
                for (int k = 1; k <= N; k++) {
                    sum += 2 * Math.min(dist[k][i], dist[k][j]);
                }
                if (minSum > sum) {
                    f = i;
                    s = j;
                    minSum = sum;
                }
            }
        }
        System.out.printf("%d %d %d\n", f, s, minSum);
    }

}