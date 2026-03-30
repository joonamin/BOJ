import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 0; i <= Math.min(N, 9); i++) {
            dp[i] = 0;
        }

        for (int cur = 10; cur <= N; cur++) {
            String str = String.valueOf(cur);
            int len = str.length();
            int minM = Integer.MAX_VALUE;

            for (int subLen = 1; subLen < len; subLen++) {
                for (int start = 0; start <= len - subLen; start++) {
                    int m = Integer.parseInt(str.substring(start, start + subLen));
                    if (m == 0)
                        continue;
                    if (dp[cur - m] == 0) {
                        if (m < minM) {
                            minM = m;
                        }
                    }
                }
            }

            if (minM != Integer.MAX_VALUE) {
                dp[cur] = minM;
            } else {
                dp[cur] = 0;
            }
        }

        if (dp[N] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N]);
        }
    }
}
