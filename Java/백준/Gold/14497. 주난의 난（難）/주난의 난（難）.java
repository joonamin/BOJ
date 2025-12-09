
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x1, y1, x2, y2;
    static char[][] board;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static boolean outOfRange(int r, int c) {
        return !(1 <= r && r <= N && 1 <= c && c <= M);
    }

    static void printBoard(int time) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n === time: %d === \n", time));
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        x1 = inputs[0];
        y1 = inputs[1];
        x2 = inputs[2];
        y2 = inputs[3];

        board = new char[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = line.charAt(j - 1);
            }
        }

        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> nq = new ArrayDeque<>();

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[x1][y1] = true;
        q.add(new int[] { x1, y1 });

        boolean isFind = false;
        while (!isFind) {
            ans++;
            while (!q.isEmpty()) {
                int[] polled = q.poll();
                int r = polled[0], c = polled[1];
                for (int d = 0; d < dir.length; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (outOfRange(nr, nc) || visited[nr][nc]) {
                        continue;
                    }
                    if (board[nr][nc] == '0') {
                        visited[nr][nc] = true;
                        q.add(new int[] { nr, nc });
                    } else if (board[nr][nc] == '1') {
                        visited[nr][nc] = true;
                        nq.add(new int[] { nr, nc });
                    } else if (board[nr][nc] == '#') {
                        isFind = true;
                        break;
                    }
                }

            }

            int size = nq.size();
            for (int i = 0; i < size; i++) {
                int[] polled = nq.poll();
                board[polled[0]][polled[1]] = '0';
                q.add(polled);
            }

            // printBoard(ans);
        }
        System.out.println(ans);
    }

}