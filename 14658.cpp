#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int n, m, l, k;
vector<pair<int,int>> v;
int calc(int x, int y) {
    int res = 0;
    for (int i = 0; i < k; i++) {
        if (x <= v[i].first && v[i].first <= x + l && y <= v[i].second && v[i].second <= y + l) {
            res++;
        }
    }
    return res;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> n >> m >> l >> k;

    v.resize(k);
    for (auto &item : v) {
        cin >> item.first >> item.second;
    }

    int ans = 0;
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < k; j++) {
            ans = max(ans, calc(v[i].first, v[j].second));
        }
    }

    cout << k - ans ;
    return 0;
}
