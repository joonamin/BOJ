import java.io.*;
import java.util.*;
import java.util.function.*;

public class Main {
    static Map<Character, Function<int[], int[]>> map = new HashMap<>();
    static int[] pos;

    static {
        map.put('A', p -> {
            int r = p[0], c = p[1];
            return new int[] { (r + 2) % 4, c };
        });
        map.put('B', p -> {
            int r = p[0], c = p[1];
            int offset = (r / 2 == 1) ? 2 : 0;
            return new int[] { (r + 1) % 2 + offset, (c + 1) % 2 };
        });
        map.put('C', p -> {
            int r = p[0], c = p[1];
            if (r == 0 || r == 3) {
                if (r == 0)
                    return new int[] { 3, (c + 1) % 2 };
                else
                    return new int[] { 0, (c + 1) % 2 };
            } else if (r == 1) {
                return new int[] { 2, (c + 1) % 2 };
            }
            return new int[] { 1, (c + 1) % 2 };
        });
        map.put('D', p -> {
            int r = p[0], c = p[1];
            if (r == 0 && c == 0) {
                return new int[] { 0, 1 };
            } else if (r == 3 && c == 1) {
                return new int[] { 3, 0 };
            }
            int d = (c == 0) ? -1 : 1;
            return new int[] { r + d, c };
        });
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        pos[0]--;
        pos[1]--;
        int K = Integer.parseInt(br.readLine());
        char[] cmd = br.readLine().toCharArray();
        for (char ch : cmd) {
            // System.out.println("pos: " + pos[0] + ", " + pos[1]);
            pos = map.get(ch).apply(pos);
        }
        System.out.println((pos[0] + 1) + " " + (pos[1] + 1));
    }
}