#define INF 1e12
#define ll long long
#include <bits/stdc++.h>
using namespace std;

int N,Q;
vector<pair<ll, int>> adj[5001];
vector<vector<ll>> USADO;

void BFS(int start){
    vector<bool> visited(N+1, false);
    // 큐에는 현재까지 경로로 오면서 가지는 USADO 의 최솟값과 해당 노드의 번호를 넣는다.
    queue<pair<ll,int>> q;
    visited[start] = true;
    USADO[start][start] = INF;

    q.push({INF, start});
    while(!q.empty()){
        auto front = q.front(); q.pop();
        int current = front.second;
        ll min_cost = front.first;

        for(int i=0; i<adj[current].size(); i++){
            int next = adj[current][i].second;
            ll cost = adj[current][i].first;

            if( visited[next] ) continue;
            visited[next] = true;
            if( min_cost > cost ){
                USADO[start][next] = cost;
                q.push({cost, next});
            }
            else{
                USADO[start][next] = min_cost;
                q.push({min_cost, next});
            }

        }

    }

}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N >> Q;
    USADO.resize(N+1, vector<ll>(N+1, INF));

    for(int i=0; i<N-1; i++){
        ll p,q,r;
        cin >> p >> q >> r;
        adj[p].push_back({r,q});
        adj[q].push_back({r,p});
    }

    // BFS를 통해 그 경로의 모든 연결들의 USADO중 최솟값을 정한다.
    for(int i=1; i<=N; i++){
        BFS(i);
    }


    // output
    for(int i=0; i<Q; i++){
        ll k, v;
        cin >> k >> v;
        int cnt = 0;
        for(auto item : USADO[v]){
            if( item >= k && item < INF )
                cnt += 1;
        }
        cout << cnt << '\n';
    }


    return 0;
}