
import java.io.*;
import java.util.*;

public class Main {
    static int N, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (v[j] > v[j + 1]) {
                    list.add(new int[] { j + 1, j + 2 });
                    count++;
                    int tmp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(count).append("\n");
        list.stream().forEach(e -> sb.append(e[0]).append(" ").append(e[1]).append("\n"));
        System.out.println(sb);
    }

}