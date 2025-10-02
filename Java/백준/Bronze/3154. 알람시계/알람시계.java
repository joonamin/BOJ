
import java.io.*;
import java.util.*;

public class Main {

    final static int[][] nPos = { { 3, 1 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 },
            { 2, 1 },
            { 2, 2 } };

    private static int getEffort(int a, int b) {
        return Math.abs(nPos[a][0] - nPos[b][0]) + Math.abs(nPos[a][1] - nPos[b][1]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(":");

        int h = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int ansLeft, ansRight, total = Integer.MAX_VALUE;
        ansLeft = ansRight = -1;

        for (int i = h; i < 100; i += 24) {
            for (int j = m; j < 100; j += 60) {
                int res = getEffort(i / 10, i % 10) + getEffort(i % 10, j / 10) + getEffort(j / 10, j % 10);
                if (res < total) {
                    total = res;
                    ansLeft = i;
                    ansRight = j;
                }
            }
        }

        System.out.print(String.format("%02d:%02d", ansLeft, ansRight));
    }

}