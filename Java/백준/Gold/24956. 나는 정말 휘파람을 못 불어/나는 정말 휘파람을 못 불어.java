import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[] S;
    static long[][] dp;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine().toCharArray();

        dp = new long[N][5];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        long ans = dfs(0, 0);
        System.out.println(ans);
    }

    // phase
    // 0: W를 찾는중 (""), 1: H를 찾는중("W"), 2: E를 찾는중("WH"), 3: E를 찾는중("WHE")
    // 4: 추가적인 E를 찾는중 ("WHEE...")
    private static long dfs(int idx, int phase) {
        if (idx == N) {
            return 0;
        }
        if (dp[idx][phase] != -1) {
            return dp[idx][phase];
        }
        long count = dfs(idx + 1, phase);
        char ch = S[idx];

        if (phase == 0 && ch == 'W') {
            count = (count + dfs(idx + 1, 1)) % MOD;
        } else if (phase == 1 && ch == 'H') {
            count = (count + dfs(idx + 1, 2)) % MOD;
        } else if (phase == 2 && ch == 'E') {
            count = (count + dfs(idx + 1, 3)) % MOD;
        } else if (phase == 3 && ch == 'E') {
            count = (count + 1 + dfs(idx + 1, 4)) % MOD;
        } else if (phase == 4 && ch == 'E') {
            count = (count + 1 + dfs(idx + 1, 4)) % MOD;
        }
        return dp[idx][phase] = count;
    }
}