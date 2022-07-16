#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, board[1001][1001], dp[1001][1001];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> board[i][j];
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            dp[i][j] = board[i][j];
            int mx = 0;
            if (i - 1 >= 0)
                mx = max(mx, dp[i-1][j]);
            if (i - 1 >= 0 && j - 1 >= 0)
                mx = max(mx, dp[i-1][j-1]);
            if (j - 1 >= 0)
                mx = max(mx, dp[i][j-1]);
            dp[i][j] += mx;
        }
    }
    cout << dp[N-1][M-1];
    return 0;
}
