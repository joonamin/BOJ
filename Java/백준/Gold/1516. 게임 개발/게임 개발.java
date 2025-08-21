
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> outgoing = new ArrayList<>();
        for (int i = 0; i < N; i++)
            outgoing.add(new ArrayList<>());
        int[] indegree = new int[N], cost = new int[N];
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            cost[i] = Integer.parseInt(temp[0]);
            int[] info = Arrays.stream(temp, 1, temp.length - 1).mapToInt(obj -> Integer.parseInt(obj) - 1).toArray();
            int cnt = 0;
            for (int ic : info) {
                outgoing.get(ic).add(i);
                cnt++;
            }
            indegree[i] += cnt;
        }

        int[] ans = new int[N];
        // {누적 시간, tgt}
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                ans[i] = cost[i];
                q.add(new int[] { cost[i], i });
            }
        }

        /*
         * 0 -- 1 -- 2 -- 3
         * \ .............\
         * .4.............\
         * ..\----------- 5
         * 
         * 
         */

        while (!q.isEmpty()) {
            int actime = q.peek()[0], tgt = q.peek()[1];
            q.poll();
            for (int next : outgoing.get(tgt)) {
                ans[next] = Math.max(ans[next], actime + cost[next]);
                if (--indegree[next] == 0) {
                    q.add(new int[] { ans[next], next });
                }
            }
        }
        Arrays.stream(ans).forEach(System.out::println);
    }

}