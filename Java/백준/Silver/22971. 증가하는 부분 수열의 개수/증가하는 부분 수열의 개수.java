
import java.io.*;
import java.util.*;

public class Main {

    static int N, v[];
    static final int MOD = 998_244_353;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            v[j] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (v[i] > v[j]) {
                    dp[i] = ((dp[i] % MOD) + (dp[j] % MOD)) % MOD;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
    }

}