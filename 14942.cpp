#include <bits/stdc++.h>
using namespace std;
int n, ant[100001], parent[100001][25], neededEnergy[100001][25] ;
vector<pair<int,int>> adj[100001];
void DFS(int current, int prev){
    for(auto next : adj[current]){
        if( next.first == prev ) continue;
        parent[next.first][0] = current;
        neededEnergy[next.first][0] = next.second;
        DFS(next.first, current);
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> n;
    for(int i=1; i<=n; i++)
        cin >> ant[i];

    for(int i=0; i<n-1; i++){
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].push_back({b, c});
        adj[b].push_back({a, c});
    }

    DFS(1, 0);

    // i번째 굴의 2^k번째 조상 굴
    for(int j=1; j<=24; j++){
        for(int i=1; i<=n; i++){
            if( parent[i][j-1] == 0 ) continue;
            parent[i][j] = parent[parent[i][j-1]][j-1];
        }
    }

    // i번째 굴에서 2^k번째 조상 굴 까지 가는데 필요한 총 에너지
    for(int j=1; j<=24; j++){
        for(int i=1; i<=n; i++){
            if( neededEnergy[i][j-1] == 0 ) {
                neededEnergy[i][j - 1] = 1e9;
            }
            if( neededEnergy[parent[i][j-1]][j-1] == 0 ){
                neededEnergy[parent[i][j-1]][j-1] = 1e9;
            }
            neededEnergy[i][j] = neededEnergy[i][j-1] + neededEnergy[parent[i][j-1]][j-1];
            if( neededEnergy[i][j] >= 1e9 )
                neededEnergy[i][j] = 1e9;
        }
    }

    for(int i=1; i<=n; i++){
        // i번째 굴에 있는 개미가 도달할 수 있는 방 중에 1번 방과 가장 가까운 방의 번호
        int& currentEnergy = ant[i];
        int currentRoom = i;
        while( currentRoom > 1 && currentEnergy > 0 ){
            int move = -1;
            for(int k=0; k<25; k++){
                if( neededEnergy[currentRoom][k] <= currentEnergy )
                    move = k;
            }
            if( move == -1 )
                break;
            currentEnergy -= neededEnergy[currentRoom][move];
            currentRoom = parent[currentRoom][move];
        }
        cout << currentRoom << '\n';
    }

    return 0;
}
