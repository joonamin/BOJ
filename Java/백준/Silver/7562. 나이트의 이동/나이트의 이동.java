
import java.io.*;
import java.util.*;

public class Main {
    static final int[][] dir = { { -2, 1 }, { -2, -1 }, { 2, -1 }, { 2, 1 }, { 1, -2 }, { 1, 2 }, { -1, -2 },
            { -1, 2 } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()), sc = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int dr = Integer.parseInt(st.nextToken()), dc = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[l][l];
            Queue<int[]> q = new ArrayDeque<>();
            visited[sr][sc] = true;
            q.add(new int[] { sr, sc });
            int ans = 0;
            iter: for (int t = 0; !q.isEmpty(); t++) {
                int len = q.size();
                for (int i = 0; i < len; i++) {
                    int[] polled = q.poll();
                    int r = polled[0], c = polled[1];
                    if (r == dr && c == dc) {
                        ans = t;
                        break iter;
                    }
                    for (int d = 0; d < dir.length; d++) {
                        int nr = r + dir[d][0], nc = c + dir[d][1];
                        if (nr < 0 || nc < 0 || nr >= l || nc >= l || visited[nr][nc])
                            continue;
                        visited[nr][nc] = true;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

}