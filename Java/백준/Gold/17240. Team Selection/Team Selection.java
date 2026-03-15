
import java.io.*;
import java.util.*;

public class Main {

    static int N, score[][];
    static List<List<Integer>> cand = new ArrayList<>();
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        score = new int[N][5];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int j = 0; j < 5; j++) {
            cand.add(new ArrayList<>());
            List<int[]> info = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                info.add(new int[] { i, score[i][j] });
            }
            Collections.sort(info, (a, b) -> -Integer.compare(a[1], b[1]));
            for (int k = 0; k < 5; k++) {
                cand.get(j).add(info.get(k)[0]);
            }
        }
        isSelected = new boolean[N];
        System.out.println(dfs(0));
    }

    private static int dfs(int depth) {
        if (depth == 5)
            return 0;
        int result = 0, maxsub = 0;
        for (int idx : cand.get(depth)) {
            if (isSelected[idx])
                continue;
            isSelected[idx] = true;
            maxsub = Math.max(maxsub, score[idx][depth] + dfs(depth + 1));
            isSelected[idx] = false;
        }
        result = result + maxsub;
        return result;
    }
}