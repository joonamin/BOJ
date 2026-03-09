
import java.io.*;
import java.util.*;

public class Main {

    static char[] chs;
    static int[] acsum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chs = br.readLine().toCharArray();
        int acsum = 0, ans = 0, left = 0, right = 0;

        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                left++;
                acsum++;
            } else if (chs[i] == ')') {
                right++;
                acsum--;
            }

            if (acsum <= 1) {
                left = 0;
            }
            if (acsum == -1) {
                ans = right;
                break;
            }
        }
        if (acsum == 2) {
            ans = left;
        }
        System.out.println(ans);
    }

}