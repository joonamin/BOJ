
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K = 0;
    static int[][] board;
    static final int[][] dir = { { -1, 0 }, { 0, -1 }, { -1, 1 }, { -1, -1 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], -1);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Set<Integer> nears = nearBy(i, j);
                boolean addNew = true;
                for (int k = 1; k <= K; k++) {
                    if (!nears.contains(k)) {
                        addNew = false;
                        board[i][j] = k;
                        break;
                    }
                }
                if (addNew) {
                    board[i][j] = ++K;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(K).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static Set<Integer> nearBy(int r, int c) {
        Set<Integer> result = new HashSet<>();
        for (int d = 0; d < dir.length; d++) {
            int nr = r + dir[d][0], nc = c + dir[d][1];
            if (nr < 0 || nc < 0 || nr >= N | nc >= M)
                continue;
            result.add(board[nr][nc]);
        }
        return result;
    }

}