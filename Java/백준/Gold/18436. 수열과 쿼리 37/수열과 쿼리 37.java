
import java.io.*;
import java.util.*;

public class Main {
    static ValueSet[] seg;
    static int N, M, A[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        seg = new ValueSet[4 * N];
        segInit(1, 1, N);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] p = new int[3];
            for (int j = 0; j < 3; j++) {
                p[j] = Integer.parseInt(st.nextToken());
            }
            QueryResult result = query(p);
            if (result.hasValue) {
                sb.append(result.value).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static ValueSet segInit(int node, int l, int r) {
        if (l == r) {
            ValueSet res = (A[l] % 2 == 0) ? new ValueSet(0, 1) : new ValueSet(1, 0);
            return seg[node] = res;
        }
        int m = (l + r) / 2;
        ValueSet leftResult = segInit(node * 2, l, m);
        ValueSet rightResult = segInit(node * 2 + 1, m + 1, r);
        return seg[node] = new ValueSet(leftResult.oddCount + rightResult.oddCount,
                leftResult.evenCount + rightResult.evenCount);
    }

    private static ValueSet query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) {
            return new ValueSet();
        }
        if (ql <= l && r <= qr) {
            return seg[node];
        }
        int m = (l + r) / 2;
        ValueSet leftResult = query(node * 2, l, m, ql, qr);
        ValueSet rightResult = query(node * 2 + 1, m + 1, r, ql, qr);
        return new ValueSet(leftResult.oddCount + rightResult.oddCount, leftResult.evenCount + rightResult.evenCount);
    }

    private static ValueSet mutate(int node, int l, int r, final int tgt, final int tgtValue) {
        if (r < tgt || tgt < l)
            return seg[node];
        if (l == r) {
            ValueSet res = (tgtValue % 2 == 0) ? new ValueSet(0, 1) : new ValueSet(1, 0);
            return seg[node] = res;
        }
        int m = (l + r) / 2;
        ValueSet leftResult = mutate(node * 2, l, m, tgt, tgtValue);
        ValueSet rightResult = mutate(node * 2 + 1, m + 1, r, tgt, tgtValue);
        return seg[node] = new ValueSet(leftResult.oddCount + rightResult.oddCount,
                leftResult.evenCount + rightResult.evenCount);
    }

    static class ValueSet {
        public int oddCount, evenCount;

        public ValueSet() {
            this.oddCount = this.evenCount = 0;
        }

        public ValueSet(int oddCount, int evenCount) {
            this.oddCount = oddCount;
            this.evenCount = evenCount;
        }
    }

    static class QueryResult {
        public boolean hasValue;
        public int value;

        public QueryResult(boolean hasValue) {
            this.hasValue = hasValue;
        }

        public QueryResult(boolean hasValue, int value) {
            this.hasValue = hasValue;
            this.value = value;
        }
    }

    private static QueryResult query(int[] param) {
        if (param[0] == 2 || param[0] == 3) {
            ValueSet res = query(1, 1, N, param[1], param[2]);
            int value = param[0] == 2 ? res.evenCount : res.oddCount;
            return new QueryResult(true, value);
        } else {
            mutate(1, 1, N, param[1], param[2]);
            return new QueryResult(false);
        }
    }
}