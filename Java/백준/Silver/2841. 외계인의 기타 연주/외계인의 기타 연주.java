
import java.io.*;
import java.util.*;

public class Main {

    static int N, P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        P = input[1];
        // stack을 항상 오름차순으로 유지
        Deque<Integer>[] dq = new Deque[N + 1];
        for (int i = 0; i <= N; i++) {
            dq[i] = new ArrayDeque<>();
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = line[0], v = line[1];
            while (!dq[u].isEmpty() && dq[u].peekFirst() > v) {
                dq[u].pollFirst();
                ans++;
            }
            if (dq[u].isEmpty() || dq[u].peekFirst() != v) {
                ans++;
                dq[u].addFirst(v);
            }
        }
        System.out.println(ans);
    }

}