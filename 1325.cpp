#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<int> adj[10001];
vector<int> connected;
int BFS(int start){
    int ret = 0;
    vector<bool> visited(N+1, false);
    queue<int> Q;
    visited[start] = true;
    Q.push(start);

    while(!Q.empty()){
        int fr = Q.front(); Q.pop();
        for(int i=0; i<adj[fr].size(); i++){
            int next = adj[fr][i];
            if( !visited[next] ) {
                visited[next] = true;
                Q.push(next);
                ret += 1;
            }
        }
    }
    return ret;
}
int main(void){
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);
	// algorithm
    cin >> N >> M;

    for(int i=0; i<M; i++){
        int to, from;
        cin >> to >> from;
        adj[from].push_back(to);
    }

    connected.resize(N+1, -1);
    for(int i=1 ; i<=N; i++){
        connected[i] = BFS(i);
    }

    vector<int> ans ;
    // 최댓값 찾기
    int max_idx = -1, max_value = -1;
    for(int i=0; i<connected.size(); i++) {
        if (max_value < connected[i]) {
            max_idx = i;
            max_value = connected[i];
        }
    }
    ans.push_back(max_idx);

    for(int i=0; i<connected.size(); i++){
        if( i != max_idx && max_value == connected[i] )
            ans.push_back(i);
    }

    for(auto it : ans ) cout << it << ' ';
    return 0;
}
