#include <bits/stdc++.h>
using namespace std;
int N, T;
vector<int> req_time, score;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> T;
    req_time.resize(N), score.resize(N);

    for(int i=0; i<N; i++){
        cin >> req_time[i] >> score[i];
    }

    /// dp[i][j] : i번째 작업까지 확인했을 때, j시간 내에 얻을 수 있는 최대 점수
    int dp[101][100001] = {0,};
    for(int i=req_time[0]; i<=100000; i++)
        dp[0][i] = score[0];

    for(int i=1; i<N; i++){
        int t = req_time[i], s = score[i];
        for(int j=1; j<=100000; j++){
            if( j - t >= 0 ){
                dp[i][j] = max(dp[i-1][j-t] + s, dp[i][j]);
            }
            dp[i][j] = max(dp[i][j], dp[i-1][j]);
        }
    }

    cout << dp[N-1][T];

    return 0;
}
