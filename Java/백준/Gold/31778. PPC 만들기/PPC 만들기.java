
import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static char[] chs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chs = br.readLine().toCharArray();

        int l = 0, r = N - 1;
        while (K > 0 && l < r) {
            while (l < r && chs[l] == 'P') {
                l++;
            }
            while (l < r && chs[r] == 'C') {
                r--;
            }
            if (l < r) {
                char temp = chs[l];
                chs[l] = chs[r];
                chs[r] = temp;
                K--;
                l++;
                r--;
            }
        }
        int acP = 0;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            if (chs[i] == 'C') {
                ans += (long) (acP) * (acP - 1) / 2L;
            } else if (chs[i] == 'P') {
                acP += 1;
            }
        }
        System.out.println(ans);
    }

}