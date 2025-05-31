import java.io.*;
import java.util.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = v[i];
            for (int j = 0; j < i; j++) {
                if (v[j] > v[i] && dp[i] < dp[j] + v[i]) {
                    dp[i] = dp[j] + v[i];
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}