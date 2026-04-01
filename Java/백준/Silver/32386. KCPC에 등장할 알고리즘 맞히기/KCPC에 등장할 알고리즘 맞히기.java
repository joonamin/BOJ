import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            if (!st.hasMoreTokens()) continue;
            int k = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < k; j++) {
                if (st.hasMoreTokens()) {
                    String tag = st.nextToken();
                    map.put(tag, map.getOrDefault(tag, 0) + 1);
                }
            }
        }

        int maxCount = -1;
        String resultTag = null;
        boolean isTie = false;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int currentCount = entry.getValue();
            
            if (currentCount > maxCount) {
                maxCount = currentCount;
                resultTag = entry.getKey();
                isTie = false;
            } else if (currentCount == maxCount) {
                isTie = true;
            }
        }

        if (isTie || resultTag == null) {
            System.out.println("-1");
        } else {
            System.out.println(resultTag);
        }
    }
}