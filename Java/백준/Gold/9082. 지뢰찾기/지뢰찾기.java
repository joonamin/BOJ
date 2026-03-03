
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String first = br.readLine();
            String second = br.readLine();

            int[] count = new int[first.length()];
            for (int i = 0; i < first.length(); i++) {
                count[i] = first.charAt(i) - '0';
            }
            int ans = 0;
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    if (count[0] > 0 && count[1] > 0) {
                        count[0]--;
                        count[1]--;
                        ans++;
                    }
                } else if (i == N - 1) {
                    if (count[N - 2] > 0 && count[N - 1] > 0) {
                        count[N - 2]--;
                        count[N - 1]--;
                        ans++;
                    }
                } else {
                    if (count[i - 1] > 0 && count[i] > 0 && count[i + 1] > 0) {
                        count[i - 1]--;
                        count[i]--;
                        count[i + 1]--;
                        ans++;
                    }
                }
            }
            // for (int i = 0; i < count.length; i++) {
            // System.out.printf("count[%d] = %d\n", i, count[i]);
            // }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}