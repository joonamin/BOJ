import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] board;
    static final char[][][] template = {
            { { '#', '#', '#' }, { '#', '.', '#' }, { '#', '.', '#' }, { '#', '.', '#' }, { '#', '#', '#'
            } }, // 0
            { { '#' }, { '#' }, { '#' }, { '#' }, { '#' } }, // 1
            { { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#'
            } }, // 2
            { { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#'
            } }, // 3
            { { '#', '.', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '.', '.', '#'
            } }, // 4
            { { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#'
            } }, // 5
            { { '#', '#', '#' }, { '#', '.', '.' }, { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#'
            } }, // 6
            { { '#', '#', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#' }, { '.', '.', '#'
            } }, // 7
            { { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#'
            } }, // 8
            { { '#', '#', '#' }, { '#', '.', '#' }, { '#', '#', '#' }, { '.', '.', '#' }, { '#', '#', '#'
            } }, // 9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nStr = br.readLine();
        if (nStr == null || nStr.trim().isEmpty())
            return;

        N = Integer.parseInt(nStr.trim());
        int width = N / 5;
        board = new char[5][width];

        String line = br.readLine();
        if (line == null)
            return;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = line.charAt(i * width + j);
            }
        }

        int c = 0;
        StringBuilder sb = new StringBuilder();

        int[] checkOrder = { 0, 2, 3, 4, 5, 6, 7, 8, 9, 1 };

        while (c < width) {
            if (board[0][c] == '.') {
                c++;
                continue;
            }

            boolean found = false;
            for (int num : checkOrder) {
                int w = template[num][0].length;

                if (c + w > width)
                    continue;

                boolean isExactlySame = true;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < w; j++) {
                        if (board[i][c + j] != template[num][i][j]) {
                            isExactlySame = false;
                            break;
                        }
                    }
                    if (!isExactlySame)
                        break;
                }

                if (isExactlySame) {
                    sb.append(num);
                    c += w;
                    found = true;
                    break;
                }
            }

            if (!found) {
                c++;
            }
        }
        System.out.println(sb.toString());
    }
}