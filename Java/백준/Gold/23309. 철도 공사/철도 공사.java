import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    static class LinkedList {
        public int[] prev;
        public int[] next;

        public LinkedList() {
            prev = new int[1000001];
            next = new int[1000001];
        }

        public void init(int[] values) {
            int first = values[0];
            int last = first;

            for (int i = 1; i < values.length; i++) {
                int cur = values[i];
                next[last] = cur;
                prev[cur] = last;
                last = cur;
            }
            next[last] = first;
            prev[first] = last;
        }

        public int bn(int i, int j) {
            int tgtNext = next[i];
            
            next[i] = j;
            prev[j] = i;
            
            next[j] = tgtNext;
            prev[tgtNext] = j;
            
            return tgtNext;
        }

        public int bp(int i, int j) {
            int tgtPrev = prev[i];
            
            next[tgtPrev] = j;
            prev[j] = tgtPrev;
            
            next[j] = i;
            prev[i] = j;
            
            return tgtPrev;
        }

        public int cn(int i) {
            int tgtN = next[i];
            int tgtNN = next[tgtN];
            
            next[i] = tgtNN;
            prev[tgtNN] = i;
            
            return tgtN;
        }

        public int cp(int i) {
            int tgtP = prev[i];
            int tgtPP = prev[tgtP];
            
            next[tgtPP] = i;
            prev[i] = tgtPP;
            
            return tgtP;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] v = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        
        LinkedList list = new LinkedList();
        list.init(v);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b;
            
            switch (cmd) {
                case "BN":
                    b = Integer.parseInt(st.nextToken());
                    sb.append(list.bn(a, b)).append("\n");
                    break;
                case "BP":
                    b = Integer.parseInt(st.nextToken());
                    sb.append(list.bp(a, b)).append("\n");
                    break;
                case "CN":
                    sb.append(list.cn(a)).append("\n");
                    break;
                case "CP":
                    sb.append(list.cp(a)).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}
