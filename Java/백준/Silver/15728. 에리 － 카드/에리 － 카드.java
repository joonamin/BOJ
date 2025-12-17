
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0], K = input[1];
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(b);

        int ans = Integer.MIN_VALUE;
        List<int[]> cand = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cand.add(new int[] { a[i] * b[j], j });
            }
        }
        Collections.sort(cand, (p1, p2) -> -Integer.compare(p1[0], p2[0]));
        boolean[] isSelected = new boolean[N];
        int i = 0, k = 0;
        while (i < cand.size() && k < K) {
            int idx = cand.get(i)[1];
            if (isSelected[idx]) {
                i++;
                continue;
            }
            isSelected[idx] = true;
            k++;
        }
        for (int j = 0; j < cand.size(); j++) {
            if (isSelected[cand.get(j)[1]])
                continue;
            ans = Math.max(ans, cand.get(j)[0]);
        }
        System.out.println(ans);
    }

}