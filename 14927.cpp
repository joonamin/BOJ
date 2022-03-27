#include <bits/stdc++.h>
using namespace std;
int N;
vector<int> row, cpy_row;
const int dir[5][2] = {{-1,0}, {1,0}, {0,-1}, {0,1}, {0,0}};
int ans = 1e9 ;
void push(int r, int c){
    // (r,c) 을 기준으로 4방향 비트를 플립
    for(int d = 0; d < 5 ; d++){
        int nr = r + dir[d][0];
        int nc = c + dir[d][1];
        if( nr < 0 || nc < 0 || nr >= N || nc >= N )
            continue;

        row[nr] ^= ( 1 << (N-1-nc) );
    }
}
void DFS(int c, int cnt){
    if( c >= N ){
        // Greedy 하게 1행 ~ N-1행까지 진행
        cpy_row = row;
        int tmp = cnt;
        for(int r = 1; r < N; r++){
            for(int cc = 0; cc < N; cc++){
                if( row[r-1] & ( 1 << (N-1-cc) )  ) {
                    push(r, cc);
                    tmp += 1;
                }
            }
        }
        bool isAllZero = true;
        for(auto item : row){
            if( item != 0 )
                isAllZero = false;
        }

        if( isAllZero )
            ans = min(ans, tmp);

        row = cpy_row;
        return;
    }
    push(0, c);
    DFS(c+1, cnt+1);
    push(0, c);
    DFS(c+1, cnt);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    row.resize(N);
    for(int i=0; i<N; i++){
        int r = 0;
        for(int j=0; j<N; j++){
            int val;
            cin >> val;
            r |= ( val << (N-1-j) );
        }
        row[i] = r;
    }

    // 첫행만 완전탐색으로 모든 경우를 구하자
    DFS(0, 0);
    if( ans == 1e9 )
        cout << -1 ;
    else
        cout << ans ;

    return 0;
}