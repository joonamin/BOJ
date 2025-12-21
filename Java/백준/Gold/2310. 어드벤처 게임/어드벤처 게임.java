
import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        public char type;
        public int amount;

        public Node(char type, int amount) {
            this.type = type;
            this.amount = amount;
        }
    }

    static Map<Integer, Node> map;
    static List<List<Integer>> list;
    static int N;

    static boolean canGo() {
        // 소지금 k를 가지고 i번째에 도착하는 경우
        int[] d = new int[N + 1];
        Arrays.fill(d, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return -Integer.compare(a[1], b[1]);
        });
        // 초기 금액 0
        if (map.get(1).type == 'L' || map.get(1).type == 'E') {
            int t = Math.max(map.get(1).amount, 0);
            d[1] = t;
            pq.add(new int[] { 1, t });
        } else if (map.get(1).type == 'T' && map.get(1).amount <= 0) {
            int t = 0 - map.get(1).amount;
            d[1] = t;
            pq.add(new int[] { 1, t });
        }
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int cur = polled[0], gold = polled[1];
            if (gold < d[cur])
                continue;
            for (int next : list.get(cur)) {
                Node node = map.get(next);
                if (node.type == 'T' && node.amount > gold)
                    continue;
                int nGold = gold;
                if (node.type == 'T') {
                    nGold = gold - node.amount;
                } else if (node.type == 'L') {
                    nGold = Math.max(node.amount, gold);
                }
                if (d[next] >= nGold)
                    continue;
                d[next] = nGold;
                pq.add(new int[] { next, nGold });
            }
        }
        return d[N] != -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            map = new HashMap<>();
            list = new ArrayList<>();
            list.add(new ArrayList<>());
            for (int i = 1; i <= N; i++) {
                list.add(new ArrayList<>());
                String[] line = br.readLine().split(" ");
                Node node = new Node(line[0].charAt(0), Integer.parseInt(line[1]));
                map.put(i, node);
                for (int j = 2; j < line.length - 1; j++) {
                    list.get(i).add(Integer.parseInt(line[j]));
                }
            }
            if (canGo()) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
        System.out.println(sb);
    }

}