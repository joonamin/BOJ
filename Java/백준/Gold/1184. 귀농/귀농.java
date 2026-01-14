import java.io.*;
import java.util.*;

public class Main {

    static int N, board[][];
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        preprocess();
        long ans = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                Map<Integer, Integer> map = new HashMap<>();

                for (int r = 0; r <= i; r++) {
                    for (int c = 0; c <= j; c++) {
                        int sum = getSum(r, c, i, j);
                        map.put(sum, map.getOrDefault(sum, 0) + 1);
                    }
                }

                for (int r = i + 1; r < N; r++) {
                    for (int c = j + 1; c < N; c++) {
                        int sum = getSum(i + 1, j + 1, r, c);
                        if (map.containsKey(sum)) {
                            ans += map.get(sum);
                        }
                    }
                }

                map.clear();

                for (int r = 0; r <= i; r++) {
                    for (int c = j + 1; c < N; c++) {
                        int sum = getSum(r, j + 1, i, c);
                        map.put(sum, map.getOrDefault(sum, 0) + 1);
                    }
                }

                for (int r = i + 1; r < N; r++) {
                    for (int c = 0; c <= j; c++) {
                        int sum = getSum(i + 1, c, r, j);
                        if (map.containsKey(sum)) {
                            ans += map.get(sum);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static int getSum(int r1, int c1, int r2, int c2) {
        int l1 = c1 >= 1 ? dp[r2][c1 - 1] : 0;
        int l2 = r1 >= 1 ? dp[r1 - 1][c2] : 0;
        int l3 = (r1 >= 1 && c1 >= 1) ? dp[r1 - 1][c1 - 1] : 0;
        return dp[r2][c2] - (l1 + l2 - l3);
    }

    private static void preprocess() {
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int l1 = (i >= 1) ? dp[i - 1][j] : 0;
                int l2 = (j >= 1) ? dp[i][j - 1] : 0;
                int l3 = (i >= 1 && j >= 1) ? dp[i - 1][j - 1] : 0;
                dp[i][j] = l1 + l2 - l3 + board[i][j];
            }
        }
    }
}