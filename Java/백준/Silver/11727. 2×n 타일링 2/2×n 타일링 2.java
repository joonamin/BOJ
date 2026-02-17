
import java.io.*;
import java.util.*;

public class Main {
    static int n, dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(1);
            return;
        }
        dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + 2 * dp[i - 2];
            dp[i] %= 10007;
        }
        System.out.println(dp[n]);
    }

}