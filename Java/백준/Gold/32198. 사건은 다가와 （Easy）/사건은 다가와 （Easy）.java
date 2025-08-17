import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;
    static int[][] info;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        info = new int[N][3];
        for (int i = 0; i < N; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(info, (a, b) -> Integer.compare(a[0], b[0]));
        dp = new int[N][2001];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }

        int T = info[0][0], A = info[0][1] + 1000, B = info[0][2] + 1000;

        for (int t = 0; t <= T; t++) {
            if (A >= 1000 - t || 1000 - t >= B)
                dp[0][1000 - t] = t;
            if (A >= 1000 + t || 1000 + t >= B)
                dp[0][1000 + t] = t;
        }

        for (int i = 1; i < N; i++) {
            T = info[i][0];
            A = info[i][1] + 1000;
            B = info[i][2] + 1000;
            int diff = T - info[i - 1][0];
            for (int j = 0; j < 2001; j++) {
                if (A < j && j < B)
                    continue;
                for (int t = 0; t <= diff; t++) {
                    if (j + t < 2001)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + t] + t);
                    if (j >= t)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - t] + t);
                }
            }
        }

        int ans = 0x3f3f3f3f;
        for (int i = 0; i < 2001; i++) {
            if (i <= A || i >= B) {
                ans = Math.min(ans, dp[N - 1][i]);
            }
        }
        if (ans == 0x3f3f3f3f) {
            ans = -1;
        }
        System.out.println(ans);
    }

}