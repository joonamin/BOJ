
import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    static int[] pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                board[i][j] = input[j];
                if (board[i][j] == 0) {
                    pos = new int[] { i, j };
                }
            }
        }
        // [0][j] + [1][j] + ... + [i-1][j] + M
        // [i][0] + [i][1] + ... + [i][j-1] + M

        long[] cols, rows;
        cols = new long[N];
        rows = new long[N];
        long[] cross = new long[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rows[i] += board[i][j];
                cols[j] += board[i][j];
            }
        }
        int i = 0, j = 0;
        while (i < N && j < N) {
            cross[0] += board[i][j];
            i++;
            j++;
        }
        i = 0;
        j = N - 1;
        while (i < N && j >= 0) {
            cross[1] += board[i][j];
            i++;
            j--;
        }
        Set<Long> cand = new HashSet<>();
        for (int k = 0; k < N; k++) {
            if (pos[0] != k) {
                long arg = rows[k] - rows[pos[0]];
                if (arg != 0)
                    cand.add(arg);
            }
            if (pos[1] != k) {
                long arg = cols[k] - cols[pos[1]];
                if (arg != 0)
                    cand.add(arg);
            }
        }
        long ans = -1;
        for (long m : cand) {
            boolean flag = true;
            long rowSum = (pos[0] == 0) ? m + rows[0] : rows[0];
            long colSum = (pos[1] == 0) ? m + cols[0] : cols[0];
            for (int k = 0; flag && k < N; k++) {
                long tgt1 = (pos[0] == k) ? rows[k] + m : rows[k];
                long tgt2 = (pos[1] == k) ? cols[k] + m : cols[k];
                if (rowSum != tgt1 || colSum != tgt2) {
                    flag = false;
                    break;
                }
            }
            // 대각선 성분 체크
            long l = (pos[0] == pos[1]) ? m + cross[0] : cross[0];
            long r = (Math.abs(pos[0] + pos[1]) == N - 1) ? m + cross[1] : cross[1];
            if (l != r) {
                flag = false;
            }

            if (flag) {
                ans = m;
                break;
            }
        }
        System.out.println(ans);
    }

}