#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int N, ans = 0;
    cin >> N;
    vector<pair<ll,ll>> pv(N);
    for(auto &item : pv)
        cin >> item.first >> item.second;

    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            for(int k=j+1; k<N; k++){
                // (i, j, k), O(N^3)로 가능할까?
                ll sqLen[3];
                sqLen[0] = (pv[i].first - pv[j].first) * (pv[i].first - pv[j].first) +
                        (pv[i].second - pv[j].second) * (pv[i].second - pv[j].second);

                sqLen[1] = (pv[i].first - pv[k].first) * (pv[i].first - pv[k].first) +
                           (pv[i].second - pv[k].second) * (pv[i].second - pv[k].second);

                sqLen[2] = (pv[j].first - pv[k].first) * (pv[j].first - pv[k].first) +
                           (pv[j].second - pv[k].second) * (pv[j].second - pv[k].second);
                if(sqLen[0] == sqLen[1] + sqLen[2] || sqLen[1] == sqLen[0] + sqLen[2] ||
                sqLen[2] == sqLen[0] + sqLen[1])
                    ans += 1;
            }
        }
    }
    cout << ans;
    return 0;
}
