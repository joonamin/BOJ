import java.io.*;
import java.util.*;

public class Main {
    static int N, S;
    static int[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = line[0];
        S = line[1];

        v = new int[N][];
        for (int i = 0; i < N; i++) {
            v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Arrays.sort(v, (a, b) -> {
            if (a[0] == b[0]) {
                return -Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] dp = new int[N];
        dp[0] = v[0][1];
        for (int i = 1; i < N; i++) {
            int h = v[i][0], cost = v[i][1];
            int tValue = h - S;
            int idx = lower(tValue, i - 1);
            int prev = (idx == -1) ? 0 : dp[idx];
            dp[i] = Math.max(dp[i - 1], cost + prev);
        }
        System.out.println(dp[N - 1]);
    }
    
    private static int lower(int value, int rightEnd) {
        int res = -1;
        int l = 0, r = rightEnd;
        while (l <= r) {
            int m = (l + r) / 2;
            if (v[m][0] <= value) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
}
