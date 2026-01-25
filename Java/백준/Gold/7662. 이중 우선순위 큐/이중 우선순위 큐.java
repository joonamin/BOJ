
import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> syncMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            syncMap = new HashMap<>();
            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> -Integer.compare(a, b));
            int Q = Integer.parseInt(br.readLine());
            for (int i = 0; i < Q; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());
                if (cmd == 'D') {
                    PriorityQueue<Integer> tq = (value == 1) ? maxQ : minQ;
                    pollQ(tq);
                } else {
                    syncMap.put(value, syncMap.getOrDefault(value, 0) + 1);
                    minQ.add(value);
                    maxQ.add(value);
                }
            }
            cleanQ(minQ);
            cleanQ(maxQ);
            Integer min = minQ.peek();
            Integer max = maxQ.peek();
            if ((min == null && max != null) || (min != null && max == null)) {
                assert (false);
            }
            if (min == null && max == null) {
                sb.append("EMPTY\n");
            } else {
                sb.append(String.format("%d %d\n", max, min));
            }
        }
        System.out.println(sb);
    }

    private static Integer pollQ(PriorityQueue<Integer> pq) {
        cleanQ(pq);
        if (!pq.isEmpty()) {
            int value = pq.poll();
            syncMap.put(value, syncMap.getOrDefault(value, 0) - 1);
            return value;
        }
        return null;
    }

    private static void cleanQ(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty() && syncMap.get(pq.peek()) == 0) {
            pq.poll();
        }
    }
}