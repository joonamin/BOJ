#include <bits/stdc++.h>
using namespace std;
vector<int> v;
int cost[5][5] ={
    //   0  1  2  3  4
        {1, 2, 2, 2, 2},
        {3, 1, 3, 4, 3},
        {3, 3, 1, 3, 4},
        {3, 4, 3, 1, 3},
        {3, 3, 4, 3, 1}
};
int dp[100001][5][5] ;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);


    while(true){
        int in;
        cin >> in;
        if( in == 0 )
            break;
        v.push_back(in);
    }

    // dp[st][i][j] : st번째까지 지시 사항을 만족했을 때, (i,j) 위치에 서있는 최소 힘.
    for(int i=0; i<100001; i++){
        for(int j=0; j<5; j++)
            fill(dp[i][j], dp[i][j] + 5, 1e9);
    }

    dp[0][0][0] = 0;
    for(int st = 1; st <= v.size(); st++){
        int current = v[st-1];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                dp[st][i][current] = min(dp[st][i][current], dp[st-1][i][j] + cost[j][current] );
            }
        }
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                dp[st][current][i] = min(dp[st][current][i], dp[st-1][i][j] + cost[j][current] );
            }
        }
    }

    int ans = 1e9;
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            if( ans > dp[v.size()][i][j] )
                ans = dp[v.size()][i][j];
        }
    }
    cout << ans ;

    return 0;
}