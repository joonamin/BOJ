
import java.io.*;
import java.util.*;

public class Main {

    static int N, prefixSum[];
    static List<Integer> primes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        init();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
            sb.append(solve(l, r)).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        primes = new ArrayList<>();
        boolean[] isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        isPrime[2] = true;
        for (int i = 3; i <= 1000000; i++) {
            if (!isPrime[i])
                continue;
            boolean flag = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            isPrime[i] = flag;
            if (flag) {
                for (int j = i + i; j <= 1000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        prefixSum = new int[1000001];
        for (int i = 1; i <= 1000000; i++) {
            if (isPrime[i]) {
                primes.add(i);
                prefixSum[i] = prefixSum[i - 1] + 1;
            } else {
                prefixSum[i] = prefixSum[i - 1];
            }
        }
    }

    private static int solve(int left, int right) {
        // 소수의 개수
        int count = prefixSum[right] - prefixSum[left - 1];
        // System.out.printf("solve(left: %d, right: %d) -> count: %d\n", left, right,
        // count);
        if (count % 2 == 0)
            return -1;

        // list에서 lowerBound찾기
        int l = largerThan(left);
        int r = lessThan(right);
        if (l == -1 || r == -1 || (r - l + 1) % 2 == 0) {
            return -1;
        }
        int m = (l + r) / 2;
        return primes.get(m);
    }

    private static int largerThan(int value) {
        int result = -1;
        int l = 0, r = primes.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (primes.get(m) >= value) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return result;
    }

    private static int lessThan(int value) {
        int result = -1;
        int l = 0, r = primes.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (primes.get(m) <= value) {
                result = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return result;
    }
}