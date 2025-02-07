import java.util.*;
import java.io.*;

public class Main {
    static int N, Q, v[];
    static int[] xors;

    private static int query(int s, int e) {
        return xors[e] ^ xors[s - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        v = new int[N + 1];
        xors = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
            xors[i] = xors[i - 1] ^ v[i];
        }

        int result = 0;
        while (Q-- > 0) {
            int[] q = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            result ^= query(q[0], q[1]);
        }
        System.out.println(result);
    }
}