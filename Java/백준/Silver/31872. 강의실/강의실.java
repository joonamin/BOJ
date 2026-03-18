
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, A[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int[] dist = new int[N];
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            sum += A[i + 1] - A[i];
            dist[i] = A[i + 1] - A[i];
        }
        if (K >= N - 1) {
            System.out.println(0);
        } else {
            Arrays.sort(dist);
            for (int i = 0; i < K; i++) {
                sum -= dist[N - 1 - i];
            }
            System.out.println(sum);
        }
    }

}