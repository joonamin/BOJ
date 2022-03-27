#include <bits/stdc++.h>
using namespace std;
int main(void){
    int N;
    cin >> N;

    bool areFriends[51][51] = {false, };
    for(int i=0; i<N; i++){
        string in;
        cin >> in;
        for(int j=0; j<in.size(); j++){
            if(in[j] == 'Y') areFriends[i][j] = true;
            else areFriends[i][j] = false;
        }
    }

    // 플로이드 와샬 이용
    // 각 정점간의 거리가 2이하인 모든 쌍을 모두 조사
    int dp[51][51] ;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            dp[i][j] = 1e9;
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if( areFriends[i][j] ){
                dp[i][j] = 1;
            }
            if( i == j )
                dp[i][j] = 0;
        }
    }

    for(int k=0; k<N; k++){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if( dp[i][j] > dp[i][k] + dp[k][j] )
                    dp[i][j] = dp[i][k] + dp[k][j] ;
            }
        }
    }
    int ans = 0;
    for(int i=0; i<N; i++){
        int cnt = 0;
        for(int j=0; j<N; j++){
            if( i == j ) continue;
            if( dp[i][j] <= 2 ) cnt += 1;
        }
        if( cnt > ans ) ans = cnt;
    }
    cout << ans;
    return 0;
}