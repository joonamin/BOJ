
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S = null;
        while ((S = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(S);
            char[] arr = st.nextToken().toCharArray();
            char[] initArr = Arrays.copyOf(arr, arr.length);
            int k = Integer.parseInt(st.nextToken());
            int initK = k;

            boolean flag = true;
            while (--k > 0) {
                if (!nextPermutation(arr)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append(String.format("%s %d = %s\n", String.valueOf(initArr), initK, String.valueOf(arr)));
            } else {
                sb.append(String.format("%s %d = %s\n", String.valueOf(initArr), initK, "No permutation"));
            }
        }
        System.out.println(sb);
    }

    private static boolean nextPermutation(char[] arr) {
        int i = arr.length - 1, j = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i <= 0) {
            return false;
        }
        while (j >= 0 && arr[j] <= arr[i - 1])
            j--;

        char tmp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = tmp;

        int start = i, end = arr.length - 1;
        while (start <= end) {
            tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
        return true;
    }

}