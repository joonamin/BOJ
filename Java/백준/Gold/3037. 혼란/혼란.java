
import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solve(input[0], input[1]));
    }

    private static int solve(int N, int C) {
        int[][] dp = new int[N][C + 1];
        // dp[i][j] := i-index까지 확인했을 떄 혼잡도 j가 되는 경우의 수
        // j-i ~ j
        // dp[i][j] = dp[i-1][j-i] + ... + dp[i-1][j] (단, i는 0이상)
        // dp[i][j-1] = dp[i-1][j-1-i] + ... + dp[i-1][j-1]
        // dp[i][j] - dp[i][j-1] = dp[i-1][j] - dp[i-1][j-1-i]
        // dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-(i+1)]
        // dp[i][j] = sum(dp[i][k]) ... k는 i ~ j까지
        dp[0][0] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= 1) {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] %= MOD;
                }

                if (j >= i + 1) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - (i + 1)] + MOD) % MOD;
                }
            }
        }
        return dp[N - 1][C];
    }
}