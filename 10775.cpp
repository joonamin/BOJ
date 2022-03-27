#include <bits/stdc++.h>
using namespace std;
vector<int> airport;
int find(int u){
    if( airport[u] == u )
        return u;
    return airport[u] = find(airport[u]);
}
void merge(int u, int v){
    u = find(u), v = find(v);
    // u > v
    airport[u] = v;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    // airport[u] : u이하의 공항중에서 도킹할 수 있는 공항중 최댓값
    // airport[u] = u-1 (if, u is already porting )
    // find(airport[u]) : u이하의 공항중에서 도킹할 수 있는 공항을 찾는 메소드
    int N;
    cin >> N;

    airport.resize(N+1);
    for(int i=1; i<=N; i++)
        airport[i] = i;

    int K;
    cin >> K;

    int cnt = 0;
    for(int i=0; i<K; i++){
        int g;
        cin >> g;
        int k = find(g);
        if( k == 0 )
            break;
        merge(k, k-1); // g -> g-1
        cnt += 1;
    }
    cout << cnt ;
    return 0;
}
