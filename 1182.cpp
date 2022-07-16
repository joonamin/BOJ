#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, S, ans = 0;
vector<int> v;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> S;

    v.resize(N);
    for (auto &item : v) {
        cin >> item;
    }

    for (int i = 1; i < (1 << N); i++) {
        int sum = 0;
        for(int j = 0; j < N; j++) {
            if (i & (1 << j))
                sum += v[j];
        }
        if (sum == S) {
            ans += 1;
        }
    }
    cout << ans;
    return 0;
}
