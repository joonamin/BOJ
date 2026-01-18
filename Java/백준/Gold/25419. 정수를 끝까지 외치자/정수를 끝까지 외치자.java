import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] dp;
    static boolean[] isForbidden;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        isForbidden = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int m = Integer.parseInt(st.nextToken());
            isForbidden[m] = true;
        }

        if (!isForbidden[n]) {
            dp[n] = 0;
        }

        int zeroCount = 0;

        for (int i = n; i >= 0; i--) {

            int out = i + k + 1;
            if (out <= n) {
                if (!isForbidden[out] && dp[out] == 0) {
                    zeroCount--;
                }
            }

            int in = i + 1;
            if (in <= n) {
                if (!isForbidden[in] && dp[in] == 0) {
                    zeroCount++;
                }
            }

            if (isForbidden[i]) {
                continue;
            }

            if (zeroCount > 0) {
                dp[i] = 1;
            } else {
                dp[i] = 0;
            }
        }

        System.out.println(dp[0]);
    }
}