import java.io.*;
import java.util.*;

public class Main {

    private static int counting(int l, int r, int n) {
        if (l >= r)
            return 0;

        int result = 0;
        int left = Math.min(l, n);
        for (int i = left; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                if (i <= n && n <= j)
                    result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int[] S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> set = new HashSet<>();
        Arrays.sort(S);
        Arrays.stream(S).forEach(e -> set.add(e));

        int n = Integer.parseInt(br.readLine());
        if (set.contains(n)) {
            System.out.println(0);
        } else if (n < S[0]) {
            System.out.println(counting(1, S[0] - 1, n));
        } else {
            int l = 0, r = L - 1;
            for (int i = 0; i < L; i++) {
                if (n > S[i] && l < i) {
                    l = i;
                }
                if (n < S[i] && r > i) {
                    r = i;
                }
            }
            System.out.println(counting(S[l] + 1, S[r] - 1, n));
        }
    }
}