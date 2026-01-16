
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];

        int ans = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(br.readLine());
            if (v[i] <= 0) {
                minHeap.add(v[i]);
            } else if (v[i] == 1) {
                ans += v[i];
            } else if (v[i] > 0) {
                maxHeap.add(v[i]);
            }
        }
        while (maxHeap.size() >= 2) {
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            ans += a * b;
        }
        if (!maxHeap.isEmpty()) {
            ans += maxHeap.poll();
        }
        while (minHeap.size() >= 2) {
            int a = minHeap.poll();
            int b = minHeap.poll();
            ans += a * b;
        }
        if (!minHeap.isEmpty()) {
            ans += minHeap.poll();
        }
        System.out.println(ans);
    }

}