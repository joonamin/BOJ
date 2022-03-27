#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll N, M, T;
vector<pair<ll, ll>> cat, dp;
ll getDist(pair<ll,ll> a, pair<ll,ll> b){
    ll y = (a.first - b.first), x = (a.second - b.second);
    y = (y<0) ? -y : y;
    x = (x<0) ? -x : x;
    return y + x;
}
struct comp{
    bool operator()(const pair<ll,ll> &a, const pair<ll,ll> &b){
        return (a.first <= b.first && a.second <= b.second);
    }
};
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> M >> T;
    for(int i=0; i<T; i++){
        ll y, x;
        cin >> y >> x;
        if( y < 0 || y > N || x < 0 || x > M ) continue;
        cat.push_back({y,x});
    }

    sort(cat.begin(), cat.end());

    for(int i=0; i<cat.size(); i++){
        const ll &cy = cat[i].first, &cx = cat[i].second;
        if(dp.empty() || (dp.back().first <= cy && dp.back().second <= cx))
            dp.push_back(cat[i]);
        else{
            int idx = lower_bound(dp.begin(), dp.end(), cat[i], comp()) - dp.begin();
            pair<ll,ll> prev;
            if( idx - 1 < 0 ) prev = {0, 0};
            else prev = dp[idx-1];

            if( getDist(prev, dp[idx]) > getDist(prev, cat[i]) )
                dp[idx] = cat[i];
        }
    }
    cout << dp.size() << '\n';

    return 0;
}
