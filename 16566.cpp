#include <bits/stdc++.h>
using namespace std;
int N, M, K;
int p[4000001], arr[4000001];
int find(int u){
    if( u == p[u] )
        return u;
    return p[u] = find(p[u]);
}
void _union(int u, int v){
    // v = u + 1
    u = find(u), v = find(v);
    if( u == v )
        return ;
    p[u] = v;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> M >> K;
    for(int i=0; i<M; i++){
        cin >> arr[i];
        p[i] = i;
    }
    sort(arr, arr+M);
    while(K--){
        int upperThan;
        cin >> upperThan;
        int d = find( upper_bound(arr, arr+M, upperThan) - arr );
        cout << arr[d] << '\n';
        _union(d, d+1);
    }
    return 0;
}