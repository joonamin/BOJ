
import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> scores = new ArrayList<>();
    static int[][][] dp; // {idx, black_selected, white_selected}

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            scores.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
        }
        dp = new int[scores.size() + 1][16][16];
        for (int i = 1; i <= scores.size(); i++) {
            // i-1 인덱스의 원소를 3가지로 배정
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    // d[i][j][k] = max(dp[i-1][j][k], dp[i-1][j-1][k] + scores[i-1][0],
                    // dp[i-1][j][k-1] + scores[i-1][1])
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= 1)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k] + scores.get(i - 1)[0]);
                    if (k >= 1)
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1] + scores.get(i - 1)[1]);
                }
            }
        }
        System.out.println(dp[scores.size()][15][15]);
    }

}