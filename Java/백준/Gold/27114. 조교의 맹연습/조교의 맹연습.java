
import java.io.*;
import java.util.*;

public class Main {
    static int A, B, C, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // (A * 90 + B * 270 + C * 180) % 360 == 0
        // 모든 에너지를 소모하여 위 조건을 만족하는 A,B,C 에 대해서 A+B+C의 최솟값
        // A * |A| + B * |B| + C * |C| = K
        // [현재 사용 에너지][바라보는방향]
        // {현재 사용 에너지, 바라보는 방향}
        dp = new int[K + 1][4];
        for (int i = 0; i < K + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
        for (int k = 0; k <= K; k++) {
            for (int d = 0; d < 4; d++) {
                if (dp[k][d] == -1)
                    continue;
                int nd = -1;
                if (k + A <= K) {
                    nd = (d + 3) % 4;
                    if (dp[k + A][nd] == -1 || dp[k + A][nd] > dp[k][d] + 1) {
                        dp[k + A][nd] = dp[k][d] + 1;
                    }
                }
                if (k + B <= K) {
                    nd = (d + 1) % 4;
                    if (dp[k + B][nd] == -1 || dp[k + B][nd] > dp[k][d] + 1) {
                        dp[k + B][nd] = dp[k][d] + 1;
                    }
                }
                if (k + C <= K) {
                    nd = (d + 2) % 4;
                    if (dp[k + C][nd] == -1 || dp[k + C][nd] > dp[k][d] + 1) {
                        dp[k + C][nd] = dp[k][d] + 1;
                    }
                }
            }
        }
        System.out.println(dp[K][0]);
    }

}