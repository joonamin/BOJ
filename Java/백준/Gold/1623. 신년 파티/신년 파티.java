
import java.io.*;
import java.util.*;

public class Main {
    static int N, score[], dp[][];
    static List<List<Integer>> adj;
    static List<List<List<int[]>>> cand; // {next, state}
    static {
        adj = new ArrayList<>();
        cand = new ArrayList<>();
        for (int i = 0; i < 200_000; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < 2; i++) {
            cand.add(new ArrayList<>());
            for (int j = 0; j < 200_000; j++) {
                cand.get(i).add(new ArrayList<>());
            }
        }
        dp = new int[200_000][2];
        for (int i = 0; i < 200_000; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    static int dfs(int current, int state) {
        if (dp[current][state] != -1) {
            return dp[current][state];
        }

        dp[current][state] = 0;
        if (state == 1) {
            int res = score[current];
            for (int next : adj.get(current)) {
                cand.get(state).get(current).add(new int[] { next, 0 });
                res += dfs(next, 0);
            }
            return dp[current][state] += res;
        }

        int res = 0;

        for (int next : adj.get(current)) {
            int l = dfs(next, 0);
            int r = dfs(next, 1);
            if (l < r) {
                cand.get(state).get(current).add(new int[] { next, 1 });
                res += r;
            } else {
                cand.get(state).get(current).add(new int[] { next, 0 });
                res += l;
            }
        }
        return dp[current][state] = res;
    }

    static void getTrace(List<Integer> trace, int current, int state) {
        if (state == 1) {
            trace.add(current + 1);
        }
        // System.out.printf("current: %d, state: %d\n", current, state);
        // cand.get(state).get(current).forEach(e -> System.out.printf("(%d, %d) / ",
        // e[0], e[1]));
        // System.out.println();
        for (int[] info : cand.get(state).get(current)) {
            int next = info[0], nstate = info[1];
            getTrace(trace, next, nstate);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N];
        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(temp[i]);
        }
        temp = br.readLine().split(" ");
        for (int i = 1; i < N; i++) {
            int parent = Integer.parseInt(temp[i - 1]) - 1;
            adj.get(parent).add(i);
        }
        int withoutBoss = dfs(0, 0);
        int withBoss = dfs(0, 1);
        List<Integer> withoutBossTrace = new ArrayList<>();
        getTrace(withoutBossTrace, 0, 0);
        Collections.sort(withoutBossTrace);
        List<Integer> withBossTrace = new ArrayList<>();
        getTrace(withBossTrace, 0, 1);
        Collections.sort(withBossTrace);

        withoutBossTrace.add(-1);
        withBossTrace.add(-1);

        System.out.printf("%d %d\n", withBoss, withoutBoss);
        StringBuilder sb = new StringBuilder();
        withBossTrace.stream().forEach(e -> sb.append(e).append(" "));
        sb.append("\n");
        withoutBossTrace.stream().forEach(e -> sb.append(e).append(" "));
        System.out.println(sb);
    }

}