import java.util.*;
import java.io.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(v);
        long ans = 0;
        for (int i = 0, rank = 1; i < N; i++, rank++) {
            ans += Math.abs(v[i] - rank);
        }
        System.out.println(ans);
    }
}