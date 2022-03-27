#include <bits/stdc++.h>
using namespace std;
int R, C, dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
char board[505][505];
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr), cout.tie(nullptr);

    cin >> R >> C;
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            cin >> board[i][j];
            if(board[i][j] == '.')
                board[i][j] = 'D';
        }
    }

    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++){
            if(board[i][j] != 'S') continue;

            for(int d=0; d<4; d++){
                int nr = i + dir[d][0], nc = j + dir[d][1];
                if(0 <= nr && nr < R && 0 <= nc && nc < C && board[nr][nc] == 'W'){
                    cout << 0 << '\n';
                    exit(0);
                }
            }
        }
    }
    cout << 1 << '\n';
    for(int i=0; i<R; i++){
        for(int j=0; j<C; j++)
            cout << board[i][j];
        cout << '\n';
    }

    return 0;
}