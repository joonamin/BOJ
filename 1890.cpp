#include <bits/stdc++.h>
using namespace std;
int N;
int board[101][101];
long long dp[101][101] = {0, }; //  dp[i][j] : [i][j] 위치까지 도착할 수 있는 경우의 수
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> board[i][j];
    }

    // 가장 왼쪽 칸 (0,0) 부터 시작, 항상 오른쪽 이나 아래로만 이동할 수 있다.
    dp[0][0] = 1;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            // target 의 i행보다 작은 행들을 검사
            for(int k=i-1; k>=0; k--){
                if( board[k][j] == i - k )
                    dp[i][j] += dp[k][j];
            }
            // target 의 j열보다 작은 열들을 검사
            for(int k=j-1; k>=0; k--){
                if( board[i][k] == j - k )
                    dp[i][j] += dp[i][k];
            }
        }
    }
    cout << dp[N-1][N-1];
    return 0;
}