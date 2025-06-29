import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tgt = Integer.parseInt(br.readLine());
        int ans = -1;
        for (int i = 1; i <= 9; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                sb.append(j);
            }
            int result = Integer.parseInt(sb.toString());
            if (tgt == result) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}