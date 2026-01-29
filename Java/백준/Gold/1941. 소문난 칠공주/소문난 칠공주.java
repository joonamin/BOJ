import java.io.*;
import java.util.*;

public class Main {
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static List<int[]> list;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }
        list = new ArrayList<>();
        comb(0, 0, 0);
        System.out.println(ans);
    }

    private static int check() {
        int sCount = 0;
        for (int[] pos : list) {
            if (board[pos[0]][pos[1]] == 'S')
                sCount++;
        }

        if (sCount < 4)
            return 0;
        boolean[][] selectedMap = new boolean[5][5];
        for (int[] pos : list) {
            selectedMap[pos[0]][pos[1]] = true;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        int[] start = list.get(0);
        q.add(start);
        visited[start[0]][start[1]] = true;

        int connectedCount = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dir[d][0];
                int nc = cur[1] + dir[d][1];
                if (nr >= 0 && nc >= 0 && nr < 5 && nc < 5 &&
                        selectedMap[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] { nr, nc });
                    connectedCount++;
                }
            }
        }
        return connectedCount == 7 ? 1 : 0;
    }

    private static void comb(int idx, int r, int c) {
        if (idx == 7) {
            ans += check();
            return;
        }
        if (r >= 5) {
            return;
        }

        int nr = r + (c + 1) / 5;
        int nc = (c + 1) % 5;
        comb(idx, nr, nc);
        list.add(new int[] { r, c });
        comb(idx + 1, nr, nc);
        list.remove(list.size() - 1);
    }
}