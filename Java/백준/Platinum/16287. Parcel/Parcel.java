import java.io.*;
import java.util.*;

public class Main {
    static int w, n, v[];
    static int[] dp = new int[400_001];

    private static String solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = v[i] + v[j];
                if (dp[sum] == -1)
                    dp[sum] = i;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = v[i] + v[j];
                if (w >= sum && w - sum <= 400_000 && dp[w - sum] != -1 && dp[w - sum] < i && dp[w - sum] < j) {
                    return "YES";
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.fill(dp, -1);
        System.out.println(solve());
    }

}