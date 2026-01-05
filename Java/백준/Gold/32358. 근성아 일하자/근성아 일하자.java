
import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        TreeSet<Integer> treeSet = new TreeSet<>();
        int cur = 0;
        long ans = 0L;
        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 1) {
                treeSet.add(input[1]);
            } else {
                // treeset에서 cur와 가장 가까운 원소
                // cur보다 작거나 같은 원소, cur보다 큰 원소
                while (!treeSet.isEmpty()) {
                    Integer c1 = treeSet.headSet(cur, true).pollLast();
                    Integer c2 = treeSet.tailSet(cur, false).pollFirst();
                    int next;
                    // c1, c2 둘 중 하나는 not null
                    if (c2 == null || (c1 != null && Math.abs(cur - c2) >= Math.abs(cur - c1))) {
                        next = c1;
                        if (c2 != null) {
                            treeSet.add(c2);
                        }
                    } else {
                        next = c2;
                        if (c1 != null) {
                            treeSet.add(c1);
                        }
                    }
                    ans += Math.abs(cur - next);
                    cur = next;
                }
            }
        }
        System.out.println(ans);
    }

}