
import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    private static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static int getDist(int[] src) {
        Queue<int[]> q = new ArrayDeque<>(); // {r, c, acdist}
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[src[0]][src[1]] = true;
        q.add(new int[] { src[0], src[1], 0 });

        int res = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            res++;
            while (size > 0) {
                int r = q.peek()[0], c = q.peek()[1], acdist = q.peek()[2];
                q.poll();
                size--;
                for (int d = 0; d < dir.length; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (nr < 0 || nc < 0 || nr >= board.length || nc >= board[nr].length
                            || visited[nr][nc] || board[nr][nc] == 'W')
                        continue;
                    visited[nr][nc] = true;
                    q.add(new int[] { nr, nc, acdist + 1 });
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int R = Integer.parseInt(temp[0]), C = Integer.parseInt(temp[1]);
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String temp2 = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = temp2.charAt(j);
            }
        }

        // (i, j) => 최대 50 * 50
        // 각각에 대해서 board_size 만큼 iterate -> 50 * 50
        // 총 시간 < 50^4
        List<int[]> candidates = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'L') {
                    candidates.add(new int[] { i, j });
                }
            }
        }

        int maxDist = 0;
        for (int i = 0; i < candidates.size(); i++) {
            int dist = getDist(candidates.get(i));
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        System.out.println(maxDist);
    }

}