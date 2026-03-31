
import java.io.*;
import java.util.*;

public class Main {
    static int N, dp[][];
    static char[] chs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chs = br.readLine().toCharArray();

        // TEST();

        dp = new int[N][5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = 0;
            }
        }

        dp[0][0] = 1;
        if (chs[0] == 'D') {
            dp[0][1] = 1;
        }

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 1][2];
            dp[i][3] = dp[i - 1][3];
            dp[i][4] = dp[i - 1][4];
            if (chs[i] == 'D' && dp[i - 1][0] != -1) {
                dp[i][1] += dp[i - 1][0];
            } else if (chs[i] == 'K' && dp[i - 1][1] != -1) {
                dp[i][2] += dp[i - 1][1];
            } else if (chs[i] == 'S' && dp[i - 1][2] != -1) {
                dp[i][3] += dp[i - 1][2];
            } else if (chs[i] == 'H' && dp[i - 1][3] != -1) {
                dp[i][4] += dp[i - 1][3];
            }
        }
        System.out.println(dp[N - 1][4]);
        // printAns();
    }

    // private static void TEST() {
    // N = 100;
    // chs = new char[100];
    // for (int i = 0; i < 100; i++) {
    // chs[i] = (char) ((int) (1000 * Math.random()) % 26 + 'A');
    // }
    // }

    // private static void printAns() {
    // int ans = 0;
    // for (int i = 0; i < N; i++) {
    // for (int j = i + 1; j < N; j++) {
    // for (int k = j + 1; k < N; k++) {
    // for (int l = k + 1; l < N; l++) {
    // if (chs[i] == 'D' && chs[j] == 'K' && chs[k] == 'S' && chs[l] == 'H') {
    // ans++;
    // }
    // }
    // }
    // }
    // }
    // System.out.println("ans: " + ans);
    // }

    /**
     * none: 0, {D}: 1, {DK}: 2, {DKS}: 3, {DKSH}: 4
     * none -> D
     * D -> DK
     * DK -> DKS
     * DKS -> DKSH
     * i를 기준으로 문자열에 따라서 이전에 존재하는 모든 state-1에 대해서 합산해줌
     * [i][0] = [i-1][0] / [i][1] = [i-1][0] + [i-1][1] / [i][2] = [i-1][1] +
     * [i-1][2] / [i][3] = [i-1][2] + [i-1][3]
     */

}