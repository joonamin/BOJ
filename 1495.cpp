#include <bits/stdc++.h>
using namespace std;
bool dp[101][1001] = {false, };
vector<int> V;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    // dp[i][j] : i번째 stage까지 고려했을 때, j볼륨으로 연주할 수 있는가?
    int N, S, M;
    cin >> N >> S >> M;

    V.resize(N+1);
    for(int i=1; i<=N; i++){
        cin >> V[i];
    }

    dp[0][S] = true;

    for(int i=1; i<=N; i++){
        for(int j=0; j<=M; j++){
            if( dp[i-1][j] == true ){
                if( 0 <= j + V[i] && j + V[i] <= M )
                    dp[i][j+V[i]] = true;
                if( 0 <= j - V[i] && j - V[i] <= M )
                    dp[i][j-V[i]] = true;
            }
        }
    }
    int answer = -1;
    for(int i=0; i<=M; i++){
        if( dp[N][i] == true )
            answer = i;
    }
    cout << answer;

    return 0;
}
