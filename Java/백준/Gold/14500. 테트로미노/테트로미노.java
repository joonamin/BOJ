
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, board[][];
    static final int[][][] shapes = {
            {
                    { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }
            },
            {
                    { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }
            },
            {
                    { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 }
            },
            {
                    { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 }
            },
            {
                    { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 }
            }
    };

    static class Info {
        public int h, w, shape[][];

        public Info(int h, int w, int[][] shape) {
            this.h = h;
            this.w = w;
            this.shape = shape;
        }

        public Info() {
        }

        public Info(int[][] shape) {
            int minY = 0x3f3f3f3f, maxY = -0x3f3f3f3f;
            int minX = 0x3f3f3f3f, maxX = -0x3f3f3f3f;
            for (int[] p : shape) {
                minY = Math.min(minY, p[0]);
                maxY = Math.max(maxY, p[0]);
                minX = Math.min(minX, p[1]);
                maxX = Math.max(maxX, p[1]);
            }
            this.h = maxY - minY + 1;
            this.w = maxX - minX + 1;
            this.shape = shape;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < shapes.length; k++) {
                    Info info = new Info(shapes[k]);
                    // 회전하거나
                    for (int d = 0; d < 4; d++) {
                        rotate90(info);
                        // 좌우 대칭하거나
                        for (int lr = 0; lr < 2; lr++) {
                            reverseLR(info);
                            // 상하 대칭하거나
                            for (int ud = 0; ud < 2; ud++) {
                                reverseUD(info);
                                int score = getScore(i, j, info);
                                ans = Math.max(ans, score);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static void rotate90(Info info) {
        int[][] newShape = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int r = info.shape[i][0];
            int c = info.shape[i][1];
            newShape[i][0] = c;
            newShape[i][1] = info.h - 1 - r;
        }
        int temp = info.h;
        info.h = info.w;
        info.w = temp;
        info.shape = newShape;
    }

    private static void reverseLR(Info info) {
        int[][] newShape = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int r = info.shape[i][0];
            int c = info.shape[i][1];
            newShape[i][0] = r;
            newShape[i][1] = info.w - 1 - c;
        }
        info.shape = newShape;
    }

    private static void reverseUD(Info info) {
        int[][] newShape = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int r = info.shape[i][0];
            int c = info.shape[i][1];
            newShape[i][0] = info.h - 1 - r;
            newShape[i][1] = c;
        }
        info.shape = newShape;
    }

    private static int getScore(int sr, int sc, Info info) {
        // 둘 수 없는 칸이 하나라도 있다면 -INF 반환
        int result = 0;
        for (int[] p : info.shape) {
            int nr = sr + p[0], nc = sc + p[1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                return -0x3f3f3f3f;
            result += board[nr][nc];
        }

        return result;
    }

}