#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int parent[200005], rnk[200005], counter = 1;
map<string, int> mp;
map<int, int> cntPerID;
int find(int u){
    if(parent[u] == u)
        return u;
    return parent[u] = find(parent[u]);
}
int _union(int u, int v){
    u = find(u), v = find(v);
    if(u == v)
        return 0;
    if(rnk[u] > rnk[v])
        swap(u, v);
    parent[u] = v;
    if(rnk[u] == rnk[v])
        rnk[v] += 1;

    cntPerID[v] += cntPerID[u];
    cntPerID[u] = 0;
    return v;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int T;
    cin >> T;
    while(T--){
        for(int i=0; i<200005; i++) parent[i] = i;
        fill(rnk, rnk+200005, 0);
        counter = 1;
        mp.clear();
        cntPerID.clear();

        int F;
        cin >> F;
        while(F--) {
            string u, v;
            cin >> u >> v;

            if (mp.find(u) == mp.end()) {
                mp[u] = counter++;
                cntPerID[mp[u]] = 1;
            }
            if (mp.find(v) == mp.end()) {
                mp[v] = counter++;
                cntPerID[mp[v]] = 1;
            }
            int k = _union(mp[u], mp[v]);
            if(k != 0)
                cout << cntPerID[k] << '\n';
            else{
                cout << cntPerID[find(mp[u])] << '\n';
            }
        }
    }

    return 0;
}
