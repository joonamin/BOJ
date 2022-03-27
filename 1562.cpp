#define MOD 1000000000LL
#include <bits/stdc++.h>
using namespace std;
long long dp[101][10][(1<<10)]; // dp[i][j][k] : i길이의 계단수 중, j로 끝나는 계단수. k에는 이전 방문 기록들을 기록함.
long long solution(int N){
    for(int i=1; i<=9; i++){
        dp[1][i][1<<i] = 1; // start 기준(1개 카운트)
    }

    for(int i=2; i<=N; i++){
        for(int j=0; j<=9; j++){
            for(int k=1; k<=(1<<10)-1; k++) {
                // k : 이전에 방문 했던 것
                int new_visited = k | (1<<j);
                if (j - 1 >= 0) {
                    dp[i][j][new_visited] += dp[i-1][j-1][k];
                    dp[i][j][new_visited] %= MOD;
                }
                if (j + 1 <= 9) {
                    dp[i][j][new_visited] += dp[i-1][j+1][k];
                    dp[i][j][new_visited] %= MOD;
                }
            }
        }
    }

    long long ret  = 0;
    for(int j=0; j<=9; j++){
        ret += dp[N][j][(1<<10)-1] ;
        ret %= MOD;
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    // 0~9가 모두 등장하는 계단수의 개수를 파악하기 위해, 그 전까지 무엇을 방문했는가도 중요한 문제임
    int N;
    cin >> N ;
    cout << solution(N);


    return 0;
}
