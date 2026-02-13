import java.io.*;
import java.util.*;

public class Main {

    static int N, w[];
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        w = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        long maxWeight = Long.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            maxWeight = Math.max(maxWeight, w[i]);
        }

        if (maxWeight <= 0) {
            System.out.println(maxWeight);
            return;
        }

        calcDepth(1, 0);

        long ans = 0;
        for (int a = 0; a < 21; a++) {
            for (int b = a; b < 21; b++) {
                long temp = 0;
                for (int i = 0; i < N; i++) {
                    int[] node = list.get(i);
                    int depth = node[0];
                    int weight = node[1];

                    if (depth < a || depth > b) {
                        continue;
                    }
                    temp = Math.max(temp + weight, 0L);
                    ans = Math.max(ans, temp);
                }
            }
        }
        System.out.println(ans);
    }

    private static void calcDepth(int node, int depth) {
        if (node * 2 <= N) {
            calcDepth(node * 2, depth + 1);
        }
        list.add(new int[] { depth, w[node] });
        if (node * 2 + 1 <= N) {
            calcDepth(node * 2 + 1, depth + 1);
        }
    }
}