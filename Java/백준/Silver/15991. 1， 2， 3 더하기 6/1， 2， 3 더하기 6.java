import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[100_001];
        final long MOD = 1_000_000_009L;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;
        for (int i = 7; i <= 100_000; i++) {
            dp[i] = (((dp[i - 2] % MOD) + (dp[i - 4] % MOD) + (dp[i - 6] % MOD)) % MOD);
        }
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.println(sb.toString());
    }
}