
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] m;
    static int[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        m = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        v = new int[N][5];
        for (int i = 0; i < N; i++) {
            v[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int minCost = 0x3f3f3f3f;
        List<Integer> ansList = new ArrayList<>();

        for (int bit = 1; bit <= (1 << N); bit++) {
            int[] tgt = new int[4];
            int acCost = 0;
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if ((bit & (1 << i)) != 0) {
                    for (int j = 0; j < 4; j++) {
                        tgt[j] += v[i][j];
                    }
                    acCost += v[i][4];
                    tempList.add(i);
                }
            }
            boolean isSatisfy = true;
            for (int i = 0; i < 4; i++) {
                if (m[i] > tgt[i]) {
                    isSatisfy = false;
                }
            }
            if (isSatisfy) {
                if (acCost < minCost) {
                    minCost = acCost;
                    ansList = tempList;
                } else if (acCost == minCost) {
                    StringBuilder currentAnsSb = new StringBuilder();
                    for (int idx : ansList)
                        currentAnsSb.append(idx).append(" ");

                    StringBuilder newAnsSb = new StringBuilder();
                    for (int idx : tempList)
                        newAnsSb.append(idx).append(" ");

                    if (newAnsSb.toString().compareTo(currentAnsSb.toString()) < 0) {
                        ansList = tempList;
                    }
                }
            }
        }
        if (minCost == 0x3f3f3f3f) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(minCost).append("\n");
            ansList.stream().forEach(e -> sb.append(e + 1).append(" "));
            System.out.println(sb);
        }
    }

}