import java.io.*;
import java.util.*;

public class Main {
    static int W, H;
    static int[][] board;
    static boolean[][] outsideAir;

    static int[][] even_dir = { { -1, -1 }, { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 } };
    static int[][] odd_dir = { { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H + 2][W + 2];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        outsideAir = new boolean[H + 2][W + 2];
        findOutsideAir();

        int totalWalls = 0;
        for (int y = 1; y <= H; y++) {
            for (int x = 1; x <= W; x++) {
                if (board[y][x] == 1) {
                    int[][] dirs = (y % 2 != 0) ? odd_dir : even_dir;
                    for (int[] d : dirs) {
                        int ny = y + d[0];
                        int nx = x + d[1];
                        
                        if (outsideAir[ny][nx]) {
                            totalWalls++;
                        }
                    }
                }
            }
        }
        System.out.println(totalWalls);
    }

    static void findOutsideAir() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 0, 0 });
        outsideAir[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int y = curr[0];
            int x = curr[1];

            int[][] dirs = (y % 2 != 0) ? odd_dir : even_dir;

            for (int[] d : dirs) {
                int ny = y + d[0];
                int nx = x + d[1];

                if (ny < 0 || ny >= H + 2 || nx < 0 || nx >= W + 2) continue;
                if (outsideAir[ny][nx] || board[ny][nx] == 1) continue;

                outsideAir[ny][nx] = true;
                q.add(new int[] { ny, nx });
            }
        }
    }
}