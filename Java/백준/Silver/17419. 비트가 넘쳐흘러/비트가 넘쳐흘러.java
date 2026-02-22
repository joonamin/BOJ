
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[] chs = br.readLine().toCharArray();
        int ans = 0;
        for (char ch : chs) {
            if (ch == '1') {
                ans++;
            }
        }
        System.out.println(ans);
    }

}