
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, C; // {N * N 크기의 맵(1시작), M: 승객수, C: 최초 연료양}
    static int[][][][] dist; // [a][b] -> [c][d] 거리
    static int[][] info; // [i]번 승객의 출발지, 도착지 정보
    static int[][] grid;

    private static void initDist() {
        final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                for (int k = 0; k < dist[i][j].length; k++) {
                    Arrays.fill(dist[i][j][k], 0x3f3f3f3f);
                }
            }
        }
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                Queue<int[]> q = new ArrayDeque<>();
                dist[a][b][a][b] = 0;
                q.add(new int[] { a, b, 0 });
                while (!q.isEmpty()) {
                    int r = q.peek()[0], c = q.peek()[1], ac = q.peek()[2];
                    q.poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dir[d][0], nc = c + dir[d][1];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N || grid[nr][nc] == 1 || dist[a][b][nr][nc] <= ac + 1)
                            continue;
                        dist[a][b][nr][nc] = ac + 1;
                        q.add(new int[] { nr, nc, ac + 1 });
                    }
                }
            }
        }
    }

    private static int solve(int[] start) {
        initDist();
        int result = C;
        boolean[] arrived = new boolean[M];

        int cnt = 0;
        int[] current = new int[] { start[0], start[1] };
        while (cnt < M) {
            int tgtIdx = -1;
            for (int i = 0; i < M; i++) {
                if (tgtIdx == -1
                        || dist[current[0]][current[1]][info[tgtIdx][0]][info[tgtIdx][1]] > dist[current[0]][current[1]][info[i][0]][info[i][1]]) {
                    if (!arrived[i])
                        tgtIdx = i;
                }
            }
            // 태워야할 승객 -> tgtIdx
            // System.out.printf("현재 위치: (%d, %d), 남은 연료의 양: %d / ", current[0], current[1],
            // result);
            // System.out.printf("%d번째로 태워야하는 승객의 위치: (%d, %d)\n", cnt, info[tgtIdx][0],
            // info[tgtIdx][1]);
            if (result < dist[current[0]][current[1]][info[tgtIdx][0]][info[tgtIdx][1]]) {
                break;
            }
            result -= dist[current[0]][current[1]][info[tgtIdx][0]][info[tgtIdx][1]];
            current = new int[] { info[tgtIdx][0], info[tgtIdx][1] };
            int[] dest = new int[] { info[tgtIdx][2], info[tgtIdx][3] };
            // System.out.printf("%d번째손님 목적지 위치: (%d, %d), 거리: %d\n", cnt, dest[0], dest[1],
            // dist[current[0]][current[1]][dest[0]][dest[1]]);
            if (dist[current[0]][current[1]][dest[0]][dest[1]] > result) {
                break;
            }
            result += dist[current[0]][current[1]][dest[0]][dest[1]];
            arrived[tgtIdx] = true;
            current[0] = dest[0];
            current[1] = dest[1];
            cnt++;
        }

        return cnt < M ? -1 : result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        C = Integer.parseInt(temp[2]);

        grid = new int[N][N];
        info = new int[M][4];
        dist = new int[N][N][N][N];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e) - 1).toArray();
        for (int i = 0; i < M; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e) - 1).toArray();
        }
        Arrays.sort(info, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        System.out.println(solve(start));
    }

}