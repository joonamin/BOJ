
import java.io.*;
import java.util.*;

public class Main {
    static int T, W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        T = input[0];
        W = input[1];

        int[] v = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[T + 1][W + 1][2];
        for (int i = 0; i <= T; i++) {
            for (int j = 0; j <= W; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[1][0][0] = v[1] == 1 ? 1 : 0;
        dp[1][1][1] = v[1] == 2 ? 1 : 0;
        for (int i = 2; i <= T; i++) {
            // dp[i][j][k] = 이전상태에서 이동 + 이전상태에서 이동x
            // 이전상태에서 이동 -> dp[i-1][j-1][(k+1)%2] + ((k+1)%2가 v[i]랑 같다면 1)
            // 이전상태에서 이동x -> dp[i-1][j][k] + (k가 v[i]랑 같다면 1)
            // 상태 전이가 불가능한 케이스를 고려해서 전이
            for (int j = 0; j <= W; j++) {
                for (int k = 0; k < 2; k++) {
                    int r1 = (k == v[i] - 1) ? 1 : 0;
                    if (dp[i - 1][j][k] != -1)
                        dp[i][j][k] = dp[i - 1][j][k] + r1;
                    if (j >= 1) {
                        dp[i][j][k] = Math.max(dp[i - 1][j - 1][(k + 1) % 2] + r1, dp[i][j][k]);
                    }
                }
            }
        }
        int ans = -1;
        for (int i = 0; i <= W; i++) {
            ans = Math.max(ans, dp[T][i][0]);
            ans = Math.max(ans, dp[T][i][1]);
        }
        System.out.println(ans);
    }

}