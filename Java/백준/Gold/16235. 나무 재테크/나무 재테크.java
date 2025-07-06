
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static class Position {
        public int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object b) {
            if (b == null || !(b instanceof Position))
                return false;
            Position other = (Position) b;
            return this.r == other.r && this.c == other.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public String toString() {
            return "(" + this.r + ", " + this.c + ")\t ";
        }
    }

    static int N, M, K, A[][], current[][], deadTrees[][];
    static Map<Position, ArrayDeque<Integer>> map = new HashMap<>();
    static final int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };

    private static boolean isInbound(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= N;
    }

    private static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ArrayDeque<Integer> dq = map.computeIfAbsent(new Position(i, j), k -> new ArrayDeque<>());
                int sz = dq.size();
                for (int k = 0; k < sz; k++) {
                    int age = dq.poll();
                    if (current[i][j] >= age) {
                        dq.add(age + 1);
                        current[i][j] -= age;
                    } else {
                        deadTrees[i][j] += age / 2;
                    }
                }
            }
        }
    }

    private static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                current[i][j] += deadTrees[i][j];
                deadTrees[i][j] = 0;
            }
        }
    }

    private static void fall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ArrayDeque<Integer> dq = map.computeIfAbsent(new Position(i, j), k -> new ArrayDeque<>());
                int sz = dq.size();
                for (int k = 0; k < sz; k++) {
                    int age = dq.poll();
                    if (age % 5 == 0) {
                        for (int d = 0; d < dir.length; d++) {
                            int nr = i + dir[d][0], nc = j + dir[d][1];
                            if (isInbound(nr, nc)) {
                                ArrayDeque<Integer> tgt = map.computeIfAbsent(new Position(nr, nc),
                                        m -> new ArrayDeque<>());
                                tgt.addFirst(1);
                            }
                        }
                    }
                    dq.addLast(age);
                }
            }
        }
    }

    private static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                current[i][j] += A[i][j];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);
        A = new int[N + 2][N + 2];
        current = new int[N + 2][N + 2];
        deadTrees = new int[N + 2][N + 2];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(current[i], 5);
        }

        for (int i = 1; i <= N; i++) {
            temp = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(temp[j - 1]);
            }
        }

        for (int i = 0; i < M; i++) {
            temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);
            int z = Integer.parseInt(temp[2]);

            ArrayDeque<Integer> dq = map.computeIfAbsent(new Position(x, y), k -> new ArrayDeque<>());
            dq.add(z);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ArrayDeque<Integer> dq = map.computeIfAbsent(new Position(i, j), k -> new ArrayDeque<>());
                if (!dq.isEmpty()) {
                    List<Integer> list = dq.stream().collect(Collectors.toList());
                    dq.clear();
                    dq.addAll(list);
                }
            }
        }

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ArrayDeque<Integer> pq = map.getOrDefault(new Position(i, j), null);
                if (pq != null && !pq.isEmpty()) {
                    ans += pq.size();
                }
            }
        }

        System.out.println(ans);
    }

}