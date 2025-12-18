
import java.io.*;
import java.util.*;

public class Main {

    static final String f1 = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
    static final String f2 = "\"재귀함수가 뭔가요?\"";
    static final String f31 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
    static final String f32 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
    static final String f33 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
    static final String f4 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
    static final String f5 = "라고 답변하였지.";

    static StringBuilder sb = new StringBuilder();

    static String getPrefix(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth * 4; i++) {
            res.append("_");
        }
        return res.toString();
    }

    static void recursive(int rest, int depth) {
        if (rest == 0) {
            sb.append(getPrefix(depth)).append(f2).append("\n");
            sb.append(getPrefix(depth)).append(f4).append("\n");
            sb.append(getPrefix(depth)).append(f5).append("\n");
            return;
        }
        sb.append(getPrefix(depth)).append(f2).append("\n");
        sb.append(getPrefix(depth)).append(f31).append("\n");
        sb.append(getPrefix(depth)).append(f32).append("\n");
        sb.append(getPrefix(depth)).append(f33).append("\n");
        recursive(rest - 1, depth + 1);
        sb.append(getPrefix(depth)).append(f5).append("\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb.append(f1).append("\n");
        recursive(N, 0);
        System.out.println(sb);
    }

}