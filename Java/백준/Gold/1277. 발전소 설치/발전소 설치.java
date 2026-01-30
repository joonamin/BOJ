
import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static double M;
    static int[][] pos;
    static boolean[][] isConnected;
    static double[] dist;

    static class Item implements Comparable<Item> {
        public int curNum;
        public double acDist;

        @Override
        public int compareTo(Item o) {
            if (Double.compare(this.acDist, o.acDist) == 0) {
                return Integer.compare(this.curNum, o.curNum);
            }
            return Double.compare(this.acDist, o.acDist);
        }

        public Item(int curNum, double acDist) {
            this.curNum = curNum;
            this.acDist = acDist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        pos = new int[N + 1][2];
        isConnected = new boolean[N + 1][N + 1];
        M = Double.parseDouble(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos[i + 1][0] = x;
            pos[i + 1][1] = y;
        }
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            isConnected[u][v] = isConnected[v][u] = true;
        }
        dist = new double[N + 1];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Item> pq = new PriorityQueue<>();
        pq.add(new Item(1, 0));
        while (!pq.isEmpty()) {
            Item it = pq.poll();
            if (dist[it.curNum] < it.acDist)
                continue;
            for (int i = 1; i <= N; i++) {
                double cost = isConnected[it.curNum][i] ? 0 : getCost(it.curNum, i);
                if (cost > M || dist[i] <= dist[it.curNum] + cost) {
                    continue;
                }
                dist[i] = dist[it.curNum] + cost;
                pq.add(new Item(i, dist[i]));
            }
        }
        System.out.println((int) (dist[N] * 1000));
    }

    private static double getCost(int fromNum, int toNum) {
        long xDiffSquare = (long) (pos[fromNum][0] - pos[toNum][0]) * (pos[fromNum][0] - pos[toNum][0]);
        long yDiffSquare = (long) (pos[fromNum][1] - pos[toNum][1]) * (pos[fromNum][1] - pos[toNum][1]);
        return Math.sqrt(xDiffSquare + yDiffSquare);
    }
}