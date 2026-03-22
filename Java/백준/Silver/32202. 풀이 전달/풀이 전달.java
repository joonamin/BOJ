
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][2];
        dp[0][0] = 1;
        dp[0][1] = 2;
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][1] % MOD;
            dp[i][1] = (dp[i - 1][1] % MOD + dp[i - 1][0] % MOD) % MOD;
            dp[i][1] = ((dp[i][1] % MOD) + (dp[i][1] % MOD)) % MOD;
        }
        System.out.println(((dp[N - 1][0] % MOD) + (dp[N - 1][1] % MOD)) % MOD);
    }

}
