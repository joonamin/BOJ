
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        long[][] e = new long[N + 1][M + 1];
        long[][] es = new long[N + 1][M + 1];
        long[][] esm = new long[N + 1][M + 1];
        e[0][0] = es[0][0] = es[0][0] = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                char ch = board[i - 1][j - 1];
                e[i][j] = (e[i - 1][j] + e[i][j - 1] - e[i - 1][j - 1] + MOD) % MOD;
                es[i][j] = (es[i - 1][j] + es[i][j - 1] - es[i - 1][j - 1] + MOD) % MOD;
                esm[i][j] = (esm[i - 1][j] + esm[i][j - 1] - esm[i - 1][j - 1] + MOD) % MOD;
                if (ch == 'E') {
                    e[i][j] = (e[i][j] + 1) % MOD;
                } else if (ch == 'S') {
                    es[i][j] = (es[i][j] + e[i][j]) % MOD;
                } else if (ch == 'M') {
                    esm[i][j] = (esm[i][j] + es[i][j]) % MOD;
                }
            }
        }
        System.out.println(esm[N][M]);
    }
}