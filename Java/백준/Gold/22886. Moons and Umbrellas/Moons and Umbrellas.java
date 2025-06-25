import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            String[] input = br.readLine().split(" ");
            int X = Integer.parseInt(input[0]), Y = Integer.parseInt(input[1]);
            String S = input[2];
            int N = S.length();
            int[][] dp = new int[N][2];
            for (int i = 0; i < N; i++)
                Arrays.fill(dp[i], 0x3f3f3f3f);
            dp[0][0] = dp[0][1] = 0;
            for (int i = 1; i < N; i++) {
                if (S.charAt(i) == 'C') {
                    // {CC, JC}
                    if (S.charAt(i - 1) == 'C')
                        dp[i][0] = dp[i - 1][0];
                    else if (S.charAt(i - 1) == 'J')
                        dp[i][0] = dp[i - 1][1] + Y;
                    else
                        dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + Y);
                } else if (S.charAt(i) == 'J') {
                    // {JJ, CJ}
                    if (S.charAt(i - 1) == 'J')
                        dp[i][1] = dp[i - 1][1];
                    else if (S.charAt(i - 1) == 'C')
                        dp[i][1] = dp[i - 1][0] + X;
                    else
                        dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + X);
                } else {
                    if (S.charAt(i - 1) == 'C') {
                        dp[i][0] = dp[i - 1][0]; // CC
                        dp[i][1] = dp[i - 1][0] + X; // CJ
                    } else if (S.charAt(i - 1) == 'J') {
                        dp[i][0] = dp[i - 1][1] + Y; // JC
                        dp[i][1] = dp[i - 1][1]; // JJ
                    } else {
                        dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + Y); // CC, JC
                        dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + X); // JJ, CJ
                    }
                }
            }
            sb.append("Case #" + tc + ": ");
            if (S.charAt(S.length() - 1) == 'C') {
                sb.append(dp[S.length() - 1][0]);
            } else if (S.charAt(S.length() - 1) == 'J') {
                sb.append(dp[S.length() - 1][1]);
            } else {
                sb.append(Math.min(dp[S.length() - 1][0], dp[S.length() - 1][1]));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}