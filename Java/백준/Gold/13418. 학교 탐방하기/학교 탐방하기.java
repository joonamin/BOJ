
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int N, M;
    static List<Edge> edges = new ArrayList<>();
    static int[] parent, rank;

    static class Edge {
        public int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }
        PriorityQueue<Edge> minPq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.cost, b.cost);
        });
        PriorityQueue<Edge> maxPq = new PriorityQueue<>((a, b) -> {
            return -Integer.compare(a.cost, b.cost);
        });
        for (Edge edge : edges) {
            minPq.add(edge);
            maxPq.add(edge);
        }
        long res1 = solve(minPq);
        long res2 = solve(maxPq);
        // System.out.printf("res1: %d, res2: %d\n", res1, res2);
        System.out.println(res1 - res2);
    }

    private static long solve(PriorityQueue<Edge> pq) {
        parent = IntStream.rangeClosed(0, N + 1).toArray();
        rank = new int[N + 1];
        long res = 0;
        int counter = 0;
        while (!pq.isEmpty() && counter < N) {
            Edge edge = pq.poll();
            if (!union(edge.from, edge.to))
                continue;
            if (edge.cost == 0) {
                res++;
            }
            counter++;
        }
        return res * res;
    }

    private static int find(int u) {
        return (parent[u] == u) ? u : (parent[u] = find(parent[u]));
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v)
            return false;
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) {
            rank[v]++;
        }
        return true;
    }

}