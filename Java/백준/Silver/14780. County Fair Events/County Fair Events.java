import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int i = 0; i < N; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = temp[0], end = start + temp[1] - 1;
            pq.add(new int[] { start, end });
        }

        int current = 0, ans = 0;
        while (!pq.isEmpty()) {
            int start = pq.peek()[0];
            int end = pq.peek()[1];
            pq.poll();
            if (current > start) {
                continue;
            }
            ans++;
            current = end + 1;
        }
        System.out.println(ans);
    }
}