import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (B.length < 3) {
            System.out.println(0);
            return;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            int d = B[1] - B[0] + i - 2;
            int[][] dp = new int[N][3];
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[j], Integer.MAX_VALUE);
            }
            dp[0][0] = dp[0][2] = 1;
            dp[0][1] = 0;

            for (int j = 1; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    int cur = B[j] + k - 1;
                    for (int l = 0; l < 3; l++) {
                        int prev = B[j - 1] + l - 1;
                        int diff = Math.abs(prev + d - cur);
                        if (dp[j - 1][l] != Integer.MAX_VALUE && diff == 0) {
                            int m = (k == 0 || k == 2) ? 1 : 0;
                            dp[j][k] = Math.min(dp[j][k], dp[j - 1][l] + m);
                        }
                    }
                }
            }
            for (int j = 0; j < 3; j++)
                ans = Math.min(ans, dp[N - 1][j]);
        }
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }
}