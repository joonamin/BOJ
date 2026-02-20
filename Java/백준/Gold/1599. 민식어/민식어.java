
import java.io.*;
import java.util.*;

public class Main {

    private static final String data = "a b k d e g h i l m n ng o p r s t u w y";
    private static final Map<String, Integer> orderMap = new HashMap<>();

    static int N;
    static String[][] strs;

    public static void main(String[] args) throws Exception {
        // System.setOut(new PrintStream(new FileOutputStream("./output.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = data.split(" ");
        for (int i = 0, order = 0; i < temp.length; i++) {
            orderMap.put(temp[i], ++order);
        }
        N = Integer.parseInt(br.readLine());
        strs = new String[N][];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            List<String> strList = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'n' && j + 1 < s.length() && s.charAt(j + 1) == 'g') {
                    strList.add(String.valueOf(s.charAt(j)) + String.valueOf(s.charAt(j + 1)));
                    j++;
                } else {
                    strList.add(String.valueOf(s.charAt(j)));
                }
            }
            strs[i] = strList.toArray(new String[strList.size()]);
        }
        Arrays.sort(strs, (String[] a, String[] b) -> {
            final int len = Math.min(a.length, b.length);
            for (int i = 0; i < len; i++) {
                if (!a[i].equals(b[i])) {
                    return Integer.compare(orderMap.get(a[i]), orderMap.get(b[i]));
                }
            }
            // prefix가 같은 경우 길이가 짧은 것이 우선순위
            if (a.length != b.length) {
                return Integer.compare(a.length, b.length);
            }
            return 0;
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length; j++) {
                if (strs[i][j] != null)
                    sb.append(strs[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}