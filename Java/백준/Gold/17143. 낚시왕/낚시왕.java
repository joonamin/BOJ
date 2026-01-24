import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static Shark[][] board;
    static int ans = 0;

    static int[][] dir = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board[r][c] = new Shark(r, c, s, d, z);
        }

        for (int fisher = 1; fisher <= C; fisher++) {
            catchShark(fisher);
            moveSharks();
        }

        System.out.println(ans);
    }

    private static void catchShark(int col) {
        for (int r = 1; r <= R; r++) {
            if (board[r][col] != null) {
                ans += board[r][col].z;
                board[r][col] = null;
                return;
            }
        }
    }

    private static void moveSharks() {
        Shark[][] nextMap = new Shark[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                Shark shark = board[r][c];
                if (shark == null)
                    continue;

                Shark nextShark = getNextShark(shark);

                if (nextMap[nextShark.r][nextShark.c] != null) {
                    if (nextMap[nextShark.r][nextShark.c].z < nextShark.z) {
                        nextMap[nextShark.r][nextShark.c] = nextShark;
                    }
                } else {
                    nextMap[nextShark.r][nextShark.c] = nextShark;
                }
            }
        }
        board = nextMap;
    }

    private static Shark getNextShark(Shark shark) {
        int r = shark.r;
        int c = shark.c;
        int s = shark.s;
        int d = shark.d;

        if (d == 1 || d == 2) {
            s %= (R - 1) * 2;
        } else {
            s %= (C - 1) * 2;
        }

        for (int i = 0; i < s; i++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];

            if (nr < 1 || nr > R || nc < 1 || nc > C) {
                d = reverseDir(d);
                nr = r + dir[d][0];
                nc = c + dir[d][1];
            }
            r = nr;
            c = nc;
        }

        return new Shark(r, c, shark.s, d, shark.z);
    }

    private static int reverseDir(int d) {
        if (d == 1)
            return 2;
        if (d == 2)
            return 1;
        if (d == 3)
            return 4;
        if (d == 4)
            return 3;
        return 0;
    }
}
