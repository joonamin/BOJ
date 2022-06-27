#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int n, k;
    cin >> n >> k;

    vector<int> val(n);
    for(auto &elem : val) {
        cin >> elem;
    }
    sort(val.begin(), val.end());
    val.erase( unique(val.begin(), val.end()), val.end());

    int dp[105][10005], cnt = 0;
    fill(&dp[0][0], &dp[104][10004], 1e9);
    while (val[0] * cnt <= k) {
        dp[0][val[0] * cnt] = cnt;
        cnt++;
    }
    n = val.size();
    for (int i = 1; i < n; i++) {
        // 가치 j에 대해
        for (int j = 0; j <= k; j++) {
            cnt = 0;
            while(j - val[i] * cnt >= 0) {
                dp[i][j] = min(dp[i][j], dp[i - 1][j - val[i] * cnt] + cnt);
                cnt++;
            }
            // 또는 현재 동전만으로 구성
            cnt = 0;
            while (val[i] * cnt <= k) {
                dp[i][val[i] * cnt] = min(dp[i][val[i] * cnt], cnt);
                cnt++;
            }
        }
    }
    if (dp[n - 1][k] == 1e9) {
        dp[n - 1][k] = -1;
    }
    cout << dp[n-1][k] << '\n';

    return 0;
}
