
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static final int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

    private static class Fireball {
        public int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    private static List<List<List<Fireball>>> createBoard() {
        List<List<List<Fireball>>> board = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                board.get(i).add(new LinkedList<>());
            }
        }
        return board;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        List<List<List<Fireball>>> board = createBoard(), tempBoard = createBoard();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board.get(r).get(c).add(new Fireball(r, c, m, s, d));
        }

        while (K-- > 0) {
            Queue<Fireball> fireballs = initFireBallQueue(board);
            tempBoard = moveAllFireball(fireballs);
            board = processBoard(tempBoard);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += board.get(i).get(j).stream().mapToInt(e -> e.m).sum();
            }
        }
        System.out.println(ans);
    }

    private static List<List<List<Fireball>>> processBoard(List<List<List<Fireball>>> tempBoard) {
        List<List<List<Fireball>>> board = createBoard();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int bSize = tempBoard.get(i).get(j).size();
                if (bSize < 2) {
                    board.get(i).set(j, tempBoard.get(i).get(j));
                } else {
                    int newM = 0, newS = 0, newD[] = { 1, 3, 5, 7 };
                    boolean isAllOdd = true, isAllEven = true;
                    for (int k = 0; k < tempBoard.get(i).get(j).size(); k++) {
                        newM += tempBoard.get(i).get(j).get(k).m;
                        newS += tempBoard.get(i).get(j).get(k).s;
                        if (tempBoard.get(i).get(j).get(k).d % 2 != 0) {
                            isAllEven = false;
                        } else {
                            isAllOdd = false;
                        }
                    }
                    newM /= 5;
                    newS /= tempBoard.get(i).get(j).size();
                    if (isAllEven || isAllOdd) {
                        newD = new int[] { 0, 2, 4, 6 };
                    }
                    if (newM == 0)
                        continue;
                    List<Fireball> fireballs = new ArrayList<>();
                    for (int d : newD) {
                        fireballs.add(new Fireball(i, j, newM, newS, d));
                    }
                    board.get(i).set(j, fireballs);
                }
            }
        }
        return board;
    }

    private static List<List<List<Fireball>>> moveAllFireball(Queue<Fireball> fireballs) {
        List<List<List<Fireball>>> tempBoard = createBoard();
        while (!fireballs.isEmpty()) {
            Fireball fireball = fireballs.poll();
            int move = fireball.s % N;
            int nr = (fireball.r + move * dir[fireball.d][0] + N) % N;
            int nc = (fireball.c + move * dir[fireball.d][1] + N) % N;
            fireball.r = nr;
            fireball.c = nc;
            tempBoard.get(nr).get(nc).add(fireball);
        }

        return tempBoard;
    }

    private static Queue<Fireball> initFireBallQueue(List<List<List<Fireball>>> board) {
        Queue<Fireball> result = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board.get(i).get(j).forEach(e -> result.add(e));
            }
        }
        return result;
    }

}