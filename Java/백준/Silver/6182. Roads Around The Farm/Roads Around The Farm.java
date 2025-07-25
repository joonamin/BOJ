import java.io.*;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map = new HashMap<>();

    private static int dfs(int n, final int k) {
        if (map.containsKey(n))
            return map.get(n);

        int l, r;
        if ((n + k) % 2 == 0) {
            l = (n + k) / 2;
            r = n - l;
            if (l >= 1 && l < n && r >= 1) {
                map.put(n, dfs(l, k) + dfs(r, k));
                return map.get(n);
            }
        }

        if ((n - k) % 2 == 0) {
            l = (n - k) / 2;
            r = n - l;
            if (l >= 1 && l < n && r >= 1) {
                map.put(n, dfs(l, k) + dfs(r, k));
                return map.get(n);
            }
        }

        return 1;
    }

    public static void main(String[] args) throws Exception {
        int N = readInt(), K = readInt();
        System.out.println(dfs(N, K));
    }

    private static int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

}