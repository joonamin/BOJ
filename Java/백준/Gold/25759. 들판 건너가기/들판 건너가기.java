
import java.io.*;
import java.util.*;

public class Main {

    static int N, A[];
    static long dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new long[N + 1][101];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[1][A[0]] = 0;
        for (int i = 2; i <= N; i++) {
            // A[i-1] 꽃을 따느냐 안따느냐
            // 딸 경우 dp[i][A[i-1]] = dp[i - 1][m] + (A[i-1] - m) ^ 2
            // m은 임의의 사이즈가 됨
            // 따지 않는 경우 dp[i][j] = dp[i - 1][j]가 됨 (단, dp[i-1][j]가 -1인 경우 시작점은 A[i-1]가 됨)
            for (int j = 0; j <= 100; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            // 현재 꽃을 따는 경우
            for (int j = 1; j <= 100; j++) {
                if (dp[i - 1][j] != -1)
                    dp[i][A[i - 1]] = Math.max(dp[i][A[i - 1]], dp[i - 1][j] + (j - A[i - 1]) * (j - A[i - 1]));
            }
        }
        long ans = 0;
        for (int j = 0; j <= 100; j++) {
            ans = Math.max(ans, dp[N][j]);
        }
        System.out.println(ans);
    }

}