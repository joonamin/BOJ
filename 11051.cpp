#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;



int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int N, K, dp[1005][1005] = {0,};
    const int MOD = 10007;
    cin >> N >> K;

    // nCr = n-1Cr + n-1Cr-1
    for(int i=0; i<=N; i++){
        dp[i][0] = dp[i][i] = 1;
    }
    for(int i=1; i<=N; i++){
        for(int j=1; j<i; j++){
            dp[i][j] += dp[i-1][j-1];
            if(i-1 >= j)
                dp[i][j] += dp[i-1][j];
            dp[i][j] %= MOD;
        }
    }
    cout << dp[N][K];
    return 0;
}
