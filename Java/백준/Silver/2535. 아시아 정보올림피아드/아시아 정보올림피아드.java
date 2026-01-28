
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] lines = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                v[i][j] = Integer.parseInt(lines[j]);
            }
        }
        Arrays.sort(v, (a, b) -> {
            return -Integer.compare(a[2], b[2]);
        });
        Map<Integer, Integer> quarter = new HashMap<>();
        int[][] ans = new int[3][2];
        for (int i = 0, idx = 0; i < 3 && idx < N; idx++) {
            if (quarter.getOrDefault(v[idx][0], 0) + 1 >= 3) {
                continue;
            }
            quarter.put(v[idx][0], quarter.getOrDefault(v[idx][0], 0) + 1);
            ans[i][0] = v[idx][0];
            ans[i][1] = v[idx][1];
            i++;
        }
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d %d\n", ans[i][0], ans[i][1]);
        }
    }

}