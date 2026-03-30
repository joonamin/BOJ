
import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M, K, conn[][] = new int[101][101];
    static int[][] dist = new int[101][101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= N; i++) {
                Arrays.fill(conn[i], -1);
                Arrays.fill(dist[i], 0x3f3f3f3f);
                dist[i][i] = 0;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                conn[a][b] = conn[b][a] = c;
                dist[a][b] = Math.min(dist[a][b], c);
                dist[b][a] = Math.min(dist[b][a], c);
            }
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                    }
                }
            }

            K = Integer.parseInt(br.readLine());
            List<Integer> arr = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            int ans = -1, ansDist = -1;
            for (int i = 1; i <= N; i++) {
                int res = 0;
                for (int p : arr) {
                    if (dist[p][i] == 0x3f3f3f3f) {
                        res = 0x3f3f3f3f;
                        break;
                    }
                    res += dist[p][i];
                }
                if (res != 0x3f3f3f3f && (ans == -1 || ansDist > res)) {
                    ans = i;
                    ansDist = res;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}