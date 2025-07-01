import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] shownCount = new int[N];
        for (int i = 0; i < N - 1; i++) {
            double scope = -0x3f3f3f3f;
            for (int j = i + 1; j < N; j++) {
                double sc = (double) (v[j] - v[i]) / (double) (j - i);
                if (scope < sc) {
                    scope = sc;
                    shownCount[i] += 1;
                    shownCount[j] += 1;
                }
            }
        }
        System.out.println(Arrays.stream(shownCount).max().getAsInt());
    }
}