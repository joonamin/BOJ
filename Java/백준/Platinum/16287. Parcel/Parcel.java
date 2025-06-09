import java.io.*;
import java.util.*;

public class Main {
    static int w, n, v[];
    static Map<Integer, Integer> map = new HashMap<>();

    private static String solve() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = v[i] + v[j];
                int val = map.getOrDefault(sum, -1);
                if (val == -1)
                    map.put(sum, i);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sum = v[i] + v[j];
                if (map.containsKey(w - sum)) {
                    int val = map.get(w - sum);
                    if (val < i && val < j) {
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(v);
        System.out.println(solve());
    }

}