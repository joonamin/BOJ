
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static long dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];
        dp = new long[N + 1];
        dp[1] = 1 % K;
        for (int i = 2; i <= N; i++) {
            long a = 1;
            while (a <= i)
                a *= 10;
            dp[i] = ((a % K) * (dp[i - 1])) % K + i % K;
            // System.out.printf("dp[%d] = %d, a: %d\n", i, dp[i], a / 10);
            dp[i] %= K;
        }
        System.out.println(dp[N]);
    }

}