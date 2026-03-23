
import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            dq.addLast(i);
        }
        while (dq.size() > 1) {
            dq.pollFirst();
            int e2 = dq.pollFirst();
            dq.addLast(e2);
        }
        System.out.println(dq.poll());
    }

}