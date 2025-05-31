import java.io.*;
import java.util.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[N][2];
        dp[0] = new int[] { v[0], v[0] }; // {last_val, sum}
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i][1] = v[i];
            for (int j = 0; j < i; j++) {
                if (dp[j][0] > v[i] && dp[j][1] + v[i] > dp[i][1]) {
                    dp[i][0] = v[i];
                    dp[i][1] = v[i] + dp[j][1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dp[i][1]);
        }
        System.out.println(ans);
    }
}