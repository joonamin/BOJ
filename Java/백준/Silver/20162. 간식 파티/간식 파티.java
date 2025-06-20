import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = v[i];
            for (int j = 0; j < i; j++) {
                if (v[j] < v[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + v[i]);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}