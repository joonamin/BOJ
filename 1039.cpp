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
    string N;
    int K;
    cin >> N >> K;

    map<pair<string, int>, bool> mp;
    queue<pair<string, int>> Q;
    mp[{N, 0}] = true;
    Q.push({N, 0});

    long long ans = -1;
    while(!Q.empty()){
        string cur;
        int cnt;
        tie(cur, cnt) = Q.front(); Q.pop();
        if(cnt == K){
            ans = max<long long>(ans, stoll(cur));
            continue;
        }
        for(int i=0; i<N.size(); i++){
            for(int j=i+1; j<N.size(); j++){
                string next = cur;
                swap(next[i], next[j]);
                if(next[0] == '0') continue;
                if(mp[{next, cnt+1}]) continue;
                mp[{next,cnt + 1}] = true;
                Q.push({next, cnt + 1});
            }
        }

    }
    cout << ans ;

    return 0;
}
