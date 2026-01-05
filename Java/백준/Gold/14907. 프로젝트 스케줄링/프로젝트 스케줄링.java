
import java.io.*;
import java.util.*;

public class Main {

    static int K, dist[], costs[], inDegree[];
    static List<List<Integer>> nexts;
    static Map<Character, Integer> idxMap;
    static String[] lines;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dist = new int[27];
        Arrays.fill(dist, -1);
        costs = new int[27];
        inDegree = new int[27];
        lines = new String[27];

        nexts = new ArrayList<>();
        for (int i = 0; i < 27; i++)
            nexts.add(new ArrayList<>());
        idxMap = new HashMap<>();

        K = 0;
        // K = 1 ~ N
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            String[] input = line.split(" ");
            idxMap.put(input[0].charAt(0), ++K);
            costs[K] = Integer.parseInt(input[1]);
            int m = 0;
            lines[K] = "";
            if (input.length >= 3) {
                m = input[2].length();
                lines[K] = input[2];
            }
            inDegree[K] += m;
        }
        for (int i = 1; i <= K; i++) {
            for (char ch : lines[i].toCharArray()) {
                nexts.get(idxMap.get(ch)).add(i);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 1; i <= K; i++) {
            if (inDegree[i] == 0) {
                dist[i] = costs[i];
                q.add(new int[] { costs[i], i });
            }
        }

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int acdist = polled[0], cur = polled[1];
            for (int next : nexts.get(cur)) {
                dist[next] = Math.max(dist[next], acdist + costs[next]);
                if (--inDegree[next] == 0) {
                    q.add(new int[] { dist[next], next });
                }
            }
        }
        int ans = -1;
        for (int i = 1; i <= K; i++) {
            ans = Math.max(ans, dist[i]);
        }
        System.out.println(ans);
    }

}