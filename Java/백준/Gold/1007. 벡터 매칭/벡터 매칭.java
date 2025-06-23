import java.io.*;
import java.util.*;

public class Main {
    static int[][] v;

    private static double solve() {
        int N = v.length;
        // 벡터의 합은 교환법칙 성립
        // 결국 시점, 종점에 대한 구분만 필요
        double ans = Double.MAX_VALUE;
        for (int i = 0; i < (1 << N); i++) {
            if (Integer.bitCount(i) != N / 2)
                continue;
            List<Integer> A = new ArrayList<>(N / 2);
            List<Integer> B = new ArrayList<>(N / 2);
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) == 0) {
                    A.add(j);
                } else {
                    B.add(j);
                }
            }
            long[] resultVector = new long[] { 0, 0 };
            for (int j = 0; j < N / 2; j++) {
                resultVector[0] += (v[B.get(j)][0] - v[A.get(j)][0]);
                resultVector[1] += (v[B.get(j)][1] - v[A.get(j)][1]);
            }
            ans = Math.min(ans, Math.sqrt((resultVector[0] * resultVector[0]) + (resultVector[1] * resultVector[1])));
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            v = new int[N][2];
            for (int i = 0; i < N; i++) {
                v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }
}