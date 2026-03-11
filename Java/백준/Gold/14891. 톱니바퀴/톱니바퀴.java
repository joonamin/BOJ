import java.io.*;
import java.util.*;

public class Main {
    static char[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = new char[4][];
        for (int i = 0; i < 4; i++) {
            v[i] = br.readLine().toCharArray();
        }
        
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken()) - 1; 
            int dir = Integer.parseInt(st.nextToken());
            
            int[] d = new int[4];
            d[target] = dir;
            
            for (int j = target - 1; j >= 0; j--) {
                if (v[j][2] != v[j + 1][6]) {
                    d[j] = -d[j + 1];
                } else {
                    break;
                }
            }
            
            for (int j = target + 1; j < 4; j++) {
                if (v[j - 1][2] != v[j][6]) {
                    d[j] = -d[j - 1];
                } else {
                    break;
                }
            }
            
            for (int j = 0; j < 4; j++) {
                if (d[j] != 0) {
                    rotate(j, d[j]);
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            ans += (1 << i) * (v[i][0] - '0');
        }
        System.out.println(ans);
    }

    private static void rotate(int tgt, int d) {
        char temp;
        if (d == 1) {
            temp = v[tgt][7];
            for (int i = 7; i >= 1; i--) {
                v[tgt][i] = v[tgt][i - 1];
            }
            v[tgt][0] = temp;
        } else {
            temp = v[tgt][0];
            for (int i = 0; i < 7; i++) {
                v[tgt][i] = v[tgt][i + 1];
            }
            v[tgt][7] = temp;
        }
    }
}
