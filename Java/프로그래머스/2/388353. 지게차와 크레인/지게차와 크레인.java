import java.util.*;
class Solution {
    private char[][] board;
    private final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private void run(String cmd) {
        if (cmd.length() == 1) {
            boolean[][] visited = new boolean[board.length][board[0].length];
            Queue<int[]> q = new ArrayDeque<>();
            visited[0][0] = true;
            q.add(new int[] {0, 0});
            while (!q.isEmpty()) {
                int r = q.peek()[0], c = q.peek()[1];
                q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if (nr < 0 || nc < 0 || nr >= board.length 
                        || nc >= board[0].length || visited[nr][nc] ||
                        board[nr][nc] != '\0' && board[nr][nc] != cmd.charAt(0))
                        continue;
                    
                    if (board[nr][nc] == cmd.charAt(0)) {
                        visited[nr][nc] = true;
                        board[nr][nc] = '\0';
                    } else if (board[nr][nc] == '\0') {
                        visited[nr][nc] = true;
                        q.add(new int[] {nr, nc});
                    }
                }
            }
        } else if (cmd.length() == 2) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == cmd.charAt(0)) {
                        board[i][j] = '\0';
                    }
                }
            }
        } 
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        board = new char[storage.length + 2][];
        board[0] = new char[storage[0].length() + 2];
        board[storage.length + 1] = new char[storage[0].length() + 2];
        for (int i = 1; i <= storage.length; i++) {
            char[] arr = storage[i - 1].toCharArray();
            board[i] = new char[arr.length + 2];
            for (int j = 1; j <= arr.length; j++) {
                board[i][j] = arr[j - 1];
            }
        }
        
        Arrays.stream(requests).forEach(this::run);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '\0') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}