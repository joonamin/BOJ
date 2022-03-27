#include <bits/stdc++.h>
using namespace std;
int n, m;
int parent[500001], rnk[500001] = {0, };
int find(int u){
    if( parent[u] == u )
        return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    // rank가 낮은 것을 더 큰 것에 결합
    if( u == v )
        return false;
    if( rnk[u] > rnk[v] )
        swap(u, v);

    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v] += 1;

    return true;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> n >> m;

    for(int i=0; i<n; i++)
        parent[i] = i;

    int current_stage = 1;
    for(int i=0; i<m; i++){
        int a, b;
        cin >> a >> b;
        if( !_union(a, b) )
            break;
        current_stage += 1;
    }
    if( current_stage > m )
        cout << 0;
    else
        cout << current_stage;

    return 0;
}
