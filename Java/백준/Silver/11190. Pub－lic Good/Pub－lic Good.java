import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int color[];
    static List<Integer>[] adj = new List[10001];

    private static boolean coloring(int start) {
        int count = 0;
        color[start] = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { start, 1 });
        while (!q.isEmpty()) {
            int cur = q.peek()[0], preColor = q.peek()[1];
            q.poll();
            int nextColor = (preColor == 1) ? 0 : 1;
            for (int next : adj[cur]) {
                if (color[next] == -1) {
                    color[next] = nextColor;
                    q.add(new int[] { next, nextColor });
                }
            }
            count++;
        }
        return count > 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 각각의 집들로 부터 walkway's distance 에 적어도 하나의 pub을 포함
        // 하나의 pub은 walkway's distance에 적어도 하나의 집은 포함
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        color = new int[n + 1];
        Arrays.fill(color, -1);
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            adj[u].add(v);
            adj[v].add(u);
        }
        // pub이 있는 construction들은 집합에 포함시킨다.
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            if (color[i] == -1 && !coloring(i)) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            System.out.println("Impossible");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (color[i] == 1) {
                    sb.append("pub ");
                } else {
                    sb.append("house ");
                }
            }
            System.out.println(sb);
        }
    }
}