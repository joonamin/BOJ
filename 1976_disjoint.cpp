#include <bits/stdc++.h>
using namespace std;
int N, M;
bool board[201][201]; // 단순 연결 상태 확인
bool visited[201] = {false, }; // 그래프 탐색을 위함

int parent[201] ; // i번째 노드의 부모를 나타낸다.
int rnk[201];
int find(int u){
    if( u == parent[u] )
        return u;
    // path compression
    return parent[u] = find(parent[u]);
}
void _union(int u, int v){
    u = find(u), v = find(v);

    if( u == v ) return;
    if( rnk[u] > rnk[v] )
        swap(u,v);

    // rnk[u] <= rnk[v] 가 보장
    parent[u] = v;
    if( rnk[u] == rnk[v] )
        rnk[v] += 1;

}
void DFS(int start){
    if( start > N )
        return;

    for(int i=1; i<=N; i++){
        if( board[start][i] == true && !visited[i] ){
            visited[i] = true;
            // 분리 집합에 추가 시킨다.
            _union(start, i);
            DFS(i);
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M ;

    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++)
            cin >> board[i][j];
    }

    vector<int> travelPlan(M);
    for(int i=0; i<M; i++)
        cin >> travelPlan[i];


    // disjoint set 초기화
    for(int i=1; i<=N; i++) {
        parent[i] = i;
        rnk[i] = 1;
    }

    // 모든 노드를 그래프 탐색 한다.
    for(int i=1; i<=N; i++){
        DFS(i);
    }

    bool canTravel = true;
    for(int i=0; i<M-1; i++){
        int d1 = travelPlan[i], d2 = travelPlan[i+1];
        if( find(d1) != find(d2) ){
            canTravel = false;
            break;
        }
    }

    if( canTravel ) cout << "YES\n";
    else cout << "NO\n";

    return 0;
}