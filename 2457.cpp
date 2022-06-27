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

    int N;
    cin >> N;

    vector<pair<int,int>> v;
    for (int i = 0; i < N; i++) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        v.push_back({a * 100 + b, c * 100 + d});
    }
    sort(v.begin(), v.end());

    int cur = 301, idx = -1, ans = 0;
    while(cur <= 1130) {
        // cur이하의 start_time을 가지는 v[i]에 대해서 end_time이 가장 긴 것 선택
        // cur 이하의 시간은 이미 커버되었음.
        bool canFind = false;
        for (int i = idx+1; i < N; i++) {
            if(v[i].first <= cur){
                canFind = true;
                if(idx == -1 || v[i].second > v[idx].second)
                    idx = i;
            }
        }
        if(!canFind) {
            cout << 0 << '\n';
            return 0;
        }
        ans++;
        cur = v[idx].second;
    }

    if(cur <= 1130)
        cout << 0 << '\n';
    else
        cout << ans << '\n';

    return 0;
}
