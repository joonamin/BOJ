#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N;
    cin >> N;

    int cost[1001][3] ;
    for(int i=1; i<=N; i++){
        cin >> cost[i][0] >> cost[i][1] >> cost[i][2] ;
    }

    int ans = 2e9;
    int dp[1001][3] = {0, };
    string cases[] = {"RG", "RB", "GR", "GB", "BR", "BG"};
    for(auto cc : cases){
        char first = cc[0], last = cc[1];
        int total = 0;

        if( first == 'R' ) total += cost[1][0];
        else if( first == 'G' ) total += cost[1][1];
        else total += cost[1][2];

        dp[2][0] = (first == 'R') ? 9999999 : cost[2][0];
        dp[2][1] = (first == 'G') ? 9999999 : cost[2][1];
        dp[2][2] = (first == 'B') ? 9999999 : cost[2][2];

        for(int i=3; i<=N-1; i++){
            dp[i][0] = min( dp[i-1][1] + cost[i][0], dp[i-1][2] + cost[i][0] );
            dp[i][1] = min( dp[i-1][0] + cost[i][1], dp[i-1][2] + cost[i][1] );
            dp[i][2] = min( dp[i-1][0] + cost[i][2], dp[i-1][1] + cost[i][2] );
        }

        if( last == 'R' ) total += min( dp[N-1][1] + cost[N][0] , dp[N-1][2] + cost[N][0] );
        else if( last == 'G' ) total += min( dp[N-1][0] + cost[N][1] , dp[N-1][2] + cost[N][1] );
        else if( last == 'B' ) total += min( dp[N-1][0] + cost[N][2] , dp[N-1][1] + cost[N][2] );
        ans = min(ans, total);
    }
    cout << ans;

    return 0;
}