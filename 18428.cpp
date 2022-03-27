#include <bits/stdc++.h>
using namespace std;
int N;
char board[7][7];
bool check(int r, int c){
    int nr = r, nc = c;
    // 상
    for(nr = r-1; nr >= 0 ; nr--){
        if( board[nr][c] == 'S' )
            return false;
        if( board[nr][c] == 'O' )
            break;
    }
    // 하
    for(nr = r+1; nr < N; nr++){
        if( board[nr][c] == 'S' )
            return false;
        if( board[nr][c] == 'O' )
            break;
    }
    // 좌
    for(nc = c-1; nc >= 0 ; nc--){
        if( board[r][nc] == 'S' )
            return false;
        if( board[r][nc] == 'O' )
            break;
    }
    // 우
    for(nc = c+1; nc < N ; nc++){
        if( board[r][nc] == 'S' )
            return false;
        if( board[r][nc] == 'O' )
            break;
    }
    return true;
}
void DFS(int k){
    if( k == 3 ){
        // 숨을 수 있는 경우인지 판단.
        bool canHide = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( board[i][j] == 'T' ){
                    bool chk = check(i, j);
                    if( !chk )
                        return;
                }
            }
        }
        cout << "YES";
        exit(0);
    }
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if( board[i][j] == 'X' ){
                board[i][j] = 'O';
                DFS(k+1);
                board[i][j] = 'X';
            }
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> board[i][j];
        }
    }

    // 장애물 3개 설치
    // 가능한 경우가 존재하는가?
    DFS(0);
    cout << "NO";
    return 0;
}
