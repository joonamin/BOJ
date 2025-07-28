
import java.io.*;
import java.util.*;

public class Main {

    private static class Pair {
        public int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int[] intArr() {
            return new int[] { y, x };
        }
    }

    private static boolean check(int[] p1, int[] p2, int[] p3) {
        return (p2[0] - p1[0]) * (p3[1] - p1[1]) == (p3[0] - p1[0]) * (p2[1] - p1[1]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Pair> map = new HashMap<>();
        char[][] board = new char[N][];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            board[i] = line;
            for (int j = 0; j < N; j++) {
                if (line[j] != '.')
                    map.put(line[j], new Pair(i, j));
            }
        }
        Character[] keys = map.keySet().toArray(new Character[map.size()]);
        int ans = 0;
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                for (int k = j + 1; k < keys.length; k++) {
                    if (check(map.get(keys[i]).intArr(), map.get(keys[j]).intArr(), map.get(keys[k]).intArr())) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }

}