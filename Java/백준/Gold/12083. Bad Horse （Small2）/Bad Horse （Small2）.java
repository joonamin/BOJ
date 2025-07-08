
import java.io.*;
import java.util.*;

public class Main {

    static Map<String, List<String>> map;
    static Map<String, Integer> cm;

    private static boolean coloring(String prev, String current, final int color) {
        boolean result = true;
        for (String next : map.getOrDefault(current, List.of())) {
            if (next.equals(prev) || (cm.getOrDefault(next, -1) == (color + 1) % 2))
                continue;
            if (cm.getOrDefault(next, -1) == color)
                return false;
            cm.put(next, (color + 1) % 2);
            result = result && coloring(current, next, (color + 1) % 2);
        }
        return result;
    }

    private static boolean solve() {
        boolean result = true;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> nexts = entry.getValue();
            if (cm.getOrDefault(key, -1) != -1) {
                continue;
            }
            cm.put(key, 0);
            for (String next : nexts) {
                int cl = cm.getOrDefault(next, -1);
                if (cl == -1)
                    cm.put(next, 1);
                else if (cl == 0)
                    result = false;
                result = result && coloring(key, next, 1);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        map = new HashMap<>();
        cm = new HashMap<>();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #" + tc + ": ");
            map.clear();
            cm.clear();
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                String[] in = br.readLine().split(" ");
                List<String> l1 = map.computeIfAbsent(in[0], k -> new ArrayList<>());
                List<String> l2 = map.computeIfAbsent(in[1], k -> new ArrayList<>());
                l1.add(in[1]);
                l2.add(in[0]);
            }
            if (solve()) {
                sb.append("Yes");
            } else {
                sb.append("No");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}