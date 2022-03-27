#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


typedef long long ll;
const ll MOD = 1000000009LL;
ll dp[1001][1001] = {0, };
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    dp[1][1] = dp[2][1] = dp[3][1] = 1;
    // dp[i][j] : i를 j개의 수를 이용하여 만드는 경우의 수
    for(int j=2; j<=1000; j++){
        for(int i=2; i<=1000; i++){
            dp[i][j] += dp[i-1][j-1], dp[i][j] %= MOD;
            if(i-2 >= 0) {
                dp[i][j] += dp[i-2][j-1];
                dp[i][j] %= MOD;
            }
            if(i-3 >= 0){
                dp[i][j] += dp[i-3][j-1];
                dp[i][j] %= MOD;
            }
        }
    }
    while(T--){
        ll n, m;
        cin >> n >> m;
        cout << dp[n][m] << '\n';
    }



    return 0;
}
