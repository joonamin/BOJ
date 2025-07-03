import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        long[] dp = new long[5001];
        final int MOD = 1_000_000_007;
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i <= 5000; i += 2) {
            dp[i] = 0;
            for (int j = i - 2; j >= 0; j -= 2) {
                dp[i] += dp[j] * dp[i - 2 - j];
                dp[i] %= MOD;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            int k = Integer.parseInt(br.readLine());
            sb.append(dp[k]).append("\n");
        }
        System.out.println(sb);
    }
}