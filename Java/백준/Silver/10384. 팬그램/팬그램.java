
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            char[] chs = br.readLine().toCharArray();
            sb.append(String.format("Case %d: %s\n", i, solve(chs)));
        }
        System.out.println(sb);
    }

    private static String solve(char[] chs) {
        int[] count = new int[26];
        for (char ch : chs) {
            if (('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
                count[Character.toLowerCase(ch) - 'a']++;
            }
        }
        int min = 0x3f3f3f3f;
        for (int i = 0; i < 26; i++) {
            min = Math.min(min, count[i]);
        }
        if (min >= 3) {
            return "Triple pangram!!!";
        } else if (min == 2) {
            return "Double pangram!!";
        } else if (min == 1) {
            return "Pangram!";
        } else {
            return "Not a pangram";
        }
    }

}