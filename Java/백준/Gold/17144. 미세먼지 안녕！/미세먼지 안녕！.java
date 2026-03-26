
import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] srcs = { { -1, -1 }, { -1, -1 } }, board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    int idx = srcs[0][0] == -1 ? 0 : 1;
                    srcs[idx][0] = i;
                    srcs[idx][1] = j;
                }
            }
        }

        while (T-- > 0) {
            diffuse();
            rotateCcw(srcs[0][0]);
            rotateCw(srcs[1][0]);
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != -1) {
                    ans += board[i][j];
                }
            }
        }
        System.out.println(ans);
    }

    private static void diffuse() {
        int[][] nextBoard = new int[R][C];
        nextBoard[srcs[0][0]][srcs[0][1]] = -1;
        nextBoard[srcs[1][0]][srcs[1][1]] = -1;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] > 0) {
                    int amount = board[r][c] / 5;
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dir[d][0];
                        int nc = c + dir[d][1];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && board[nr][nc] != -1) {
                            nextBoard[nr][nc] += amount;
                            count++;
                        }
                    }
                    nextBoard[r][c] += (board[r][c] - (amount * count));
                }
            }
        }
        board = nextBoard;
    }

    private static void rotateCcw(int top) {
        for (int i = top - 1; i > 0; i--)
            board[i][0] = board[i - 1][0];
        for (int i = 0; i < C - 1; i++)
            board[0][i] = board[0][i + 1];
        for (int i = 0; i < top; i++)
            board[i][C - 1] = board[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            board[top][i] = board[top][i - 1];
        board[top][1] = 0;
    }

    private static void rotateCw(int bottom) {
        for (int i = bottom + 1; i < R - 1; i++)
            board[i][0] = board[i + 1][0];
        for (int i = 0; i < C - 1; i++)
            board[R - 1][i] = board[R - 1][i + 1];
        for (int i = R - 1; i > bottom; i--)
            board[i][C - 1] = board[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            board[bottom][i] = board[bottom][i - 1];
        board[bottom][1] = 0;
    }
}