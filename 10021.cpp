#include <bits/stdc++.h>
using namespace std;

int N, C, parent[2001], rnk[2001] = {0,};
vector<pair<int,int>> pos;
int find(int u){
    if( u == parent[u] )
        return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    if( u == v )
        return false;
    if( rnk[u] > rnk[v] ){
        swap(u, v);
    }
    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v] += 1;
    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> C;
    for(int i=0; i<N; i++){
        int x, y;
        cin >> x >> y;
        pos.push_back({x,y});
    }

    for(int i=0; i<2001; i++)
        parent[i] = i;

    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> PQ;
    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            int from = i, to = j;
            int dist = (pos[from].first - pos[to].first) * (pos[from].first - pos[to].first) +
                    (pos[from].second - pos[to].second) * (pos[from].second - pos[to].second) ;
            if( dist >= C ){
                PQ.push({dist, from, to});
            }
        }
    }

    bool canBuild = true;
    int ans = 0;
    for(int cnt = 0; cnt < N-1; ){
        if( PQ.empty() ){
            canBuild = false;
            break;
        }
        auto top = PQ.top(); PQ.pop();
        int d = get<0>(top), src = get<1>(top), dest = get<2>(top);
        if( !_union(src, dest) )
            continue;
        ans += d;
        cnt += 1;
    }
    if( canBuild )
        cout << ans;
    else
        cout << -1;
    return 0;
}
