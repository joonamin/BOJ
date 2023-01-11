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
    // highest <-> lowest => 17 이하
    int N;
    cin >> N;
    vector<int> v(N);
    for (auto &item : v)
        cin >> item;

    long long ans = 1e9;
    for (int h = 0; h <= 83; h++) {
        // min = h ~~ max = h + {1, 2, ..., 17}
        for (int m = h + 1; m <= h + 17; m++) {
            long long ret = 0L;
            for (int i = 0; i < N; i++) {
                if (v[i] < h) ret += (h - v[i]) * (h - v[i]);
                else if (v[i] > m) ret += (m - v[i]) * (m - v[i]);
            }
            ans = min(ans, ret);
        }
    }
    cout << ans;
    return 0;
}
