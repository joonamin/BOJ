
import java.io.*;
import java.util.*;

public class Main {
    static int N, A[];
    static boolean[][] isPalind;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        isPalind = new boolean[N][N];
        calcIsPalind();

        int i = 0, j = 1, ans = 0;
        while (i < N && j < N) {
            if (isPalind[i][j]) {
                ans++;
                i = j + 1;
            } else {
                j += 2;
            }
        }
        if (i < N) {
            ans = -1;
        }
        System.out.println(ans);
    }

    private static void calcIsPalind() {
        for (int i = 0; i < N; i++) {
            if (i + 1 < N && A[i] == A[i + 1]) {
                isPalind[i][i + 1] = true;
            }
            isPalind[i][i] = true;
        }
        for (int sz = 3; sz <= N; sz++) {
            for (int i = 0; i <= N - sz; i++) {
                int j = i + sz - 1;
                if (isPalind[i + 1][j - 1] && A[i] == A[j]) {
                    isPalind[i][j] = true;
                }
            }
        }
    }

}