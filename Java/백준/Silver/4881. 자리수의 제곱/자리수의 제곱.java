
import java.io.*;
import java.util.*;

public class Main {
    private static int solve(int a, int b) {
        Map<Long, Integer> map1 = new HashMap<>();
        Map<Long, Integer> map2 = new HashMap<>();
        long cur = a;
        int idx = 0, ans = 0;
        while (!map1.containsKey(cur)) {
            map1.put(cur, idx);
            long next = 0L;
            while (cur > 0) {
                next += (cur % 10) * (cur % 10);
                cur /= 10;
            }
            cur = next;
            idx++;
        }
        cur = b;
        idx = 0;
        while (!map2.containsKey(cur)) {
            if (map1.containsKey(cur)) {
                ans = map1.get(cur) + idx + 2;
                break;
            }
            map2.put(cur, idx);
            long next = 0L;
            while (cur > 0) {
                next += (cur % 10) * (cur % 10);
                cur /= 10;
            }
            cur = next;
            idx++;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (v[0] == 0 && v[1] == 0)
                break;

            int s1 = solve(v[0], v[1]);
            int s2 = solve(v[1], v[0]);
            sb.append(String.format("%d %d %d\n", v[0], v[1], Math.min(s1, s2)));
        }
        System.out.println(sb);
    }

}