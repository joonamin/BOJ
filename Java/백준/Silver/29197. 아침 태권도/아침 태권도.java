
import java.io.*;
import java.util.*;

public class Main {

    static class State {
        public Double slope;
        public int[] d;

        public State(int x, int y) {
            if (x == 0) {
                if (y < 0)
                    this.slope = Double.MIN_VALUE;
                else
                    this.slope = Double.MAX_VALUE;
            } else {
                this.slope = (double) y / x;
            }

            if (x > 0)
                x = 1;
            else if (x < 0)
                x = -1;
            if (y > 0)
                y = 1;
            else if (y < 0)
                y = -1;
            this.d = new int[] { x, y };
        }

        @Override
        public int hashCode() {
            return Objects.hash(slope, d[0], d[1]);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State)) {
                return false;
            }
            State obj = (State) o;
            return this.slope.equals(obj.slope) && this.d[0] == obj.d[0] && this.d[1] == obj.d[1];
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] p = new int[N][2];
        for (int i = 0; i < N; i++) {
            p[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Set<State> slope = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            State state = new State(p[i][0], p[i][1]);
            if (slope.contains(state))
                continue;
            slope.add(state);
            ans++;
        }
        System.out.println(ans);
    }

}