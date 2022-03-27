// O(N^2 * 2^(N^2))
#include <bits/stdc++.h>
using namespace std;
int N;
int board[5][5];
int ans = 1e9;
const int dir[4][4] = {{-1, 0}, {1,0}, {0, -1}, {0, 1} };
void push(int r, int c){
    if( r < 0 || c < 0 || r >= N || c >= N )
        return;
    int rr[4], cc[4];
    for(int i=0; i<4; i++){
        rr[i] = r + dir[i][0];
        cc[i] = c + dir[i][1];
    }
    board[r][c] = (board[r][c] == 1) ? 0 : 1 ;
    for(int i=0; i<4; i++){
        if( rr[i] < 0 || rr[i] >= N || cc[i] < 0 || cc[i] >= N )
            continue;
        board[rr[i]][cc[i]] = (board[rr[i]][cc[i]] == 1) ? 0 : 1;
    }
}
void DFS(int r, int c, int cnt){

    if( r == N && c == 0 ){
        // 모든 전구들을 검사했으므로 최소횟수를 갱신시켜준다.
        bool isAllZero = true;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( board[i][j] == 1 ) {
                    isAllZero = false;
                    break;
                }
            }
        }
        if( isAllZero )
            ans = min(ans, cnt);
        return;
    }
    int nr = r, nc = c+1;
    if( nc >= N ){
        nr += 1;
        nc = 0;
    }

    // 현재 전구의 스위치를 누르지 않고 다음 전구로 넘어간다.
    DFS(nr, nc, cnt);
    // 현재 전구의 스위치를 누르고 진행해본다.
    push(r,c);
    DFS(nr, nc, cnt+1);

    // 이 후 원 상태로 복구
    push(r,c);
}
int main(void){
    cin >> N ;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> board[i][j];
        }
    }
    DFS(0,0,0);

    if( ans == 1e9 )
        cout << -1;
    else
        cout << ans;

    return 0;
}