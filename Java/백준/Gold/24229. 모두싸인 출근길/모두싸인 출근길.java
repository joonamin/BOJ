
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[][];
    static List<int[]> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(v, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        list = new ArrayList<>();
        int left = v[0][0], right = v[0][1];
        for (int i = 1; i < N; i++) {
            if (v[i][0] <= right) {
                right = Math.max(right, v[i][1]);
            } else {
                list.add(new int[] { left, right });
                left = v[i][0];
                right = v[i][1];
            }
        }
        list.add(new int[] { left, right });

        // list.stream().forEach(e -> System.out.printf("[%d, %d] \n", e[0], e[1]));
        int idx = -1, rb = 0;
        for (int i = 0; i < list.size(); i++) {
            if (rb >= list.get(i)[0]) {
                idx = i;
                int len = list.get(i)[1] - list.get(i)[0];
                rb = Math.max(rb, list.get(i)[1] + len);
            }
        }

        System.out.println(list.get(idx)[1]);
    }

}