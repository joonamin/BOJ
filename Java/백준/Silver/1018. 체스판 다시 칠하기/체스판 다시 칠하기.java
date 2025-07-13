
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] board, tgt;
    static final char[] order = { 'W', 'B' };

    private static int calc(int r, int c) {
        tgt = new char[8][8];
        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c + 8; j++) {
                tgt[i - r][j - c] = board[i][j];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int ord = 0; ord < 2; ord++) {
            int cur = ord, tmp = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tgt[i][j] != order[cur]) {
                        tmp++;
                    }
                    cur = (cur + 1) % 2;
                }
                cur = (cur + 1) % 2;
            }
            result = Math.min(result, tmp);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        board = new char[N][];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                ans = Math.min(ans, calc(i, j));
            }
        }
        System.out.println(ans);
    }

}