
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H, W, board[][];
    static int[] src, dest;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        board = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= M; j++) {
                board[i][j] = input[j - 1];
            }
        }
        // [a, b] ~ [a + H - 1, b + W - 1] 사이에 1이 존재하는지 유무체크
        // 직사각형의 경계만 체크하면 됨
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        H = input[0];
        W = input[1];
        src = new int[] { input[2], input[3] };
        dest = new int[] { input[4], input[5] };

        // queue
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<int[]> q = new ArrayDeque<>();
        visited[src[0]][src[1]] = true;
        q.add(new int[] { src[0], src[1], 0 });
        int ans = -1;
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int r = polled[0], c = polled[1], acdist = polled[2];
            // System.out.printf("r: %d, c: %d, acdist: %d\n", r, c, acdist);
            if (r == dest[0] && c == dest[1]) {
                ans = acdist;
                break;
            }
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (checkOutOfRange(nr, nc))
                    continue;
                if (checkExistedWall(nr, nc))
                    continue;
                if (visited[nr][nc])
                    continue;
                visited[nr][nc] = true;
                q.add(new int[] { nr, nc, acdist + 1 });
            }
        }
        System.out.println(ans);
    }

    private static boolean checkExistedWall(int r, int c) {
        // 경계만 확인
        for (int i = 0; i < H; i++) {
            if (board[r + i][c] == 1 || board[r + i][c + W - 1] == 1)
                return true;
        }
        for (int j = 0; j < W; j++) {
            if (board[r][c + j] == 1 || board[r + H - 1][c + j] == 1)
                return true;
        }
        return false;
    }

    private static boolean checkOutOfRange(int r, int c) {
        if (c > M || c <= 0 || c + W - 1 > M)
            return true;
        if (r > N || r <= 0 || r + H - 1 > N)
            return true;
        return false;
    }

}