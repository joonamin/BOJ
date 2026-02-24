
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
            StringTokenizer stQuery = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(stQuery.nextToken());
            
            if (cmd == 1) {
                int val = Integer.parseInt(stQuery.nextToken());
                if (A[val] == 1) {
                    A[val] = 0;
                    ts.remove(val);
                } else {
                    A[val] = 1;
                    ts.add(val);
                }
            } else if (cmd == 2) {
                int val = Integer.parseInt(stQuery.nextToken());
                cur = (cur - 1 + val) % N + 1;
            } else if (cmd == 3) {
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