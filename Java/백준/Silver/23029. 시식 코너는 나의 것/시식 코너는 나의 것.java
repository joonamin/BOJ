
import java.io.*;
import java.util.*;

public class Main {

    static int N, v[];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        dp[0][1] = v[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][0]), dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + v[i]);
            dp[i][2] = Math.max(dp[i - 1][1] + v[i] / 2, dp[i - 1][2]);
        }
        System.out.println(Math.max(Math.max(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
    }

}