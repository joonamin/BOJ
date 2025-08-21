
import java.io.*;
import java.util.*;

public class Main {

    static Map<Character, List<String>> asso = new HashMap<>();

    private static void init() {
        asso.computeIfAbsent('c', ArrayList::new).add("=");
        asso.computeIfAbsent('c', ArrayList::new).add("-");
        asso.computeIfAbsent('d', ArrayList::new).add("z=");
        asso.computeIfAbsent('d', ArrayList::new).add("-");
        asso.computeIfAbsent('l', ArrayList::new).add("j");
        asso.computeIfAbsent('n', ArrayList::new).add("j");
        asso.computeIfAbsent('s', ArrayList::new).add("=");
        asso.computeIfAbsent('z', ArrayList::new).add("=");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();
        String s = br.readLine();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            List<String> list = asso.getOrDefault(s.charAt(i), List.of());
            for (int j = 0; j < list.size(); j++) {
                String tgt = list.get(j);
                if (i + 1 + tgt.length() <= s.length() && s.substring(i + 1, i + 1 + tgt.length()).equals(tgt)) {
                    i += tgt.length();
                    break;
                }
            }
            ans++;
        }
        System.out.println(ans);
    }

}