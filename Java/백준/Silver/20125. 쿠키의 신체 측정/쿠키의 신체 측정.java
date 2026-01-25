
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int[] heart = new int[2];
        int[] ans = new int[5];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '*' && isThisHeart(i, j)) {
                    heart[0] = i + 1;
                    heart[1] = j + 1;
                    ans[0] = getLength(i, j, 2);
                    ans[1] = getLength(i, j, 3);
                    ans[2] = getLength(i, j, 1); // 허리
                    int nr = i + ans[2];
                    int leftLegLen = getLength(nr, j - 1, 1);
                    int rightLegLen = getLength(nr, j + 1, 1);
                    ans[3] = leftLegLen;
                    ans[4] = rightLegLen;
                }
            }
        }
        System.out.printf("%d %d\n", heart[0], heart[1]);
        System.out.printf("%d %d %d %d %d\n", ans[0], ans[1], ans[2], ans[3], ans[4]);
    }

    private static int getLength(int r, int c, int d) {
        int result = 0;
        r += dir[d][0];
        c += dir[d][1];
        while (0 <= r && r < N && 0 <= c && c < N && board[r][c] == '*') {
            r += dir[d][0];
            c += dir[d][1];
            result++;
        }
        return result;
    }

    private static boolean isThisHeart(int r, int c) {
        // 머리길이 1
        if (r >= 2 && board[r - 1][c] == '*' && board[r - 2][c] == '*') {
            return false;
        }
        // 팔, 머리, 허리 길이 1이상
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] != '*') {
                return false;
            }
        }

        int nr = r, nc = c;
        while (nr < N && board[nr][nc] == '*')
            nr++;
        // 다리 1이상
        if (nr >= N || nc < 1 || nc >= N - 1 || board[nr][nc - 1] != '*' || board[nr][nc + 1] != '*')
            return false;

        return true;
    }

}