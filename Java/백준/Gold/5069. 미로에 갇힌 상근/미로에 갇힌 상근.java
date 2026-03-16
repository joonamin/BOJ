import java.io.*;
import java.util.*;

public class Main {

    static int T, n;
    static int[][][] dp = new int[30][30][15];

    static int[][] evenDir = { { -1, -1 }, { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 } };
    static int[][] oddDir = { { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            sb.append(dfs(14, 14, n)).append("\n");
        }

        System.out.print(sb);
    }

    static int dfs(int r, int c, int remain) {
        if (remain == 0) {
            if (r == 14 && c == 14)
                return 1;
            return 0;
        }

        if (dp[r][c][remain] != -1) {
            return dp[r][c][remain];
        }

        int ways = 0;
        int[][] dirs = (r % 2 == 0) ? evenDir : oddDir;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];
            ways += dfs(nr, nc, remain - 1);
        }

        return dp[r][c][remain] = ways;
    }
}
