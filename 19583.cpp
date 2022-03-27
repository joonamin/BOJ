#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll time_parse(string s){
    ll h = stoi(s.substr(0, 2)) ;
    ll m = stoi(s.substr(3, 2));
    return h*60LL+m;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    string S, E, Q;
    cin >> S >> E >> Q;
    ll s = time_parse(S), e = time_parse(E), q = time_parse(Q);
    // 1. T <= S 이전인것, 입장확인
    // 2. E <= T <= Q 인 것, 퇴장 확인
    string log;
    map<string, pair<bool,bool>> mp;

    cin.ignore();
    while(getline(cin, log)){
        ll t = time_parse(log);
        string name = log.substr(6);
        if( t <= s )
            mp[name].first = true;
        else if( e <= t && t <= q )
            mp[name].second = true;
    }
    int ans = 0;
    for(const auto& item : mp){
        if( item.second.first && item.second.second )
            ans += 1;
    }

    cout << ans << '\n';
    return 0;
}
