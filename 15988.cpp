#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
const int MOD = 1e9 + 9;
int n, dp[1000005] = {0, };
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;

    dp[1] = 1, dp[2] = dp[1] + 1, dp[3] = dp[1] + dp[2] + 1;
    for(int i=4; i<=1000000; i++){
        dp[i] = dp[i-1] % MOD;
        dp[i] += dp[i-2] % MOD; dp[i] %= MOD;
        dp[i] += dp[i-3] % MOD; dp[i] %= MOD;
    }
    while(T--){
        cin >> n;
        cout << dp[n] << '\n';
    }
    return 0;
}

