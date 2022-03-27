#include <bits/stdc++.h>
using namespace std;
vector<pair<int,int>> adj[1001];
int parent[1001], rnk[1001];
int find(int u){
    if( u == parent[u] )
        return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    if( u == v )
        return false;
    if( rnk[u] > rnk[v] )
        swap(u,v);
    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v] += 1;
    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int N,M;
    cin >> N >> M;

    // init
    for(int i=1; i<=N; i++){
        rnk[i] = 1;
        parent[i] = i;
    }

    // 모든 컴퓨터를 연결하는데 필요한 최소비용 필요
    for(int i=0; i<M; i++){
        int a,b,c;
        cin >> a >> b >> c;
        adj[a].push_back({c, b});
    }

    // 코스트, 전, 후
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> PQ;
    for(int i=1; i<=N; i++){
        for( auto info : adj[i] ){
            PQ.push({info.first, i, info.second});
        }
    }

    int cnt = N-1;
    long long min_cost = 0;
    while(cnt){
        tuple<int,int,int> top = PQ.top();
        PQ.pop();

        if( find(get<1>(top)) == find(get<2>(top)) )
            continue;

        _union(get<1>(top), get<2>(top));
        min_cost += get<0>(top);
        cnt -= 1;
    }

    cout << min_cost;
    return 0;
}
