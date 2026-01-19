
import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static boolean[] isForbidden;
    static State[] dp;
    static final int P_STATE = 0, N_STATE = 1;

    private static class State {
        public int flag, counter;

        public State() {
            this.flag = -1;
            this.counter = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        isForbidden = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int m = Integer.parseInt(st.nextToken());
            isForbidden[m] = true;
        }
        dp = new State[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new State();
        }
        State result = dfs(0);
        System.out.println(result.counter);
    }

    private static State dfs(int cur) {
        if (dp[cur].flag != -1) {
            return dp[cur];
        }

        State result = new State();

        List<Integer> nlist = new ArrayList<>(); // next player win, winning state
        List<Integer> plist = new ArrayList<>(); // previous player win, losing state

        for (int i = 1; i <= k && cur + i <= n; i++) {
            if (isForbidden[cur + i])
                continue;
            State nstate = dfs(cur + i);
            if (nstate.flag == N_STATE) {
                nlist.add(cur + i);
            } else if (nstate.flag == P_STATE) {
                plist.add(cur + i);
            }
        }
        // 현재 학생이 이길 수 있는 경우, 앞으로 외칠 정수 개수의 최소값
        if (!plist.isEmpty()) {
            result.flag = N_STATE;
            int minState = -1;
            for (int state : plist) {
                int count = dp[state].counter;
                if (minState == -1 || dp[minState].counter > count) {
                    minState = state;
                }
            }
            result.counter = dp[minState].counter + 1;
        } else if (!nlist.isEmpty()) {
            result.flag = P_STATE;
            int maxState = -1;
            for (int state : nlist) {
                int count = dp[state].counter;
                if (maxState == -1 || dp[maxState].counter < count) {
                    maxState = state;
                }
            }
            result.counter = dp[maxState].counter + 1;
        } else {
            // 마지막 P_STATE에서는 counter에 포함하지 않음.
            result.flag = P_STATE;
            result.counter = 0;
        }
        return dp[cur] = result;
    }

}