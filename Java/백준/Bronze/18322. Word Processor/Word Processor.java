import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = br.readLine();

        String[] S = input.split(" ");
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for (i = 0; i < N; i = j) {
            int cnt = 0;
            j = i;
            while (j < N) {
                cnt += S[j].length();
                if (cnt > K)
                    break;
                j++;
            }
            StringBuilder temp = new StringBuilder();
            for (int k = i; k < j; k++) {
                temp.append(S[k]).append(" ");
            }
            sb.append(temp.toString().trim()).append("\n");
        }
        System.out.println(sb.toString());
    }
}