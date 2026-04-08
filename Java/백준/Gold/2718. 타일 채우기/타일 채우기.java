
import java.io.*;
import java.util.*;

public class Main {

    static int T, N, dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        dp = new int[1001][16];

        while (T-- > 0) {
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            N = Integer.parseInt(br.readLine());
            sb.append(dfs(0, 0)).append("\n");
        }
        System.out.println(sb);
    }

    private static int dfs(int col, int state) {
        if (col >= N) {
            return dp[col][state] = (state == 0) ? 1 : 0;
        }
        if (dp[col][state] != -1)
            return dp[col][state];
        int res = 0;
        if (state == 0) {
            res += dfs(col + 1, 0);
            res += dfs(col + 1, 15);
            res += dfs(col + 1, 3);
            res += dfs(col + 1, 12);
            res += dfs(col + 1, 9);
        } else if (state == 3) {
            res += dfs(col + 1, 0);
            res += dfs(col + 1, 12);
        } else if (state == 6) {
            res += dfs(col + 1, 9);
        } else if (state == 9) {
            res += dfs(col + 1, 0);
            res += dfs(col + 1, 6);
        } else if (state == 12) {
            res += dfs(col + 1, 0);
            res += dfs(col + 1, 3);
        } else if (state == 15) {
            res += dfs(col + 1, 0);
        }
        return dp[col][state] = res;
    }

}