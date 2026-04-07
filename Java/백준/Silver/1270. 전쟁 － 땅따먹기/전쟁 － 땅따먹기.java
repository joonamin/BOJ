
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int total = Integer.parseInt(st.nextToken());
            long[] v = new long[total];
            for (int j = 0; j < total; j++) {
                v[j] = Long.parseLong(st.nextToken());
            }
            // 임의의 하나가 절반 이상의 크기를 가진다면, 절반을 초과하는 것은 이것 하나로 유일하다는 것이 자명
            Arrays.sort(v);
            int counter = 0;
            String result = "SYJKGW";
            for (int l = 0, r = 0; l <= r && r < total;) {
                if (v[l] == v[r]) {
                    if (++counter >= total / 2 + 1) {
                        result = String.valueOf(v[l]);
                        break;
                    }
                    r++;
                } else {
                    l = r;
                    counter = 0;
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

}