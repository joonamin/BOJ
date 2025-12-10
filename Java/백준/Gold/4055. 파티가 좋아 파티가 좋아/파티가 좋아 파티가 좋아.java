
import java.io.*;
import java.util.*;

public class Main {

    private static final String fmt = "On day %d Emma can attend as many as %d parties.\n";

    private static int solve(int[][] times) {
        int result = 0;

        Arrays.sort(times, (int[] a, int[] b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int slice = 0;
        // 08:00 ~ 23:00 까지 30분 단위로 slice
        // 현재 선택할 수 있는 party가 있다면, 선택할 수 있는 party중 종료시간이 제일 짧은 것

        boolean[] check = new boolean[times.length];
        while (slice < 40) {
            double sc = 8.0 + (slice * 0.5);
            double ec = 8.0 + ((slice + 1) * 0.5);
            for (int i = 0; i < times.length; i++) {
                if (check[i])
                    continue;
                if (times[i][0] <= sc && ec <= times[i][1]) {
                    check[i] = true;
                    result++;
                    break;
                }
            }
            slice++;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 0;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            int[][] times = new int[N][];
            for (int i = 0; i < N; i++) {
                times[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int res = solve(times);
            sb.append(String.format(fmt, ++tc, res));
        }
        System.out.println(sb.toString());
    }

}