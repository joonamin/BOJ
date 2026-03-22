
import java.io.*;
import java.util.*;

public class Main {
    static int[] acOddSum, acEvenSum;
    static int[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        init(v);
        int ans = 0;
        for (int i = 0; i < N; i += 2) {
            int changeIf = getPrevSum(i) + getNextSum(i + 1);
            ans = Math.max(ans, changeIf);
        }
        for (int i = 1; i < N; i += 2) {
            int changeIf = getPrevSum(i + 1) + getNextSum(i) - v[N - 1];
            ans = Math.max(ans, changeIf);
        }
        System.out.println(ans);
    }

    private static void init(int[] v) {
        acOddSum = new int[v.length];
        acEvenSum = new int[v.length];
        acEvenSum[0] = v[0];
        acOddSum[1] = v[1];
        for (int i = 2; i < v.length; i++) {
            if (i % 2 == 0) {
                acEvenSum[i] = acEvenSum[i - 2] + v[i];
            } else {
                acOddSum[i] = acOddSum[i - 2] + v[i];
            }
        }
    }

    private static int getPrevSum(int tgt) {
        // 0, 2, 4, ..., tgt - 2까지 뽑은 경우
        if (tgt == 0)
            return 0;
        return acEvenSum[tgt - 2];
    }

    // i를 기준 getPrevSum(i) + getNextSum(i + 1)
    private static int getNextSum(int tgt) {
        // tgt부터 끝까지 뽑은 것들의 합
        // tgt은 짝수에서 홀수로 전환됨
        int prev = tgt < 2 ? 0 : acOddSum[tgt - 2];
        return acOddSum[v.length - 1] - prev;
    }

}