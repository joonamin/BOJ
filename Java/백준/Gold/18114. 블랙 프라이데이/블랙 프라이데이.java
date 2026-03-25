
import java.io.*;
import java.util.*;

public class Main {

    static int N, C, v[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N + N(N-1)/2 + [N(N-1)(N-2)/6] 선택된 2개의 인덱스를 제외한 임의의 k가 존재하는지 결정
        // i, j 가 이미 선택됐을 때 j+1이상의 인덱스 중에서 c - a[i] - a[j] 를 만족하는 원소가 있는지 확인
        // set[i] := i, i+1, ... n까지 포함된 원소의 set
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        v = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(v);

        for (int i = 0; i < N; i++) {
            if (v[i] == C) {
                System.out.println(1);
                return;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (v[i] + v[j] == C) {
                    System.out.println(1);
                    return;
                }
            }
        }

        for (int i = 0; i < N - 2; i++) {
            int tgt = C - v[i];
            int l = i + 1, r = N - 1;
            // 서로다른 2개를 선택하게끔
            while (l < r) {
                int sum = v[l] + v[r];
                if (sum == tgt) {
                    System.out.println(1);
                    return;
                } else if (sum < tgt) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        System.out.println(0);
    }

}