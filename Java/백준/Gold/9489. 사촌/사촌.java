
import java.io.*;
import java.util.*;

public class Main {
    static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * 1. parent_idx
         * 2. [start, end)
         * end 를 확장 (연속된 원소가 아닐떄까지)
         * 이 때 [3, 4, 5, 8)
         * [3, 4, 5]를 parent_idx에 삽입, parent_idx++
         * v[parent_idx] = 3 -> start, end -> [8, 9, 15)
         */
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = -1;
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0)
                break;
            if (n == 1) {
                st = new StringTokenizer(br.readLine());
                sb.append(0).append("\n");
                continue;
            }
            int[] v = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                v[i] = Integer.parseInt(st.nextToken());
                if (v[i] == k) {
                    m = i;
                }
            }
            int[] parent = new int[n];
            parent[0] = -1;
            int pIdx = 0, start = 1, end = 1;

            while (pIdx < n && start < n) {
                // System.out.printf("start: %d, end: %d\n", start, end);
                while (end < n && (start == end || v[end - 1] + 1 == v[end]))
                    end++;
                // System.out.printf("end: %d\n", end);
                while (start < end)
                    parent[start++] = pIdx;
                pIdx++;
            }
            // for (int i = 0; i < n; i++) {
            // int temp = parent[i] == -1 ? -1 : v[parent[i]];
            // System.out.printf("parent[%d] = %d\n", v[i], temp);
            // }
            int ans = 0, pk = parent[m], gpk = (pk == -1) ? -1 : parent[pk];
            for (int i = 0; i < n; i++) {
                int pi = parent[i], gpi = (pi == -1) ? -1 : parent[pi];
                if (gpk == -1 || gpi == -1)
                    continue;
                if (pk != pi && gpk == gpi) {
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}