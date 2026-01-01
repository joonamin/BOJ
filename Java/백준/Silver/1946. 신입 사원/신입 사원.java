
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] v = new int[N][];
            for (int i = 0; i < N; i++) {
                v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            Arrays.sort(v, (a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            });
            int last = 0x3f3f3f3f;
            int ans = 0;
            // v[i][0] <= last[i][0] 인 것이 보장됨. 이 경우 선발된 다른 인원들보다 점수가 하나라도 더 좋으려면 2차 기준이 더
            // 좋아야함
            for (int i = 0; i < N; i++) {
                if (last > v[i][1]) {
                    last = v[i][1];
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}