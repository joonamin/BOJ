
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dist = new int[1000001];
        int[] prev = new int[1000001];
        Arrays.fill(dist, 0x3f3f3f3f);
        Arrays.fill(prev, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        dist[N] = 0;
        pq.add(new int[] { N, 0 });
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int cur = polled[0];
            if (dist[cur] < polled[1])
                continue;
            if (cur % 3 == 0 && dist[cur / 3] > dist[cur] + 1) {
                dist[cur / 3] = dist[cur] + 1;
                prev[cur / 3] = cur;
                pq.add(new int[] { cur / 3, dist[cur / 3] });
            }
            if (cur % 2 == 0 && dist[cur / 2] > dist[cur] + 1) {
                dist[cur / 2] = dist[cur] + 1;
                prev[cur / 2] = cur;
                pq.add(new int[] { cur / 2, dist[cur / 2] });
            }
            if (cur >= 2 && dist[cur - 1] > dist[cur] + 1) {
                dist[cur - 1] = dist[cur] + 1;
                prev[cur - 1] = cur;
                pq.add(new int[] { cur - 1, dist[cur - 1] });
            }
        }
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(1);
        while (true) {
            int p = prev[stk.peek()];
            if (p == -1)
                break;
            stk.push(p);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dist[1]).append("\n");
        while (!stk.isEmpty()) {
            sb.append(stk.pop()).append(" ");
        }
        System.out.println(sb);
    }

}