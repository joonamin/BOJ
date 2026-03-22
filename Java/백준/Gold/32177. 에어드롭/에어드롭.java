
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, T;
    static int[][] info;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        info = new int[N + 1][];
        info[0] = new int[3];
        info[0][0] = Integer.parseInt(st.nextToken());
        info[0][1] = Integer.parseInt(st.nextToken());
        info[0][2] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            info[i] = new int[4];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }

    private static void solve() {
        // 1. 연결성 확인
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        visited[0] = true;
        q.add(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 1; i <= N; i++) {
                if (visited[i])
                    continue;
                if (!check(info[cur][0], info[cur][1], info[i][0], info[i][1], info[cur][2], info[i][2]))
                    continue;
                visited[i] = true;
                q.add(i);
            }
        }
        // 2. 검증 확인
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (info[i][3] == 1 && visited[i]) {
                sb.append(i).append(" ");
            }
        }
        // 3. 디버깅
        // for (int i = 1; i <= N; i++) {
        // System.out.printf("visited[%d] = %s\n", i, visited[i]);
        // }
        ///
        if (sb.length() == 0) {
            sb.append("0");
        }
        System.out.println(sb);
    }

    private static boolean check(int r, int c, int nr, int nc, int cv, int nv) {
        return check(r, c, nr, nc) && Math.abs(nv - cv) <= T;
    }

    private static boolean check(int r, int c, int nr, int nc) {
        int rd = Math.abs(nr - r);
        int cd = Math.abs(nc - c);
        return rd * rd + cd * cd <= K * K;
    }
}