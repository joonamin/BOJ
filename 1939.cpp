#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<pair<int,int>> weight[10001];
int src, dest;
bool BFS(int itemWeight){
    bool visited[10001] = {false,};
    queue<pair<int,int>> Q;
    visited[src] = true;
    Q.push({0, src});

    while(!Q.empty()){
        int current = Q.front().second ; Q.pop();
        if( current == dest ) return true;

        for(int i=0; i<weight[current].size(); i++){
            int next = weight[current][i].second;
            // 현재 다리가 버틸 수 있는 최대 중량
            int maxWeight = weight[current][i].first;
            if( visited[next] || maxWeight < itemWeight )
                continue;
            visited[next] = true;
            Q.push({maxWeight, next});
        }

    }
    return false;
}
int main(void){
	// algorithm
    cin >> N >> M;

    for(int i=0; i<M; i++){
        int from, to, w;
        cin >> from >> to >> w;
        weight[from].push_back({w, to});
        weight[to].push_back({w, from});
    }

    cin >> src >> dest;

    long long left = 0, right = 1e9;
    long long ans = -1e9;
    while(left <= right){
        // src -> dest 가는 길 위에 모든 간선이 무게가 mid 이하이면 true
        int mid = (left+right)/2;
        bool canVisit = BFS(mid);
        if( canVisit ){
            ans = mid;
            left = mid + 1;
        }else{
            right = mid - 1;
        }
    }
    cout << ans;

    return 0;
}
