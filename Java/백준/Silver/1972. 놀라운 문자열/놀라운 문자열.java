
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();
        while (true) {
            line = br.readLine();
            if (line.equals("*")) {
                break;
            }
            if (solve(line)) {
                sb.append(line).append(" is surprising.\n");
            } else {
                sb.append(line).append(" is NOT surprising.\n");
            }
        }
        System.out.println(sb);
    }

    static boolean solve(String s) {
        for (int d = 0; d < s.length() - 1; d++) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i + d + 1 < s.length(); i++) {
                String tgt = String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + d + 1));
                if (set.contains(tgt))
                    return false;
                set.add(tgt);
            }
        }
        return true;
    }

}