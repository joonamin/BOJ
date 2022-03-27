#define INF 1e9
#include <bits/stdc++.h>
using namespace std;

// u -> v 로 가는 edge 가 2개 이상 존재할 수도 있음 ( 비둘기 집의 원리 )
vector<pair<int,int>> adj[501];
vector<long long> dist;
int N, M;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M ;
    for(int i=0; i<M; i++){
        int src, dest, cost;
        cin >> src >> dest >> cost;
        adj[src].push_back({cost, dest});
    }

    dist.resize(N+1, INF);
    dist[1] = 0 ;

    bool hasNegativeCycle = false;
    for( int round = 1;  round <= N ; round ++ ){
        // 모든 edge 들을 조사한다.
        for(int i=1; i<=N; i++){
            for(auto item : adj[i]){
                int cost = item.first;
                int next = item.second;

                if( dist[i] != INF && dist[next] > dist[i] + cost ){
                    dist[next] = dist[i] + cost;
                    if( round == N ) hasNegativeCycle = true;
                }
            }
        }
    }

    // 1 -> v 까지 걸리는 시간을 구하고 싶다. u를 경로 상에 존재하는 또 다른 노드라고 생각하자.
    // 1 -> u 로 가는 경로 중 negative cycle 이 있다면
    // u -> v 로 가는 경로도 사이클을 따라서 무한히 돌게 된다.

    // 문제 조건에 따라, 1 -> v 로 가는 과정에서 무한히 오래 전으로 돌릴 수 있는 경우 -1을 출력하라는 조건이
    // 있으므로, 1 -> v 로 가는 길에서 음수 사이클이 존재하는 지 여부를 확인해야하는 것이 맞다.
    // 하지만, N번째 라운드에서 hasNegativeCycle이 true 로 바뀌었다는 것은 v로 가는 길이 INF가 아니고
    // 즉, v로 가는 길이 연결 되어 있고 그 경로 중 음수 사이클이 존재 한다는 것이다.
    // 출력 초과의 원인은 바로 데이터형 !
    // 최악의 경우 dist[v]의 값은 500 * 6000 * -10000 이 되고 이는  -3 * 10^10 이므로
    // int형을 표현 할 경우 underflow 가 남.
    // 최악의 경우 ? 500 개의 노드 사이의 6000 개의 edge 가 존재, 이 들의 코스트는 모두 -10000
    // 이 경우, N번째 stage에서도 값이 갱신 되는데 이 경우 500 * 6000 * -10000 이 된다.


    if( hasNegativeCycle )
        cout << -1 << '\n';
    else{
        for(int i=2; i<=N; i++){
            if( dist[i] >= INF ) cout << -1 << '\n';
            else cout << dist[i] << '\n';
        }
    }

    return 0;
}