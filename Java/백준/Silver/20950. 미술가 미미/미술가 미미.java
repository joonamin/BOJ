import java.io.*;
import java.util.*;

public class Main {
    static int N, tgt[];
    static int[][] v;
    static boolean[] isSelected;
    static List<Integer> selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N][3];
        isSelected = new boolean[N];
        selected = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                v[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tgt = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            tgt[i] = Integer.parseInt(st.nextToken());
        }

        int ans = select(0, 0);
        System.out.println(ans);
    }

    private static int select(int depth, int offset) {
        int result = calc();

        if (depth == 7 || depth == N) {
            return result;
        }

        for (int i = offset; i < N; i++) {
            if (isSelected[i]) {
                continue;
            }
            isSelected[i] = true;
            selected.add(i);
            result = Math.min(result, select(depth + 1, i + 1));
            selected.remove(selected.size() - 1);
            isSelected[i] = false;
        }
        return result;
    }

    private static int calc() {
        int[] current = new int[3];
        final int k = selected.size();
        if (k <= 1) {
            return 0x3f3f3f3f;
        }
        for (int i = 0; i < k; i++) {
            int vidx = selected.get(i);
            for (int j = 0; j < 3; j++) {
                current[j] += v[vidx][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            current[i] /= k;
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            result += Math.abs(tgt[i] - current[i]);
        }

        return result;
    }
}