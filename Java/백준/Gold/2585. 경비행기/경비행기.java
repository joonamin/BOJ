
import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static List<int[]> positions = new ArrayList<>();
    static int[][] srcDist, destDist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        positions.add(new int[] { 0, 0 });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            positions.add(new int[] { x, y });
        }
        positions.add(new int[] { 10000, 10000 });

        // 연료통이 m개 있을 때 k번안에 도달할 수 있는지 체크
        int l = 1, r = 1000000000, ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (f(m)) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean f(int m) {
        // {curIdx, passed}
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 2];
        visited[0] = true;
        q.add(new int[] { 0, 0 });
        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int curIdx = polled[0], passed = polled[1];
            for (int nxt = 0; nxt < n + 2; nxt++) {
                if (visited[nxt])
                    continue;
                int cost = calcF(curIdx, nxt);
                if (cost > m || passed + 1 > k + 1)
                    continue;
                if (nxt == n + 1)
                    return true;
                visited[nxt] = true;
                q.add(new int[] { nxt, passed + 1 });
            }
        }
        return false;
    }

    private static int calcF(int fromIdx, int toIdx) {
        int[] f = positions.get(fromIdx);
        int[] t = positions.get(toIdx);
        double d = Math.sqrt((f[0] - t[0]) * (f[0] - t[0]) + (f[1] - t[1]) * (f[1] - t[1])) / 10;
        return (int) Math.ceil(d);
    }

}