#include <bits/stdc++.h>
using namespace std;
enum{ HORIZONAL = 0, VERTICAL = 1, CROSS = 2 };
int board[17][17];
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int n;
    cin >> n;

    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++)
            cin >> board[i][j];
    }
    // base case
    int dp[3][17][17] ={0, };
    for(int i=1; i<n; i++){
        if( board[0][i] == 1 ) break;
        dp[HORIZONAL][0][i] = 1;
    }

    for(int i=1; i<n; i++){
        for(int j=1; j<n; j++){
            if( board[i][j] == 1 )
                continue;
            // 가로로 현재 위치에 도착할 수 있는 경우
            dp[HORIZONAL][i][j] = dp[HORIZONAL][i][j-1] + dp[CROSS][i][j-1];
            // 세로로 현재 위치에 도착할 수 있는 경우
            dp[VERTICAL][i][j] = dp[VERTICAL][i-1][j] + dp[CROSS][i-1][j];
            // 대각선 모양으로 현재 위치에 도착할 수 있는 경우
            if( board[i-1][j] != 1 && board[i][j-1] != 1 )
                dp[CROSS][i][j] = dp[CROSS][i-1][j-1] + dp[HORIZONAL][i-1][j-1] + dp[VERTICAL][i-1][j-1] ;
        }
    }

    int answer = 0;
    for(int i=0; i<3; i++)
        answer += dp[i][n-1][n-1];
    cout << answer;

    return 0;
}