#define INF 1e9
#include <bits/stdc++.h>
using namespace std;
double adj[101][101];
double get_dist(pair<double,double> a, pair<double,double> b){
    return sqrt( (a.first - b.first)*(a.first - b.first) + (a.second - b.second)*(a.second - b.second) );
}
int parent[101];
int rnk[101];
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

    int n;
    cin >> n;

    // 각 노드를 매핑
    vector<pair<double,double>> v;
    for(int i=0; i<n; i++){
        double x, y;
        cin >> x >> y;
        v.push_back({x,y});
    }

    priority_queue<tuple<double,int,int>, vector<tuple<double,int,int>>, greater<tuple<double,int,int>>> PQ;
    for(int i=0; i<n; i++){
        parent[i] = i;
        for(int j=i+1; j<n; j++){
            adj[i][j] = get_dist(v[i],v[j]);
            PQ.push({adj[i][j], i, j});
        }
    }

    int cnt = n-1;
    double cost = 0.0;
    while(cnt){
        auto fr = PQ.top(); PQ.pop();
        if( _union(get<1>(fr), get<2>(fr)) ){
            cost += get<0>(fr);
            cnt -= 1;
        }
    }

    cout << fixed;
    cout.precision(2);

    cout << cost;

    return 0;
}
