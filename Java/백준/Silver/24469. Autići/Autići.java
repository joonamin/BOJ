import java.util.*;
import java.io.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(v);
        long sum = 0L;
        for (int i = 1; i < N; i++) {
            sum += 1L * v[i];
        }
        long ans = (N - 1) * 1L * v[0] + sum;
        System.out.println(ans);
    }
}