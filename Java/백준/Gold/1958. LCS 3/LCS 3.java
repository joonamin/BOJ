
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] a, b, c;
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        c = br.readLine().toCharArray();
        int[][][] dp = new int[a.length + 1][b.length + 1][c.length + 1];
        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                for (int k = 0; k <= c.length; k++) {
                    if (i < a.length && j < b.length && k < c.length && a[i] == b[j] && b[j] == c[k]) {
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i + 1][j + 1][k + 1], dp[i][j][k] + 1);
                    }
                    if (i + 1 <= a.length) {
                        dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k]);
                    }
                    if (j + 1 <= b.length) {
                        dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k]);
                    }
                    if (k + 1 <= c.length) {
                        dp[i][j][k + 1] = Math.max(dp[i][j][k + 1], dp[i][j][k]);
                    }

                }
            }
        }
        System.out.println(dp[a.length][b.length][c.length]);

    }

}