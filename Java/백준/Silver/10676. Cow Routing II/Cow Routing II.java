import java.io.*;
import java.util.*;

public class Main {

    static int A, B, N, cost[], cities[][];

    private static boolean canArrive(int a, int b) {
        int[] ac = cities[a];
        int[] bc = cities[b];
        for (int i = 0; i < ac.length; i++) {
            int start = ac[i];
            if (start != A)
                continue;
            for (int j = i; j < ac.length; j++) {
                int mid = ac[j];
                for (int k = 0; k < bc.length; k++) {
                    if (bc[k] != mid)
                        continue;
                    for (int l = k; l < bc.length; l++) {
                        if (bc[l] != B)
                            continue;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        A = Integer.parseInt(temp[0]);
        B = Integer.parseInt(temp[1]);
        N = Integer.parseInt(temp[2]);

        cost = new int[N];
        cities = new int[N][];
        for (int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            cost[i] = Integer.parseInt(temp[0]);
            int sz = Integer.parseInt(temp[1]);
            cities[i] = new int[sz];

            temp = br.readLine().split(" ");
            for (int j = 0; j < sz; j++) {
                cities[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canArrive(i, j)) {
                    if (i != j)
                        ans = Math.min(ans, cost[i] + cost[j]);
                    else
                        ans = Math.min(ans, cost[i]);
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }
}