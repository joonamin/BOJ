#define MOD 1000000000LL
#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N, K;
    cin >> N >> K;
 
    long long dp[201][201] = {0,};
    for(int i=0; i<=N; i++)
        dp[1][i] = 1;

    for(int i=2; i<=K; i++){
        for(int j=0; j<=N; j++){
            for(int k=0; k<=N; k++) {
                if( j - k >= 0 ) {
                    dp[i][j] += (dp[i - 1][j - k]) % MOD;
                    dp[i][j] %= MOD;
                }
            }
        }
    }
    cout << dp[K][N] % MOD ;

    return 0;
}