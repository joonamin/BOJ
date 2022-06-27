#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, dp[505], v[505];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    int k = 0;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        v[a] = b;
        k = max(k, a);
    }

    // dp[i] := i를 마지막으로 하는 LIS의 길이
    for (int i = 1; i <= k; i++) {
        if(v[i] == 0)
            continue;
        dp[i] = 1;
        for (int j = 1; j < i; j++) {
            if(v[j] < v[i])
                dp[i] = max(dp[j] + 1, dp[i]);
        }
    }

    cout << N - *max_element(dp + 1, dp + k + 1);

    return 0;
}
