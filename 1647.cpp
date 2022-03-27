#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<int> parent, rnk;
vector<pair<int,int>> adj[100001];
int find(int u){
    if( parent[u] == u )
        return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    if( u == v ){
        return false;
    }
    // union by rank
    if( rnk[u] > rnk[v] )
        swap(u,v);
    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v]++;

    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M;
    parent.resize(N+1), rnk.resize(N+1, 0);
    for(int i=0; i<=N; i++)
        parent[i] = i ;

    for(int i=0; i<M; i++){
        int A, B, C;
        cin >> A >> B >> C;
        adj[A].push_back({C,B});
        adj[B].push_back({C,A});
    }

    // N-1 개의 간선만 있으면 됨.
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<tuple<int,int,int>>> PQ; // { cost, u, v  }
    for(int i=0; i<N; i++){
        for(auto it : adj[i]){
            PQ.push({it.first,i,it.second});
        }
    }


    int answer = 0;
    int loop_cnt = N-1-1;
    while( loop_cnt && !PQ.empty() ){
        auto top = PQ.top(); PQ.pop();
        if( !_union(get<1>(top), get<2>(top)) )
            continue;

        _union(get<1>(top), get<2>(top));
        loop_cnt -= 1;
        answer += get<0>(top);

    }
    cout << answer ;

    return 0;
}