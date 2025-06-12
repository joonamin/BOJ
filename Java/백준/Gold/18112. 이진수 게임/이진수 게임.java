import java.io.*;
import java.util.*;

public class Main {
    static int[] dist = new int[2049];

    static int toInteger(String s) {
        int result = 0, d = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            result += d * (s.charAt(i) - '0');
            d *= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = toInteger(br.readLine());
        int K = toInteger(br.readLine());
        Arrays.fill(dist, 0x3f3f3f3f);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        dist[L] = 0;
        pq.add(new int[] { 0, L });
        while (!pq.isEmpty()) {
            int cur = pq.poll()[1];
            // 1. 한 자리 숫자를 보수로 바꾸기
            int bitCount = Integer.toBinaryString(cur).length();
            for (int i = 0; i < bitCount - 1; i++) {
                int next = cur ^ (1 << i);
                if (next < 2049 && dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1;
                    pq.add(new int[] { dist[next], next });
                }
            }
            // 2. 현재 수에 +1, -1하기
            if (cur + 1 < 2049 && dist[cur + 1] > dist[cur] + 1) {
                dist[cur + 1] = dist[cur] + 1;
                pq.add(new int[] { dist[cur + 1], cur + 1 });
            }

            if (cur >= 1 && dist[cur - 1] > dist[cur] + 1) {
                dist[cur - 1] = dist[cur] + 1;
                pq.add(new int[] { dist[cur - 1], cur - 1 });
            }
        }
        System.out.println(dist[K]);
    }
}