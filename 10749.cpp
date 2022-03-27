#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int parent[2001], rnk[2001];
int find(int u){
    if(u == parent[u]) return u;
    return parent[u] = find(parent[u]);
}
bool _union(int u, int v){
    u = find(u), v = find(v);
    if(u == v)
        return false;

    if(rnk[u] > rnk[v]){
        swap(u, v);
    }
    parent[u] = v;
    if(rnk[u] == rnk[v])
        rnk[v]++;

    return true;
}
typedef tuple<int, int, int> tp;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int N;
    cin >> N;

    vector<int> id(N);
    for(int i=0; i<N; i++){
        cin >> id[i];
    }
    // 최적 부분 구조
    priority_queue<tp, vector<tp>> PQ;
    for(int i=0; i<N; i++){
        for(int j=i+1; j<N; j++){
            PQ.push({id[i] ^ id[j], i, j});
        }
    }

    for(int i=0; i<2001; i++)
        parent[i] = i;
    int c = N-1;
    long long ans = 0;
    while(c){
        int cost, u, v;
        tie(cost, u, v) = PQ.top(); PQ.pop();

        if(!_union(u, v)) continue;
        ans += cost;
        c--;
    }
    cout << ans;
    return 0;
}

