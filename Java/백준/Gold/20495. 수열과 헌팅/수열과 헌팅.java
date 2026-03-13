import java.io.*;
import java.util.*;

public class Main {
    static int[] mins, maxs;
    static int[] orgMins, orgMaxs;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        mins = new int[N];
        maxs = new int[N];
        orgMins = new int[N];
        orgMaxs = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            orgMins[i] = mins[i] = a - b;
            orgMaxs[i] = maxs[i] = a + b;
        }

        Arrays.sort(mins);
        Arrays.sort(maxs);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int minIdx = lowerBound(maxs, orgMins[i]) + 1;
            int maxIdx = upperBound(mins, orgMaxs[i]);

            sb.append(minIdx).append(" ").append(maxIdx).append("\n");
        }
        System.out.println(sb);
    }

    private static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private static int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}