
import java.io.*;
import java.util.*;

public class Main {
    static int[] arr = new int[500001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                int x = Integer.parseInt(st.nextToken());
                arr[r++] = x;
            } else {
                int m = (l + r) / 2;
                // 1. [l, m) 까지의 합을 구한다
                // 2. [m, r) 까지의 합을 구한다.
                int lsum = 0, rsum = 0;
                for (int i = l; i < m; i++)
                    lsum += arr[i];
                for (int i = m; i < r; i++)
                    rsum += arr[i];
                if (lsum <= rsum) {
                    l = m;
                    sb.append(lsum);
                } else {
                    r = m;
                    sb.append(rsum);
                }
                sb.append("\n");
            }
        }
        for (int i = l; i < r; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

}