
import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    static class Team implements Comparable<Team> {
        public String university, name;
        public int solved, penalty;

        public Team(String university, String name, int solved, int penalty) {
            this.university = university;
            this.name = name;
            this.solved = solved;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Team t) {
            if (this.solved == t.solved) {
                return Integer.compare(this.penalty, t.penalty);
            }
            return -Integer.compare(this.solved, t.solved);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] temp = br.readLine().split(" ");
            Team team = new Team(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
            teams.add(team);
        }
        Collections.sort(teams);
        StringBuilder sb = new StringBuilder();
        Set<String> participant = new HashSet<>();
        for (int i = 0; i < N && participant.size() < K; i++) {
            if (participant.contains(teams.get(i).university))
                continue;
            sb.append(teams.get(i).name).append("\n");
            participant.add(teams.get(i).university);
        }
        System.out.println(sb);
    }

}