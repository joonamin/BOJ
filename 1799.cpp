#include <bits/stdc++.h>
using namespace std;
int N, board[11][11], maxVal[2] ={0, 0};
bool placed[11][11];
bool canSet(int r, int c){
    // 2방향 탐색
    int nr = r, nc = c;
    while( nr >= 0 && nc >= 0 ){
        // left up
        if( placed[nr][nc] )
            return false;
        nr -= 1;
        nc -= 1;
    }
    nr = r, nc = c;
    while( nr >= 0 && nc < N ){
        // right up
        if( placed[nr][nc] )
            return false;
        nr -= 1;
        nc += 1;
    }
    return true;
}
void DFS(int r, int c, const int stIdx){
    if( r == N ){
        // 폰의 개수를 카운팅한다
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( placed[i][j] )
                    cnt += 1;
            }
        }
        maxVal[stIdx] = max(maxVal[stIdx], cnt);
        return ;
    }

    int nr = r, nc = c+2;
    if( nc >= N ){
        nr += 1;
        if( stIdx == 0 && (nr & 1) )
            nc = 1;
        else if( stIdx == 0 )
            nc = 0;
        if( stIdx == 1 && (nr & 1) )
            nc = 0;
        else if( stIdx == 1 )
            nc = 1;
    }

    // 현재 위치에 폰을 둘 수 있는가?
    if( board[r][c] == 1 && canSet(r,c) ){
        placed[r][c] = true;
        DFS(nr, nc, stIdx);
        placed[r][c] = false;
    }
    DFS(nr, nc, stIdx);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin >> board[i][j];
        }
    }

    DFS(0, 0, 0); // 짝수 번째 row -> 짝수 인덱스 부터 시작, 홀수 번째 row -> 홀수 인덱스
    for(auto& item : placed){
        fill(item, item+11, false);
    }

    DFS(0, 1, 1); // 짝수 번째 row -> 홀수 인덱스 부터 시작,

    cout << maxVal[0] + maxVal[1];

    return 0;
}