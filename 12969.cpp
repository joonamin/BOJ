#include <bits/stdc++.h>
using namespace std;
int N, K;
bool dp[31][31][31][450] = {false,};
char ans[31];
bool solve(int n, int a, int b, int p){
    if( n == N ){
        if( p == K ){
            cout << ans;
            exit(0);
            return true;
        }
        else
            return false;
    }

    if( dp[n][a][b][p] ){
        // 이미 이전에 이러한 쌍을 시도했지만, 정답이 나오지 못한 경우이므로
        // 추가 탐색을 진행하지 않고 종료한다.
        return false;
    }
    dp[n][a][b][p] = true;

    ans[n] = 'A';
    solve(n+1, a+1, b, p);
    ans[n] = 'B';
    solve(n+1, a, b+1, p+a);
    ans[n] = 'C';
    solve(n+1, a, b, p+a+b);

    return false;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> K;
    solve(0, 0, 0, 0);

    cout << -1;
    return 0;
}
