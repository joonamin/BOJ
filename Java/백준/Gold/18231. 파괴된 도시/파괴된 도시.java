
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] isDestroyed;
    static List<List<Integer>> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        for (int i = 0; i < N + 1; i++)
            adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = input[0], v = input[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int K = Integer.parseInt(br.readLine());
        isDestroyed = new boolean[N + 1];
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < input.length; i++) {
            isDestroyed[input[i]] = true;
        }

        Set<Integer> candidates = new HashSet<>();
        for (int cand : input) {
            boolean isCand = true;
            for (int next : adj.get(cand)) {
                if (!isDestroyed[next]) {
                    isCand = false;
                    break;
                }
            }
            if (isCand) {
                candidates.add(cand);
            }
        }

        if (check(candidates)) {
            StringBuilder sb = new StringBuilder();
            sb.append(candidates.size()).append("\n");
            candidates.stream().forEach(e -> sb.append(e).append(" "));
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean check(Set<Integer> candidates) {
        boolean[] temp = new boolean[N + 1];
        for (int candidate : candidates) {
            temp[candidate] = true;
            for (int next : adj.get(candidate)) {
                temp[next] = true;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (isDestroyed[i] != temp[i]) {
                return false;
            }
        }
        return true;
    }

}
