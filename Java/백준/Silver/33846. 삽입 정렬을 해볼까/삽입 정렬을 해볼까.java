
import java.io.*;
import java.util.*;

public class Main {
    static int n, t;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
            a[j] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a, 0, t);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(a[i]).append(" ");
        }
        System.out.println(sb);
    }

}