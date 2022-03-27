#include <bits/stdc++.h>
using namespace std;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int C, N;
    cin >> C >> N;

    int cost[21], person[21];
    for(int i=0; i<N; i++)
        cin >> cost[i] >> person[i];

    int dp[100001] = {0,}; // i의 금액을 가지고 수용할 수 있는 최대 사람의 수

    for(int i=0; i<N; i++){
        for(int j=0; j<= 100000; j++){
            // 하나만 끼워넣는 식으로 연산해도 가능, 어차피 2개 넣는 경우나
            // (j+cost[i]) 에 한개를 끼워 넣는 경우나 같음
            if( j - cost[i] >= 0 )
                dp[j] = max(dp[j], dp[j-cost[i]] + person[i]);
        }
    }
    for(int i=0; i<=100000; i++){
        if( dp[i] >= C ){
            cout << i ;
            break;
        }
    }

    return 0;
}