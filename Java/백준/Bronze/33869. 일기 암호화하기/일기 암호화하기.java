
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String W = br.readLine();
        String s = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < W.length(); i++) {
            boolean flag = false;
            for (int j = 0; !flag && j < sb.length(); j++) {
                if (sb.charAt(j) == W.charAt(i))
                    flag = true;
            }
            if (!flag) {
                sb.append(W.charAt(i));
            }
        }

        Map<Character, Character> map = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch - 'A' < sb.length()) {
                map.put(ch, sb.charAt(ch - 'A'));
            } else {
                for (char last = 'A'; last <= 'Z'; last++) {
                    if (!map.containsValue(last)) {
                        map.put(ch, last);
                        break;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            answer.append(map.get(s.charAt(i)));
        }
        System.out.println(answer);
    }

}