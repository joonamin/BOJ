#define MAX_SIZE 100001
#include <bits/stdc++.h>
using namespace std;
int parent[MAX_SIZE], _rank[MAX_SIZE];
int find(int u){
    if( parent[u] == u )
        return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    if( u == v )
        return false;
    if( u > v )
        swap(u, v);
    parent[u] = v;
    if( _rank[u] == _rank[v] )
        _rank[v] += 1;

    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    int N;
    cin >> N;

    for(int i=0; i<N; i++)
        parent[i] = i;

    pair<int,int> x[MAX_SIZE], y[MAX_SIZE], z[MAX_SIZE];
    for(int i=0; i<N; i++){
        cin >> x[i].first >> y[i].first >> z[i].first;
        x[i].second = y[i].second = z[i].second = i;
    }
    sort(x, x+N);
    sort(y, y+N);
    sort(z, z+N);


    auto compare = [](const tuple<int,int,int>& a, const tuple<int,int,int>& b ) -> bool{
        if( get<0>(a) == get<0>(b) ){
            if( get<1>(a) == get<1>(b) ){
                return get<2>(a) > get<2>(b);
            }
            return get<1>(a) > get<1>(b);
        }
        return get<0>(a) > get<0>(b);
    };
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, decltype(compare)> PQ(compare);
    // tuple { 거리, A, B }
    for(int i=0; i<N-1; i++){
        int x_diff, y_diff, z_diff;
        x_diff = x[i+1].first - x[i].first;
        y_diff = y[i+1].first - y[i].first;
        z_diff = z[i+1].first - z[i].first;


        PQ.push({x_diff, x[i].second, x[i+1].second});
        PQ.push({y_diff, y[i].second, y[i+1].second});
        PQ.push({z_diff, z[i].second, z[i+1].second});
    }

    int cnt = 0 ;
    long long ans = 0 ;
    while( cnt < N && !PQ.empty() ){
        auto top = PQ.top(); PQ.pop();
        int dist = get<0>(top);
        int from = get<1>(top);
        int to = get<2>(top);

        if( !_union(from, to) )
            continue;

        cnt += 1;
        ans += dist;
    }
    cout << ans << '\n';
    return 0;
}