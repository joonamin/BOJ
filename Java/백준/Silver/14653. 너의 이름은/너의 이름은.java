
import java.io.*;
import java.util.*;

public class Main {

    static int N, K, Q;
    static Message[] messages;

    static class Message {
        public int rest;
        public char p;

        public Message(int rest, char p) {
            this.rest = rest;
            this.p = p;
        }

        @Override
        public String toString() {
            return String.format("rest: %d, p: %c\n", this.rest, this.p);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        K = input[1];
        Q = input[2];

        messages = new Message[K + 1];
        for (int i = 1; i <= K; i++) {
            String[] line = br.readLine().split(" ");
            int rest = Integer.parseInt(line[0]);
            char p = line[1].charAt(0);
            messages[i] = new Message(rest, p);
        }

        Set<Character> except = new HashSet<>();
        except.add('A');
        for (int i = Q; i <= K; i++) {
            char tgt = messages[i].p;
            except.add(tgt);
        }

        for (int i = 1; i < Q; i++) {
            if (messages[i].rest == messages[Q].rest) {
                except.add(messages[i].p);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char p = (char) ('A' + i);
            if (!except.contains(p)) {
                sb.append(p).append(" ");
            }
        }

        if (except.size() == 0 || messages[Q].rest == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

}