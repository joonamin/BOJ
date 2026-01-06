
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int[] ans = new int[2];

        for (int A = 1; A < 1000; A++) {
            for (int B = A; B < 1000; B++) {
                if (check(input, A, B)) {
                    ans[0] = A;
                    ans[1] = B;
                    System.out.printf("%d %d\n", ans[0], ans[1]);
                    return;
                }
            }
        }
    }

    private static boolean check(char[] tgt, int A, int B) {
        StringBuilder temp = new StringBuilder();
        int idx = 0;
        int cur = A;
        while (cur <= B) {
            temp.append(String.valueOf(cur));
            if (temp.length() > tgt.length)
                return false;
            for (int i = idx; i < temp.length(); i++) {
                if (tgt[i] != temp.charAt(i))
                    return false;
            }
            idx = temp.length();
            cur++;
        }
        return temp.length() == tgt.length;
    }

}