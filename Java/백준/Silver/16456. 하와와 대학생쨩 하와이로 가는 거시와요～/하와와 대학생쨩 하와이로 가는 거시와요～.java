
import java.io.*;
import java.util.*;

public class Main {

    static int N, dp[][];
    static final int MOD = 1000000009;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 0));
    }

    // type = 0 (현재 방문 1,...,cur) => +1, +2칸을 뛰어야함 (각각 0, 1로 전이)
    // type = 1 (현재 방문 1,...,cur, cur + 2) => -1칸을 뛰어야함 (2로 전이)
    // type = 2 (현재 방문 1,...,cur+1) => 2칸을 뛰어야함 (0으로 전이)
    private static int dfs(int cur, int type) {
        if (dp[cur][type] != -1) {
            return dp[cur][type];
        }
        // System.out.printf("cur: %d, type: %d\n", cur, type);
        if ((type == 0 && cur == N - 1) || (type == 2 && cur == N - 2)) {
            return dp[cur][type] = 1;
        }

        dp[cur][type] = 0;
        if (type == 0) {
            if (cur + 1 < N)
                dp[cur][type] += dfs(cur + 1, 0);
            if (cur + 2 < N)
                dp[cur][type] += dfs(cur + 2, 1);
            dp[cur][type] %= MOD;
        } else if (type == 1) {
            dp[cur][type] += dfs(cur - 1, 2);
            dp[cur][type] %= MOD;
        } else if (type == 2) {
            if (cur + 2 < N)
                dp[cur][type] += dfs(cur + 2, 0);
            dp[cur][type] %= MOD;
        }
        return dp[cur][type];
    }
}