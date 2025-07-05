import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        if (s1.length() != s2.length()) {
            System.out.println("NO");
        }

        boolean flag = true;
        int len = s1.length();

        int[] ch1 = new int[26];
        int[] ch2 = new int[26];
        for (int i = 0; i < len; i++) {
            ch1[s1.charAt(i) - 'A']++;
            ch2[s2.charAt(i) - 'A']++;
        }

        boolean[] checked1 = new boolean[26];
        boolean[] checked2 = new boolean[26];
        for (int i = 0; i < len; i++) {
            if (checked1[s1.charAt(i) - 'A'])
                continue;
            checked1[s1.charAt(i) - 'A'] = true;

            boolean isMatched = false;
            char c1 = s1.charAt(i);
            for (int j = 0; j < len; j++) {
                char c2 = s2.charAt(j);
                if (!checked2[c2 - 'A'] && ch1[c1 - 'A'] == ch2[c2 - 'A']) {
                    isMatched = true;
                    checked2[s2.charAt(j) - 'A'] = true;
                    break;
                }
            }
            if (!isMatched) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}