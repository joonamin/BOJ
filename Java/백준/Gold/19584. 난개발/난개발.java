
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], M = input[1];
        int[][] pos = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pos[i] = new int[] { input[0], input[1] };
        }

        // {sy, ey}
        List<int[]> list = new ArrayList<>();
        for (int j = 0; j < M; j++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = input[0], v = input[1], c = input[2];
            int[] pu = pos[u];
            int[] pv = pos[v];
            list.add(new int[] { Math.min(pu[1], pv[1]), c }); // 도로 시작지점
            list.add(new int[] { Math.max(pu[1], pv[1]), -c }); // 도로 마지막 지점
        }
        Collections.sort(list, (a, b) -> {
            if (a[0] == b[0]) {
                return -Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        long ans = 0, cur = 0;
        for (int[] e : list) {
            cur += e[1];
            ans = Math.max(ans, cur);
        }
        System.out.println(ans);
    }

}