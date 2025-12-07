
import java.io.*;
import java.util.*;

public class Main {

    static int n, k, c[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = inputs[0];
        k = inputs[1];
        c = new int[n];
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }

        // base condition
        for (int i = 0; i * c[0] <= k; i++) {
            dp[0][i * c[0]] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= c[i])
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - c[i]] + 1);
            }
        }
        int ans = dp[n - 1][k] == 0x3f3f3f3f ? -1 : dp[n - 1][k];
        System.out.println(ans);
    }

}