
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Set<String> extentions = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<CustomFile> files = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), ".");
            CustomFile file = new CustomFile(st.nextToken(), st.nextToken());
            files.add(file);
        }
        for (int i = 0; i < M; i++) {
            String extention = br.readLine();
            extentions.add(extention);
        }
        Collections.sort(files);
        StringBuilder sb = new StringBuilder();
        files.stream().forEach(e -> sb.append(e.name).append(".").append(e.extention).append("\n"));
        System.out.println(sb);
    }

    static class CustomFile implements Comparable<CustomFile> {
        public String name, extention;

        public CustomFile(String name, String extention) {
            this.name = name;
            this.extention = extention;
        }

        @Override
        public int compareTo(CustomFile file) {
            if (!this.name.equals(file.name)) {
                return this.name.compareTo(file.name);
            }
            
            boolean thisRecognized = extentions.contains(this.extention);
            boolean otherRecognized = extentions.contains(file.extention);
            
            if (thisRecognized == otherRecognized) {
                return this.extention.compareTo(file.extention);
            }
            
            return thisRecognized ? -1 : 1;
        }
    }
}