
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, board[][];
    static boolean[][] isSet;
    static final int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        isSet = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = dfs(0, 0, 0);
        System.out.println(ans);
    }

    private static int dfs(int r, int c, int acSum) {
        // System.out.printf("(r: %d, c: %d, acSum: %d)\n", r, c, acSum);
        if (r == N) {
            return acSum;
        }

        int result = dfs(r + (c + 1) / M, (c + 1) % M, acSum);
        for (int d = 0; d < 4; d++) {
            if (canSet(r, c, d, (d + 1) % 4)) {
                int added = set(r, c, d, (d + 1) % 4);
                result = Math.max(result, dfs(r + (c + 1) / M, (c + 1) % M, acSum + added));
                unset(r, c, d, (d + 1) % 4);
            }
        }
        return result;
    }

    private static boolean inRange(int[] pos) {
        return 0 <= pos[0] && pos[0] < N && 0 <= pos[1] && pos[1] < M;
    }

    private static boolean canSet(int r, int c, int d1, int d2) {
        if (isSet[r][c])
            return false;
        int[] np1 = new int[] { r + dir[d1][0], c + dir[d1][1] };
        int[] np2 = new int[] { r + dir[d2][0], c + dir[d2][1] };
        return inRange(np1) && inRange(np2) && !isSet[np1[0]][np1[1]] && !isSet[np2[0]][np2[1]];
    }

    private static int set(int r, int c, int d1, int d2) {
        int[] np1 = new int[] { r + dir[d1][0], c + dir[d1][1] };
        int[] np2 = new int[] { r + dir[d2][0], c + dir[d2][1] };
        isSet[r][c] = isSet[np1[0]][np1[1]] = isSet[np2[0]][np2[1]] = true;
        return 2 * board[r][c] + board[np1[0]][np1[1]] + board[np2[0]][np2[1]];
    }

    private static void unset(int r, int c, int d1, int d2) {
        int[] np1 = new int[] { r + dir[d1][0], c + dir[d1][1] };
        int[] np2 = new int[] { r + dir[d2][0], c + dir[d2][1] };
        isSet[r][c] = isSet[np1[0]][np1[1]] = isSet[np2[0]][np2[1]] = false;
    }

}