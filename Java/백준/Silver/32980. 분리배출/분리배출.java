
import java.io.*;
import java.util.*;

public class Main {
    static int N, P, C, V, S, G, F, O;
    static long ans;
    static char[][] chsArr;

    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chsArr = new char[N][];
        for (int i = 0; i < N; i++) {
            char[] chs = br.readLine().toCharArray();
            chsArr[i] = chs;
        }
        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        O = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            char[] chs = chsArr[i];
            char startsWith = chs[0];
            boolean isAllSame = true;
            for (int j = 0; j < chs.length - 1; j++) {
                if (chs[j] != chs[j + 1]) {
                    isAllSame = false;
                    break;
                }
            }
            // 일반 쓰레기로 버려야함
            if (!isAllSame || startsWith == 'O') {
                ans += chs.length * O;
                continue;
            }
            int cost = -1;
            if (startsWith == 'P') {
                cost = P;
            } else if (startsWith == 'C') {
                cost = C;
            } else if (startsWith == 'V') {
                cost = V;
            } else if (startsWith == 'S') {
                cost = S;
            } else if (startsWith == 'G') {
                cost = G;
            } else if (startsWith == 'F') {
                cost = F;
            }
            cost = Math.min(cost, O);
            ans += cost * chs.length;
        }
        System.out.println(ans);
    }

}