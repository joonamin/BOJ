import java.util.*;
class Solution {
    int solution(int[][] land) {
        int answer = 0;

        int[][] dp = new int[land.length][4];
        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }
        
        for (int i = 1; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);
                }
            }
        }
        answer = Arrays.stream(dp[land.length - 1]).max().getAsInt();
        return answer;
    }
}