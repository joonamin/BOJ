#include <bits/stdc++.h>
using namespace std;
int N,M;
vector<int> adj[50001];
int parent[50001];
int depth[50001];
bool visited[50001];
void make_tree(int prev, int current, int cnt){
    parent[current] = prev;
    depth[current] = cnt;
    for(auto next : adj[current]){
        if( visited[next] ) continue;

        visited[next] =true;
        make_tree(current, next, cnt + 1);
    }
}
int find_common_ancestor(int a, int b){

    if( depth[a] > depth[b] )
        swap(a,b);
    // b의 depth가 a의 depth보다 무조건 크거나 같음
    if( a == b )
        return a;
    if( parent[a] == parent[b] )
        return parent[a];

    if( a == 1 || b == 1 )
        return 1;

    if( depth[a] == depth[b] )
        return find_common_ancestor(parent[a], parent[b]);
    else
        return find_common_ancestor(a, parent[b]);
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N ;

    for(int i=0; i<N-1; i++){
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // 트리 형성
    visited[1] = true;
    make_tree(0,1,0);

    cin >> M ;
    while(M--){
        int a, b;
        cin >> a >> b;
        cout << find_common_ancestor(a,b) << '\n';
    }


    return 0;
}
