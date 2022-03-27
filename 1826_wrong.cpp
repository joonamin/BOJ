#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
ll N, L, P;
vector<pair<ll,ll>> v;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N ;
    v.resize(N);
    for(int i=0; i<N; i++){
        cin >> v[i].first >> v[i].second;
    }
    sort(v.begin(), v.end());

    cin >> L >> P;

    ll cur = 0, oil = P, ans = 0;
    int i;
    for(i=0; i<N+1; i++){
        // 현재 가진 오일로 갈 수 있는 최대 거리의 위치 : cur + oil = k
        ll k = min(cur + oil, L);
        if(k == L)
            break;
        // (cur, k] 구간에 있는 주유소 중, k까지 도착했을 때 연료가 가장 많은 주유소 선택 (x) => 굳이 k까지 갈 필요가 없다.
        // ce) 위 조건을 만족하는 주유소 g_i 에 대해 [g_i, k] 사이에 있는 주유소를 택하여 최적해가 나오는 경우도 발생한다.
        // ce2) 추 후의 선택에서 이전 주유소를 반드시 택해야 갈 수 있는 경로가 존재, 이전에 하나의 지점만 고려했음..
        // ex)
        //2
        //2 3
        //4 7
        //14 4

        int idx = -1;
        for(int j=0; j<N; j++){
            if(cur < v[j].first && v[j].first <= k){
                if(idx == -1 || oil + cur - v[idx].first + v[idx].second < oil + cur - v[j].first + v[j].second)
                    idx = j;
            }
        }
        if(idx != -1){
            oil = oil + cur - v[idx].first + v[idx].second;
            cur = v[idx].first;
            ans += 1;
        }
    }
    if(i > N)
        ans = -1;
    cout << ans ;
    return 0;
}
