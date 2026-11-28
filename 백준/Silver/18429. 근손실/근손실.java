import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    static int N, K;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        v = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        int[] order = IntStream.rangeClosed(0, N - 1).toArray();
        int ans = 0;
        do {
            if (check(order)) ans++;
        } while (hasNextPermutation(order));
        System.out.println(ans);
    }

    private static boolean check(int[] arr) {
        final int THRESHOLD = 500;
        int cur = 500;
        for (int i = 0; i < N; i++) {
            cur = cur + v[arr[i]] - K;
            if (cur < THRESHOLD) return false;
        }
        return true;
    }

    private static boolean hasNextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i <= 0) return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }
        swap(arr, i - 1, j);

        j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
