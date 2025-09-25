
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N, M, K, py = 0;
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        // {priority}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(b[0], a[0]);
        });
        for (int i = 0; i < N; i++) {
            int e = Integer.parseInt(br.readLine());
            pq.add(new int[] { e });
        }

        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            int p = pq.poll()[0];
            py = py / 2 + p;
            list.add(py);
            p -= M;
            if (p <= K)
                continue;
            pq.add(new int[] { p });
        }

        System.out.println(list.size());
        list.stream().forEach(e -> sb.append(e).append("\n"));
        System.out.println(sb);
    }

}