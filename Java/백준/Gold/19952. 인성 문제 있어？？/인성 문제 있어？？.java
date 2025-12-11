
import java.io.*;
import java.util.*;

public class Main {
    static int H, W, O, F, src[], dest[];
    static int[][] board = new int[101][101];
    static int[][] visited = new int[101][101];
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static String solve() {
        // 특정 위치 [i][j] 에 도달할 때, 이전에 방문했던 원소중 힘이 현재 원소보다 더 클 경우 더 진행 X
        // 이 경우에는 이것보다 더 최상의 경로를 찾을 수 없다는 것이 보장되기 때문
        int[][] dist = new int[H + 1][W + 1];
        for (int i = 0; i <= H; i++) {
            Arrays.fill(dist[i], -1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return -Integer.compare(a[2], b[2]);
        });
        dist[src[0]][src[1]] = F;
        pq.add(new int[] { src[0], src[1], F });
        while (!pq.isEmpty()) {
            int r = pq.peek()[0], c = pq.peek()[1], f = pq.peek()[2];
            pq.poll();
            if (r == dest[0] && c == dest[1])
                return "잘했어!!";
            for (int d = 0; d < dir.length; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (nr <= 0 || nr > H || nc <= 0 || nc > W || f - 1 < 0 || dist[nr][nc] >= f - 1) {
                    continue;
                }
                int threshold = Math.max(0, board[nr][nc] - board[r][c]);
                if (f < threshold) {
                    continue;
                }
                dist[nr][nc] = f - 1;
                pq.add(new int[] { nr, nc, f - 1 });
            }
        }
        return "인성 문제있어??";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            src = new int[2];
            dest = new int[2];
            for (int i = 0; i < 101; i++) {
                Arrays.fill(board[i], 0);
                Arrays.fill(visited[i], 0x3f3f3f3f);
            }
            H = inputs[0];
            W = inputs[1];
            O = inputs[2];
            F = inputs[3];
            src[0] = inputs[4];
            src[1] = inputs[5];
            dest[0] = inputs[6];
            dest[1] = inputs[7];
            for (int i = 0; i < O; i++) {
                inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int r = inputs[0], c = inputs[1], h = inputs[2];
                board[r][c] = h;
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb.toString());
    }

}