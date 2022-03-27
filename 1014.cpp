#include <bits/stdc++.h>
using namespace std;
int C, N, M;
char board[11][11];
bool check(int n){
    // 현재 n이 유효한 형태로 앉아있는가?
    for(int i=0; i<M; i++){
        if( n & (1<<i) ){
            // i번째 비트를 기준으로 오른쪽 비트 검사
            if( i > 0 && n & (1<<(i-1)) )
                return false;
            // i번째 비트를 기준으로 왼쪽 비트 검사
            if( i < M-1 && n & (1<<(i+1)) )
                return false;
        }
    }
    return true;
}
bool check(int up, int down){
    // 윗 행이 up, 아랫 행이 down 일 때
    // 이런 방식으로 앉을 수 있는가?
    // up, down 각각은 앉을 수 있는 상태임이 보장
    for(int i=0; i<M; i++){
        if( down & (1<<i) ){
            // 오른쪽 위 검사
            if( i > 0 &&  (up & 1<<(i-1)) )
                return false;
            if( i < M-1 && (up & 1 << (i+1)) )
                return false;
        }
    }
    return true;
}
bool canHaveSeat(int row, int n){
    string current = board[row];
    for(int i=0; i<M; i++){
        if( n & (1<<i) && current[M-1-i] == 'x' )
            return false;
    }
    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> C;
    while(C--){
        cin >> N >> M;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++)
                cin >> board[i][j];
        }
        int dp[10][1<<11] = {0, }; // i번째 줄에 ex: 1011 처럼 앉을 때, 배치할 수 있는 최대 학생의 수
        for(int j=0; j<(1<<M); j++){
            // j 형태로 앉을 수 있는가?
            if( check(j) && canHaveSeat(0, j) )
                dp[0][j] = __builtin_popcount(j);
        }

        for(int i=1; i<N; i++){
            for(int j=0; j<(1<<M); j++){
                // j형태로 앉을 수 있는가?, 이전 행에서 앉을 수 있는 사람의 최댓값 + 현재 값
                if( !check(j) || !canHaveSeat(i, j) ) continue;
                for(int prev = 0; prev<(1<<M); prev++){
                    if( !check(prev) || !check(prev, j) || !canHaveSeat(i, j) ) continue;
                    dp[i][j] = max(dp[i][j], dp[i-1][prev] + __builtin_popcount(j));
                }
            }
        }

        int ans = 0;
        for(int i=0; i<(1<<M); i++)
            ans = max(ans, dp[N-1][i]);
        cout << ans << '\n';
    }

    return 0;
}