import java.io.*;
import java.util.*;

public class Main {

    static long N;
    static char[] chs;
    static int K;
    static int[][] pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        chs = br.readLine().toCharArray();
        K = Integer.parseInt(br.readLine());
        
        long L = chs.length;
        
        int[] counts = new int[26];
        for (int i = 0; i < L; i++) {
            counts[chs[i] - 'A']++;
        }
        
        pos = new int[26][];
        for (int i = 0; i < 26; i++) {
            pos[i] = new int[counts[i]];
        }
        
        int[] ptr = new int[26];
        for (int i = 0; i < L; i++) {
            int c = chs[i] - 'A';
            pos[c][ptr[c]++] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long r = Long.parseLong(st.nextToken());
            char ch = st.nextToken().charAt(0);
            int target = ch - 'A';
            
            long cycles = r / L;
            int rem = (int)(r % L);
            
            long ans = cycles * counts[target];
            
            if (rem > 0) {
                long modR = r % (2 * L);
                int startIdx = (int) ((modR * (modR - 1) / 2) % L);
                int endIdx = startIdx + rem - 1;
                
                if (endIdx < L) {
                    ans += getCount(target, startIdx, endIdx);
                } else {
                    ans += getCount(target, startIdx, (int)L - 1);
                    ans += getCount(target, 0, endIdx - (int)L);
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static int getCount(int c, int start, int end) {
        if (pos[c].length == 0) return 0;
        int leftIdx = lowerBound(pos[c], start);
        int rightIdx = upperBound(pos[c], end);
        return rightIdx - leftIdx;
    }

    static int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    static int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}