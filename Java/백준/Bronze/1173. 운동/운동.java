
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], m = input[1], M = input[2], T = input[3], R = input[4];
        int ans = 0;
        int X = m;
        if (m + T > M) {
            System.out.println(-1);
            return;
        }
        while (N > 0) {
            if (X + T > M) {
                // 휴식
                X = Math.max(m, X - R);
            } else {
                N--;
                X += T;
            }
            ans++;
        }
        System.out.println(ans);
    }

}