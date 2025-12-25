import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0) {
                int polled = pq.isEmpty() ? -1 : pq.poll();
                sb.append(polled).append("\n");
            } else {
                int size = input[0];
                for (int j = 1; j <= size; j++) {
                    pq.add(input[j]);
                }
            }
        }
        System.out.println(sb);
    }
}