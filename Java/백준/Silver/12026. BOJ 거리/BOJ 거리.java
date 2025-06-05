import java.util.*;
import java.io.*;

public class Main {
    static char[] seq = { 'B', 'O', 'J' };

    private static char getPrevSeq(char ch) {
        int idx = -1;
        for (int i = 0; i < seq.length; i++) {
            if (ch == seq[i]) {
                idx = i;
                break;
            }
        }
        return seq[(idx + 2) % 3];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int[] dp = new int[N];

        for (int i = 1; i < N; i++) {
            dp[i] = 0x3f3f3f3f;
            for (int j = 0; j < i; j++) {
                if (S.charAt(j) == getPrevSeq(S.charAt(i))) {
                    dp[i] = Math.min(dp[i], dp[j] + (j - i) * (j - i));
                }
            }
        }
        int ans = (dp[N - 1] == 0x3f3f3f3f) ? -1 : dp[N - 1];
        System.out.println(ans);
    }
}