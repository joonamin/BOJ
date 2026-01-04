
import java.io.*;
import java.util.*;

public class Main {

    static int[] costs, inDegree;
    static List<List<Integer>> nexts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        costs = new int[N];
        nexts = new ArrayList<>();
        inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            nexts.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cost = line[0], k = line[1];
            int[] pred = new int[k];

            costs[i] = cost;
            inDegree[i] += k;

            for (int j = 0; j < k; j++) {
                pred[j] = line[2 + j] - 1;
                nexts.get(pred[j]).add(i);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                // {누적거리, 현재}
                ans[i] = costs[i];
                q.add(new int[] { costs[i], i });
            }
        }

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int acdist = polled[0], cur = polled[1];
            for (int next : nexts.get(cur)) {
                ans[next] = Math.max(ans[next], acdist + costs[next]);
                if (--inDegree[next] == 0) {
                    q.add(new int[] { ans[next], next });
                }
            }
        }

        System.out.println(Arrays.stream(ans).max().getAsInt());
    }

}