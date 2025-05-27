import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        int max = 0;
        String keyMax = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > max) {
                keyMax = key;
                max = value;
            } else if (value == max && key.compareTo(keyMax) > 0) {
                keyMax = key;
            }
        }
        System.out.println(keyMax + " " + max);
    }
}