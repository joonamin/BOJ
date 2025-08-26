
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, P, S[];
    static char[][] grid;
    static List<Queue<int[]>> qlist = new ArrayList<>();
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static boolean expand(int tgt) {
        boolean result = false;
        Queue<int[]> q = qlist.get(tgt);
        Queue<int[]> nextq = new ArrayDeque<>();

        // 큐에 넣기 전 marking
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int dist = pos[2];
            for (int d = 0; d < 4; d++) {
                int nr = pos[0] + dir[d][0], nc = pos[1] + dir[d][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || grid[nr][nc] != '.') {
                    continue;
                }
                if (dist + 1 > S[tgt]) {
                    nextq.add(new int[] { pos[0], pos[1], 0 });
                } else {
                    result = true;
                    grid[nr][nc] = (char) ('1' + tgt);
                    q.add(new int[] { nr, nc, dist + 1 });
                }
            }
        }

        qlist.set(tgt, nextq);
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        P = Integer.parseInt(temp[2]);
        S = new int[P];
        grid = new char[N][M];

        temp = br.readLine().split(" ");
        for (int i = 0; i < P; i++) {
            S[i] = Integer.parseInt(temp[i]);
            qlist.add(new ArrayDeque<>());
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] != '.' && grid[i][j] != '#') {
                    qlist.get(grid[i][j] - '1').add(new int[] { i, j, 0 });
                }
            }
        }

        // 라운드마다 1, ..., P번 플레이어들이 이동
        boolean isContinue = true;
        while (isContinue) {
            boolean hasMoved = false;
            for (int i = 0; i < P; i++) {
                hasMoved = expand(i) || hasMoved;
            }
            if (!hasMoved)
                isContinue = false;
        }

        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ('1' <= grid[i][j] && grid[i][j] <= '9')
                    counterMap.put(grid[i][j] - '1', counterMap.getOrDefault(grid[i][j] - '1', 0) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < P; i++) {
            sb.append(counterMap.get(i)).append(" ");
        }
        System.out.println(sb);
    }

}