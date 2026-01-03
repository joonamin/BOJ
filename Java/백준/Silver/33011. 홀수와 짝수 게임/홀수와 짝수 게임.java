
import java.io.*;
import java.util.*;

public class Main {
    static int N, A[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    private static String solve() {
        int odd = 0, even = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        if (odd == even || Math.max(odd, even) % 2 == 0) {
            return "heeda0528";
        } else {
            return "amsminn";
        }
    }

}