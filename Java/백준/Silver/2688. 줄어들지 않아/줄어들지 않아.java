
import java.io.*;
import java.util.*;

public class Main {
    static int T, n;
    static long[][] dp = new long[65][10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        init();
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            long res = 0;
            for (int j = 0; j < 10; j++) {
                res += dp[n - 1][j];
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        // dp[i][j] := i자리의 수 and 마지막 숫자가 j로 끝나는 경우의 수
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < 65; i++) {
            // dp[i][j] = dp[i][0] + ... + dp[i][j-1] + dp[i][j];
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
    }

}