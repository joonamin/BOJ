
import java.io.*;
import java.util.*;

public class Main {

    static int n, capa[];
    static List<int[]>[] adj = new List[100001];
    static int[][][] parent;

    private static void memo(int prev, int current) {
        for (int[] nxt : adj[current]) {
            if (nxt[0] == prev)
                continue;
            int next = nxt[0], cost = nxt[1];
            parent[0][next] = new int[] { current, cost };
            memo(current, next);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        capa = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            capa[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            adj[u].add(new int[] { v, c });
            adj[v].add(new int[] { u, c });
        }

        parent = new int[17][n + 1][2];
        parent[0][1] = new int[] { 1, 0 };
        memo(-1, 1);
        for (int level = 1; level < 17; level++) {
            for (int i = 1; i <= n; i++) {
                int node = parent[level - 1][i][0];
                parent[level][i][0] = parent[level - 1][node][0];
                parent[level][i][1] = parent[level - 1][i][1] + parent[level - 1][node][1];
                // System.out.println("level: " + level + ", node: " + node + ", parent[" +
                // level + "][" + i + "] = "
                // + parent[level][i][0] + ", " + parent[level][i][1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int tgt = i;
            for (int depth = 16; depth >= 0; depth--) {
                if (parent[depth][tgt][1] > capa[i])
                    continue;
                capa[i] -= parent[depth][tgt][1];
                tgt = parent[depth][tgt][0];
            }
            sb.append(tgt).append("\n");
        }
        System.out.println(sb);
    }

}