
import java.io.*;
import java.util.*;

public class Main {
    static final int[][] dir = { { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
    static int n, m, board[][];

    private static boolean check(int nr, int nc) {
        return !(nr < 0 || nc < 0 || nr >= n || nc >= m);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] stemp = br.readLine().split(" ");
        n = Integer.parseInt(stemp[0]);
        m = Integer.parseInt(stemp[1]);
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = (int) (line.charAt(j) - '0');
            }
        }

        int ans = 0;
        while (getClusterCount() > 1) {
            spread();
            ans++;
        }
        System.out.println(ans);
    }

    static void spread() throws Exception {
        List<Queue<int[]>> qlist = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            qlist.add(new ArrayDeque<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                qlist.get(board[i][j]).add(new int[] { i, j, 0 });
            }
        }

        for (int idx = 5; idx >= 1; idx--) {
            while (!qlist.get(idx).isEmpty()) {
                int[] polled = qlist.get(idx).poll();
                int r = polled[0], c = polled[1], acdist = polled[2];
                if (board[r][c] != idx)
                    continue;
                for (int d = 0; d < dir.length; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (!check(nr, nc) || board[nr][nc] == board[r][c] || acdist + 1 > idx)
                        continue;
                    board[nr][nc] = board[r][c];
                    qlist.get(idx).add(new int[] { nr, nc, acdist + 1 });
                }
            }
        }

        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < m; j++) {
        // sb.append(board[i][j] + " ");
        // }
        // sb.append("\n");
        // }
        // System.out.println(sb);
        // Thread.sleep(1500);

    }

    static int getClusterCount() {
        int result = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 || visited[i][j])
                    continue;
                result++;
                visited[i][j] = true;
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[] { i, j });
                while (!q.isEmpty()) {
                    int r = q.peek()[0], c = q.peek()[1];
                    // System.out.printf("[i: %d, j: %d] r: %d, c: %d \n", i, j, r, c);
                    q.poll();
                    for (int d = 4; d < 8; d++) {
                        int nr = r + dir[d][0], nc = c + dir[d][1];
                        if (!check(nr, nc) || visited[nr][nc] || board[nr][nc] == 0)
                            continue;
                        visited[nr][nc] = true;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
        }
        // System.out.printf("getClusterCount(): %d\n", result);
        return result;
    }
}