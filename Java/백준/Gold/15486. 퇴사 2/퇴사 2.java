
import java.io.*;
import java.util.*;

public class Main {

    static int N, v[][], dp[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N + 1][2];
        dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            v[i] = new int[] { T, P };
        }

        for (int i = N; i >= 1; i--) {
            int c1 = -1;
            if (i + v[i][0] <= N + 1) {
                c1 = v[i][1] + dp[i + v[i][0]];
            }
            int c2 = dp[i + 1];
            dp[i] = Math.max(c1, c2);
        }

        System.out.println(dp[1]);
    }

}