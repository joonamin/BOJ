
import java.io.*;
import java.util.*;

public class Main {
    static int N, A[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] mins = new int[N];
        mins[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            mins[i] = Math.min(mins[i + 1], A[i]);
        }

        long sum = 0L;
        for (int i = 0; i < N; i++) {
            sum += mins[i];
        }
        System.out.println(sum);
    }

}