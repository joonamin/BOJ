
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static Set<Integer> visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new HashSet<>();
        st = new StringTokenizer(br.readLine());

        Queue<long[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int sam = Integer.parseInt(st.nextToken());
            visited.add(sam);
            q.add(new long[] { sam, 0 });
        }

        long ans = 0L;
        while (!q.isEmpty() && K > 0) {
            long[] polled = q.poll();
            int cur = (int) polled[0];
            long acdist = polled[1];
            if (acdist > 0) {
                K--;
                ans += acdist;
            }
            if (!visited.contains(cur + 1)) {
                visited.add(cur + 1);
                q.add(new long[] { cur + 1, acdist + 1 });
            }
            if (!visited.contains(cur - 1)) {
                visited.add(cur - 1);
                q.add(new long[] { cur - 1, acdist + 1 });
            }
        }
        System.out.println(ans);
    }

}