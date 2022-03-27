#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int n, m, dp[1001][1001];
char board[1001][1001];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> n >> m;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++) {
            cin >> board[i][j];
            if(board[i][j] == '1')
                dp[i][j] = 1;
        }
    }

    // dp[i][j] : (i, j)를 정사각형의 right-bottom 으로 잡는 가장 큰 정사각형의 한변의 길이
    for(int i=1; i<n; i++){
        for(int j=1; j<m; j++){
            if(board[i][j] == '0' || dp[i-1][j-1] == 0) continue;
            int dx = 0, dy = 0;
            while(j-dx >= 0 && board[i][j-dx] == '1') dx++;
            while(i-dy >= 0 && board[i-dy][j] == '1') dy++;
            dx--, dy--;
            int sz = min(min(dy, dx), dp[i-1][j-1]);
            dp[i][j] = sz + 1;
        }
    }

    int ans = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++)
            ans = max(ans, dp[i][j]);
    }
    cout << ans * ans;
    return 0;
}
