
import java.io.*;
import java.util.*;

public class Main {
    static int N, Q, A[], cur = 1;
    static TreeSet<Integer> ts = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] == 1)
                ts.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (line[0] == 1) {
                if (A[line[1]] == 1) {
                    A[line[1]] = 0;
                    ts.remove(line[1]);
                } else {
                    A[line[1]] = 1;
                    ts.add(line[1]);
                }
            } else if (line[0] == 2) {
                cur = (cur - 1 + line[1]) % N + 1;
            } else if (line[0] == 3) {
                NavigableSet<Integer> res = ts.tailSet(cur, true);
                if (res.isEmpty()) {
                    if (ts.headSet(cur).isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        int tgt = N + ts.headSet(cur).first();
                        sb.append(tgt - cur).append("\n");
                    }
                } else {
                    sb.append(res.first() - cur).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

}