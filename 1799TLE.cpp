#define BISHOP 2
#include <bits/stdc++.h>
using namespace std;
int N, blank_space;
int board[11][11];
int answer;
int mark(int y, int x){
    int ret = 0;
    // (y,x) 에 비숍을 두었을 때, 마킹 후 그 수를 리턴
    int sy = y, sx = x;
    // 좌 상단 화살표
    for(; sy >= 0 && sx >= 0; sy--, sx--){
        if( board[sy][sx] == 1 ){
            board[sy][sx] = 0;
            ret += 1;
        }
    }
    // 우 상단 화살표
    sy = y, sx = x;
    for(; sy >= 0 && sx < N; sy--, sx++){
        if( board[sy][sx] == 1 ){
            board[sy][sx] = 0;
            ret += 1;
        }
    }
    // 좌 하단 방향
    sy = y, sx = x;
    for(; sy < N && sx >= 0; sy++, sx--){
        if( board[sy][sx] == 1 ){
            board[sy][sx] = 0;
            ret += 1;
        }
    }
    // 우 하단 방향
    sy = y, sx = x;
    for(; sy < N && sx < N; sy++, sx++){
        if( board[sy][sx] == 1 ){
            board[sy][sx] = 0;
            ret += 1;
        }
    }
    return ret;
}
void DFS(int filled_cnt){
    if( filled_cnt == blank_space ){
        int cnt = 0 ;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
               if( board[i][j] == BISHOP )
                   cnt += 1;
            }
        }
        answer = max(answer, cnt);
        return ;
    }
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if( board[i][j] == 1 ){
                int cpy_board[11][11] ;
                memcpy(cpy_board, board, sizeof(board));
                board[i][j] = BISHOP;
                int marked = mark(i,j);
                DFS(filled_cnt+marked+1);
                memcpy(board, cpy_board, sizeof(cpy_board));
            }
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N ;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> board[i][j];
            if(board[i][j] == 1 )
                blank_space += 1;
        }
    }
    DFS(0);
    cout << answer ;
    return 0;
}
