#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>

using namespace std;


int main(void) {
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int N, D;
    cin >> N >> D;

    vector<int> dp(D + 1);
    generate(dp.begin(), dp.end(), [n = 0]() mutable { return n++; });

    vector<tuple<int, int, int>> v;
    for (int i = 0; i < N; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        v.emplace_back(a, b, c);
    }

    for (int i = 0; i <= D; i++) {
        dp[i] = min(dp[i], dp[i - 1] + 1);
        for (int j = 0; j < N; j++) {
            const auto &tp = v[j];
            if (get<1>(tp) == i) {
                dp[i] = min(dp[i], dp[get<0>(tp)] + get<2>(tp));
            }
        }
    }

    cout << dp[D] << '\n';
    return 0;
}
