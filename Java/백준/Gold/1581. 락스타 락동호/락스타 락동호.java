import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        int[] v = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        int ff = v[0], fs = v[1], sf = v[2], ss = v[3];

        int ans = 0;
        if (ff == 0 && fs == 0) {
            // 1. 빠른 시작 곡이 단 1개도 없는 경우
            // 무조건 느린 시작 곡 중에서 골라야 함.
            // SS 곡들을 모두 잇고, 마지막에 SF 곡이 있다면 하나 추가 가능.
            ans = ss + (sf > 0 ? 1 : 0);
        } else {
            // 2. 빠른 시작 곡이 1개 이상 있는 경우
            // 무조건 빠른 시작 곡 중에서 골라야 함.
            // FF 곡들을 처음에 모두 소진 가능.
            if (fs == 0) {
                // 느린 쪽으로 넘어갈 수 있는 방법이 없음.
                ans = ff;
            } else {
                // FS가 1개 이상이므로 SS 진영을 모두 포함할 수 있음.
                // FS와 SF 사이를 왕복하며 개수를 최대화.
                if (fs > sf) {
                    // FS가 더 많으면 SF를 모두 쓰고 FS를 하나 더 씀.
                    ans = ff + ss + 2 * sf + 1;
                } else {
                    // SF가 더 많거나 같으면 FS를 모두 쓰고 SF도 그만큼 씀.
                    ans = ff + ss + 2 * fs;
                }
            }
        }
        System.out.println(ans);
    }
}
