
import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static class Problem implements Comparable<Problem> {
        public int num, difficulty;
        public char extract;

        public Problem(int num, int difficulty, String title) {
            this.num = num;
            this.difficulty = difficulty;
            this.extract = Character.toUpperCase(title.toCharArray()[difficulty - 1]);
        }

        @Override
        public int compareTo(Problem problem) {
            return Integer.compare(this.num, problem.num);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            String title = line[0];
            int num = Integer.parseInt(line[1]);
            int difficulty = Integer.parseInt(line[2]);
            problems.add(new Problem(num, difficulty, title));
        }
        Collections.sort(problems);
        StringBuilder sb = new StringBuilder();
        problems.stream().forEach(e -> sb.append(e.extract));
        System.out.println(sb);
    }

}