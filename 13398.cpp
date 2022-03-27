#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;


int arr[100001], dp[100001][2] = {0,};
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int n;
    cin >> n;

    for(int i=0; i<n; i++)
        cin >> arr[i];

    // dp[i][j] : i번째 원소를 마지막으로 하는 최대 원소합 && j만큼 지웠을 때
    dp[0][0] = arr[0], dp[0][1] = 0;
    for(int i=1; i<n; i++){
        dp[i][0] = max(dp[i-1][0] + arr[i], arr[i]);
        dp[i][1] = max(dp[i-1][0], dp[i-1][1] + arr[i]);
    }
    int ans = dp[0][0]; // 수는 하나 이상 선택되어야 함.
    for(int i=1; i<n; i++) {
        ans = max(ans, dp[i][1]);
        ans = max(ans, dp[i][0]);
    }
    cout << ans ;
    return 0;
}
