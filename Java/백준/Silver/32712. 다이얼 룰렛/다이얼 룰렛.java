import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = temp[0], K = temp[1];
        long[] A = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long[] rightSum = new long[N];
        rightSum[0] = A[0];
        for (int i = 1; i < N; i++) {
            rightSum[i] = rightSum[i - 1] + A[i];
        }

        long[] leftSum = new long[N];
        leftSum[0] = A[N - 1];
        for (int i = 1; i < N; i++) {
            leftSum[i] = leftSum[i - 1] + A[N - 1 - i];
        }

        long ans = -1;
        for (int m = 0; m < Math.min(K, N); m++) {
            long result1 = rightSum[m] + Math.max(0, K - m - 1) * A[m];
            long result2 = leftSum[m] + Math.max(0, K - m - 1) * A[N - 1 - m];
            // System.out.println("m: " + m + ", iter: " + (K - m) + ", result: " + result1
            // + ", " + result2);
            ans = Math.max(ans, Math.max(result1, result2));
        }

        System.out.println(ans);
    }
}