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
    int T;
    cin >> T;

    while(T--){
        int K;
        cin >> K;
        vector<long long> v(K+1), prefix(K+1, 0);
        for(int i=1; i<=K; i++) {
            cin >> v[i];
            prefix[i] = prefix[i-1] + v[i];
        }

        vector<vector<long long>> dp(K+1, vector<long long>(K+1, 1e8));
        for(int i=1; i<=K; i++){
            dp[i][i] = 0;
        }

        for (int sz=2; sz<=K; sz++) {
            for (int i = 1; i + sz - 1 <= K; i++) {
                int j = i + sz - 1;
                for (int k=i; k<j; k++) {
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j] + prefix[j] - prefix[i-1]);
                }
            }
        }

        cout << dp[1][K] << '\n';
    }

    return 0;
}
