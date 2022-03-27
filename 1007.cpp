#define ll long long
#include <bits/stdc++.h>
using namespace std;
int N;
vector<pair<ll,ll>> v;
bitset<21> selected;
pair<ll, ll> ans = {1e7, 1e7};

void select(int rest, int idx){
    if( rest == 0 ){
        // selected가 false 인 것을 종점(to)라고 정의
        pair<ll, ll> ret = {0, 0};
        for(int i=0; i<N; i++){
            if( selected[i] ){
                ret.first -= v[i].first;
                ret.second -= v[i].second;
            }
            else{
                ret.first += v[i].first;
                ret.second += v[i].second;
            }
        }
        if( ret.first * ret.first + ret.second * ret.second <
                ans.first * ans.first + ans.second * ans.second ){
            ans.first = ret.first;
            ans.second = ret.second;
        }
        return;
    }
    for(int i=idx; i<N; i++){
        if(selected[i])
            continue;
        selected[i] = true;
        select(rest-1, i+1);
        selected[i] = false;
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int T;
    cin >> T;
    cout.precision(7);
    cout << fixed;

    while(T--){
        cin >> N;
        v.resize(N), selected.reset();
        ans = {1e7, 1e7};

        for(int i=0; i<N; i++){
            cin >> v[i].first >> v[i].second;
        }
        select(N/2, 0);
        cout << sqrt( ans.first * ans.first + ans.second * ans.second ) << '\n';
    }
    return 0;
}
