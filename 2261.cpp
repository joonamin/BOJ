#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

struct order{
    bool operator()(const pair<int,int>& a, const pair<int,int>& b) const{
        return tie(a.second, a.first) < tie(b.second, b.first);
    }
};
int N;
vector<pair<int,int>> v;
int getDist(pair<int,int> a, pair<int,int> b){
    int x = b.first - a.first;
    int y = b.second - a.second;
    return x*x + y*y;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N;
    v.resize(N);
    for(int i=0; i<N; i++){
        cin >> v[i].first >> v[i].second;
    }

    sort(v.begin(), v.end());

    int d = getDist(v[0], v[1]);
    // tree형태의 set으로 구현됨
    set<pair<int,int>, order> cand = {v[0], v[1]};
    int offset = 0;
    for(int i=2; i<N; i++){
        // v[i]와 x좌표가 d+1이상 차이나는 점들은 후보군에서 제외한다.
        // v[i]는 x를 기준으로 정렬되어 있다.
        while( offset < i ){
            int diff= v[i].first - v[offset].first;
            if( diff * diff > d ){
                cand.erase(v[offset]);
                offset += 1;
            }else
                break;
        }
        // 후보 군 내에서 v[i]와 y좌표가 d+1 이상 차이나는 점들은 후보군에서 제외한다.
        // 이 후, 후보군내에서 거리가 최적인 것이 있다면 그것으로 재설정해준다.
        int sqrtD = (int)sqrt((double)d) + 1;
        pair<int,int> low = {-1e9, v[i].second-sqrtD}, hi = {1e9, v[i].second+sqrtD};
        auto lb = cand.lower_bound(low), ub = cand.upper_bound(hi);
        for(auto it = lb; it != ub; it++){
            int nd = getDist(v[i], *it);
            if( nd < d )
                d = nd;
        }
        cand.insert(v[i]);
    }
    cout << d ;
    return 0;
}
