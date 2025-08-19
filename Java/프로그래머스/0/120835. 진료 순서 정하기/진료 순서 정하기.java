import java.util.*;
class Solution {
    private int getRank(int[] v, int tgt) {
        int l = 0, r = v.length - 1;
        int tgtIdx = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (v[m] == tgt) {
                tgtIdx = m;
                break;
            }
            if (v[m] < tgt) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return v.length - tgtIdx;
    }
    public int[] solution(int[] emergency) {
        int[] temp = Arrays.copyOf(emergency, emergency.length);
        Arrays.sort(temp);
        
        int[] result = new int[emergency.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRank(temp, emergency[i]);
        }
        return result;
    }
}