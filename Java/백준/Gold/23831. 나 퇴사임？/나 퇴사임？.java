import java.io.*;
import java.util.*;

public class Main {

    static int N, A, B;
    static int[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        v = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 4; k++)
                v[i][k] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j][k][l]
        // i: 날짜(0~N-1), j: 공부횟수, k: 요양횟수, l: 전날 휴식여부(0:X, 1:O)
        int[][][][] dp = new int[N][N + 1][A + 1][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= A; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        dp[0][1][0][0] = Math.max(v[0][0], v[0][1]);
        dp[0][0][0][1] = v[0][2];
        if (A >= 1)
            dp[0][0][1][0] = v[0][3];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i + 1; j++) {
                for (int k = 0; k <= A; k++) {

                    // Case 1: 이번에 '공부'를 하는 경우 (l=0이 됨)
                    // 조건: j >= 1 이어야 함 (공부 횟수가 늘어나므로)
                    if (j >= 1) {
                        int prevMax = -1;
                        // 전날 휴식X(0) 였거나, 휴식O(1) 였거나 둘 다 가능
                        if (dp[i - 1][j - 1][k][0] != -1)
                            prevMax = Math.max(prevMax, dp[i - 1][j - 1][k][0]);
                        if (dp[i - 1][j - 1][k][1] != -1)
                            prevMax = Math.max(prevMax, dp[i - 1][j - 1][k][1]);

                        if (prevMax != -1) {
                            dp[i][j][k][0] = Math.max(dp[i][j][k][0], prevMax + Math.max(v[i][0], v[i][1]));
                        }
                    }

                    // Case 2: 이번에 '요양'을 하는 경우 (l=0이 됨)
                    // 조건: k >= 1 이어야 함
                    if (k >= 1) {
                        int prevMax = -1;
                        // 전날 휴식X(0) 였거나, 휴식O(1) 였거나 둘 다 가능
                        if (dp[i - 1][j][k - 1][0] != -1)
                            prevMax = Math.max(prevMax, dp[i - 1][j][k - 1][0]);
                        if (dp[i - 1][j][k - 1][1] != -1)
                            prevMax = Math.max(prevMax, dp[i - 1][j][k - 1][1]);

                        if (prevMax != -1) {
                            // 같은 dp[i][j][k][0] 상태이므로 기존 값과 비교하여 Max 취함
                            dp[i][j][k][0] = Math.max(dp[i][j][k][0], prevMax + v[i][3]);
                        }
                    }

                    // Case 3: 이번에 '휴식'을 하는 경우 (l=1이 됨)
                    // 조건: 전날 휴식을 하지 않았어야 함 (l=0인 상태에서만 전이)
                    if (dp[i - 1][j][k][0] != -1) {
                        dp[i][j][k][1] = Math.max(dp[i][j][k][1], dp[i - 1][j][k][0] + v[i][2]);
                    }
                }
            }
        }

        int ans = 0;
        for (int j = B; j <= N; j++) {
            for (int k = 0; k <= A; k++) {
                ans = Math.max(ans, dp[N - 1][j][k][0]);
                ans = Math.max(ans, dp[N - 1][j][k][1]);
            }
        }
        System.out.println(ans);
    }
}