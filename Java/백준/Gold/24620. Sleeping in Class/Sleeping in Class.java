
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] v = new int[N], prefixSum = new int[N];
            for (int i = 0; i < N; i++) {
                v[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = (i == 0 ? 0 : prefixSum[i - 1]) + v[i];
            }

            Set<Integer> divisors = new HashSet<>();
            for (int i = 1; i * i <= prefixSum[N - 1]; i++) {
                if (prefixSum[N - 1] % i == 0) {
                    divisors.add(i);
                    divisors.add(prefixSum[N - 1] / i);
                }
            }
            int ans = prefixSum[N - 1] == 0 ? 0 : N;
            for (int div : divisors) {
                int i, acc = 0, acc_count = 0, tgt = prefixSum[N - 1] / div;
                boolean check = true;
                for (i = 0; i < N; i++) {
                    acc += v[i];
                    if (acc > tgt) {
                        check = false;
                        break;
                    }
                    if (acc == tgt) {
                        acc = 0;
                    } else if (acc < tgt) {
                        acc_count++;
                    }
                }
                if (i == N && acc != 0) {
                    check = false;
                }
                if (check) {
                    ans = Math.min(ans, acc_count);
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}