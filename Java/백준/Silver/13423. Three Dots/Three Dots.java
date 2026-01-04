
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(input);
            sb.append(solve(N, input)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int N, int[] v) {
        // (a, b) 두 점을 산정했을 때 b에서 시작해서 c에 도달하는 길이가 동일한 점을 선정
        int result = 0;
        // Arrays.stream(v).forEach(e -> System.out.printf("%d ", e));
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int tgtGap = Math.abs(v[j] - v[i]);
                // v[j] + tgtGap에 해당하는 동일한 원소의 개수를 카운팅한다.
                // lowerBound, upperBound 차이
                int l1 = upperBound(v, v[j] + tgtGap);
                int l2 = lowerBound(v, v[j] + tgtGap);
                if (l2 == -1) {
                    continue;
                }
                if (l1 == -1) {
                    result += N - l2;
                } else {
                    result += l1 - l2;
                }
            }
        }
        return result;
    }

    private static int lowerBound(int[] v, int value) {
        int result = -1;
        int l = 0, r = v.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (v[m] >= value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

    private static int upperBound(int[] v, int value) {
        int result = -1;
        int l = 0, r = v.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (v[m] > value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

}