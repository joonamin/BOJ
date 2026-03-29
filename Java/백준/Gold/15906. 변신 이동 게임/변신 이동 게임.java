import java.io.*;
import java.util.*;

public class Main {
    static int N, T, R, C;
    static char[][] board;
    static int[][][][] warp;
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static int[][][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[N][N];
        warp = new int[N][N][4][2];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                board[i][j] = line[j];
                for (int d = 0; d < 4; d++) {
                    warp[i][j][d][0] = -1;
                    warp[i][j][d][1] = -1;
                }
            }
        }

        initWarp();

        // dist[r][c][mode] := 해당 위치와 모드(0: 일반, 1: 변신)에 도달하는 최단거리
        // {acdist, r, c, mode}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });

        dist = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], 0x3f3f3f3f);
            }
        }

        dist[0][0][0] = 0;
        pq.add(new int[] { 0, 0, 0, 0 });

        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int acdist = polled[0], r = polled[1], c = polled[2], mode = polled[3];

            // 목적지 도착 시 조기 종료
            if (r == R - 1 && c == C - 1) {
                System.out.println(acdist);
                return;
            }

            if (dist[r][c][mode] < acdist)
                continue;

            if (mode == 0) {
                // 1. 일반 이동 (상하좌우 1칸, 1턴 소모)
                for (int d = 0; d < dir.length; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                        continue;

                    if (dist[nr][nc][0] > acdist + 1) {
                        dist[nr][nc][0] = acdist + 1;
                        pq.add(new int[] { acdist + 1, nr, nc, 0 });
                    }
                }

                // 2. 모드 전환: 일반 모드 -> 변신 모드 (제자리에서 t턴 소모)
                if (dist[r][c][1] > acdist + T) {
                    dist[r][c][1] = acdist + T;
                    pq.add(new int[] { acdist + T, r, c, 1 });
                }
            } else {
                // 1. 모드 복귀: 변신 모드 -> 일반 모드 (제자리에서 0턴 소모)
                if (dist[r][c][0] > acdist) {
                    dist[r][c][0] = acdist;
                    pq.add(new int[] { acdist, r, c, 0 });
                }

                // 2. 워프 이동: 가장 가까운 워프 지점으로 이동 (1턴 소모)
                for (int d = 0; d < dir.length; d++) {
                    int wnr = warp[r][c][d][0];
                    int wnc = warp[r][c][d][1];

                    if (wnr != -1 && wnc != -1) {
                        if (dist[wnr][wnc][1] > acdist + 1) {
                            dist[wnr][wnc][1] = acdist + 1;
                            pq.add(new int[] { acdist + 1, wnr, wnc, 1 });
                        }
                    }
                }
            }
        }

        System.out.println(Math.min(dist[R - 1][C - 1][0], dist[R - 1][C - 1][1]));
    }

    private static void initWarp() {
        for (int c = 0; c < N; c++) {
            // 상(0) 방향 - 위에서 아래로 스캔
            int lastR = -1;
            for (int r = 0; r < N; r++) {
                warp[r][c][0][0] = lastR;
                warp[r][c][0][1] = (lastR == -1) ? -1 : c;
                if (board[r][c] == '#')
                    lastR = r;
            }
            // 하(1) 방향 - 아래에서 위로 스캔
            lastR = -1;
            for (int r = N - 1; r >= 0; r--) {
                warp[r][c][1][0] = lastR;
                warp[r][c][1][1] = (lastR == -1) ? -1 : c;
                if (board[r][c] == '#')
                    lastR = r;
            }
        }

        for (int r = 0; r < N; r++) {
            // 좌(2) 방향 - 왼쪽에서 오른쪽으로 스캔
            int lastC = -1;
            for (int c = 0; c < N; c++) {
                warp[r][c][2][0] = (lastC == -1) ? -1 : r;
                warp[r][c][2][1] = lastC;
                if (board[r][c] == '#')
                    lastC = c;
            }
            // 우(3) 방향 - 오른쪽에서 왼쪽으로 스캔
            lastC = -1;
            for (int c = N - 1; c >= 0; c--) {
                warp[r][c][3][0] = (lastC == -1) ? -1 : r;
                warp[r][c][3][1] = lastC;
                if (board[r][c] == '#')
                    lastC = c;
            }
        }
    }
}