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
    int n;
    cin >> n;
    vector<pair<int,int>> v(n);
    for(int i=0; i<n; i++){
        cin >> v[i].first >> v[i].second;
    }

    // x축 기준으로 정렬
    sort(v.begin(), v.end());

    int ans = v[0].second - v[0].first ;
    pair<int,int> coverage = v[0];
    for(int i=1; i<n; i++){
        // 이전에 커버한 영역과의 합집합
        if( v[i].first <= coverage.second ){
            if( v[i].second - coverage.second >= 0 ) {
                ans += v[i].second - coverage.second;
                coverage.second = v[i].second;
            }
        }
        else{
            ans += (v[i].second - v[i].first);
            coverage = v[i];
        }
    }
    cout << ans ;

    return 0;
}
