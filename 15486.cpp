#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    int N;
    cin >> N ;

    vector<pair<int,int>> info(N+1);
    vector<long long> dp(N+2, 0); // dp[i] : i일전까지 얻을 수 있는 최대 이익

    for(int i=1; i<=N; i++){
        cin >> info[i].first >> info[i].second; // T, P
    }

    for(int i=1; i<=N+1; i++){
        int T = info[i].first, P = info[i].second;
        // dp[i] : i일전 까지 얻을 수 있는 최대 이익
        dp[i] = max(dp[i-1], dp[i]);
        if( i + T <= N+1 )
            dp[i+T] = max(dp[i+T], dp[i] + P );
    }
    cout << dp[N+1];

    return 0;
}