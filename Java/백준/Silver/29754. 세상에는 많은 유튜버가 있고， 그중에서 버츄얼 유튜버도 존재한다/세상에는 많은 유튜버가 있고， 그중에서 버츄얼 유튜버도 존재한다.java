
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Map<String, Info>> weeks = new ArrayList<>();

    static class Info implements Comparable<Info> {
        public String name;
        public int accMinute;
        public List<Integer> days;

        public int compareTo(Info o) {
            return this.name.compareTo(o.name);
        }

        public Info() {
            days = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M / 7; i++) {
            weeks.add(new TreeMap<>());
        }

        Set<String> names = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            String from = st.nextToken();
            String to = st.nextToken();
            Map<String, Info> map = weeks.get((day - 1) / 7);
            names.add(name);
            Info info = map.computeIfAbsent(name, k -> new Info());
            info.name = name;
            info.accMinute += computeAccMinute(from, to);
            info.days.add(day);
        }
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            boolean flag = true;
            for (int i = 0; i < M / 7; i++) {
                if (weeks.get(i).containsKey(name)) {
                    if (weeks.get(i).get(name).days.size() < 5
                            || weeks.get(i).get(name).accMinute < 3600) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append(name).append("\n");
            }
        }
        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

    static int computeAccMinute(String from, String to) {
        int fromMinute = Integer.parseInt(from.substring(0, 2)) * 60 + Integer.parseInt(from.substring(3));
        int toMinute = Integer.parseInt(to.substring(0, 2)) * 60 + Integer.parseInt(to.substring(3));
        return toMinute - fromMinute;
    }
}