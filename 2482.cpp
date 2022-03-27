#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int MOD = 1e9 + 3;
int cand[3][2] = {{0, 0}, {0, 1}, {1, 0}};
int dp[1005][1005][2] = {0, };
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int N, K, ans = 0;
    cin >> N >> K;

    for(const auto &[fc, lc] : cand){
        memset(dp, 0, sizeof(dp));
        // [0, N-1] => 0, [1, N-2], N-1
        dp[0][0][0] = !fc;
        dp[0][1][1] = fc;

        for(int i=1; i<N-1; i++){
            for(int j=0; j<=K; j++){
                dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                dp[i][j][1] = j-1 >= 0 ? dp[i-1][j-1][0] : 0;
            }
        }
        dp[N-1][K][0] = (dp[N-2][K][0] + dp[N-2][K][1]) % MOD;
        dp[N-1][K][1] = dp[N-2][K-1][0];

        ans += dp[N-1][K][lc];
        ans %= MOD;
    }
    cout << ans ;
    return 0;
}
