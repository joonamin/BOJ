
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int M = Integer.parseInt(tmp[1]);

        Deque<Integer>[] dq = new Deque[200_001];
        for (int i = 1; i < 200_001; i++) {
            dq[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < N; i++) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < v.length; j++) {
                dq[v[j]].addLast(i);
            }
        }

        int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answers = new int[N + 1];
        for (int i = 0; i < B.length; i++) {
            if (!dq[B[i]].isEmpty()) {
                answers[dq[B[i]].pollFirst()]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(answers, 0, N).forEach(e -> sb.append(e + " "));
        System.out.println(sb);
    }

}