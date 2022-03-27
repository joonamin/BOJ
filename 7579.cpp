#include <bits/stdc++.h>
using namespace std;
int dp[101][10001];
vector<int> mem, cost;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N, M;
    cin >> N >> M;

    mem.resize(N), cost.resize(N);
    for(int i=0; i<N; i++)
        cin >> mem[i];
    for(int i=0; i<N; i++)
        cin >> cost[i];


    // dp[i][j] : i번째 앱 까지 고려했을 때, j의 비용으로 만들 수 있는 메모리의 최대 값
    for(int i=cost[0]; i<=10000; i++){
        dp[0][i] = mem[0];
    }

    for(int i=1; i<N; i++){
        int mi = mem[i], ci = cost[i];
        for(int j=0; j<=10000; j++){
            // cost 가 양수이므로, 무조건 가능하다면 선택하는 것이 이득이다.
            if( j - ci >= 0 ){
                dp[i][j] = max( dp[i][j], dp[i-1][j-ci] + mi );
            }
            dp[i][j] = max( dp[i][j], dp[i-1][j] );
        }
    }

    for(int i=0; i<=10000; i++){
        if( dp[N-1][i] >= M ){
            cout << i ;
            break;
        }
    }



    return 0;
}
