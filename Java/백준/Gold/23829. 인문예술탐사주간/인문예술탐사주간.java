
import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, P[];
    static long[] negSum, posSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        Q = input[1];
        P = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(P);
        // abs(p1-x) + abs(p2-x) + ... + abs(pn - x)
        // abs(sum(p1,...,pn) - n * x) 와 같은가? 다르군..
        // (1-10) + (2-10) + ... + (5-10) = 9 + 8 + 7 + 6 + 5 = 28 + 7 = 35
        // 1+2+3+4+5-5*10 = 45
        // 차가 양수가 보장되는 구간, 음수가 보장되는 구간을 구분하여 각각을 계산하여 더해주는 방식은?
        // 음수일 경우에 각각을 -1 -2 -3 으로보고 누적합을 계산한 것 (1, 3) => (-1, -3)
        // sum_neg(start_idx-1) + (start_idx + 1) * x
        // 수식상으로 해결됨
        init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int item = Integer.parseInt(br.readLine());
            int lastIndex = findIndex(item);
            long posCnt = N - lastIndex - 1;
            long negCnt = lastIndex + 1;
            long res1 = (lastIndex < N - 1) ? getPositiveSum(lastIndex + 1) - (long) posCnt * item : 0;
            long res2 = (lastIndex >= 0) ? getNegativeSum(lastIndex) + (long) negCnt * item : 0;
            sb.append(res1 + res2).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        negSum = new long[N];
        posSum = new long[N];
        negSum[0] = -P[0];
        posSum[0] = P[0];
        for (int i = 1; i < N; i++) {
            negSum[i] = negSum[i - 1] - P[i];
            posSum[i] = posSum[i - 1] + P[i];
        }
    }

    // 합이 음수가 되는 첫번째 지점
    private static int findIndex(int item) {
        int result = -1;
        int l = 0, r = N - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (P[m] < item) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return result;
    }

    private static long getPositiveSum(int startIndex) {
        long prev = 0L;
        if (startIndex >= 1)
            prev = posSum[startIndex - 1];
        return posSum[N - 1] - prev;
    }

    private static long getNegativeSum(int endIndex) {
        return negSum[endIndex];
    }

}