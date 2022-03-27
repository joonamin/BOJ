#define INF 1e9
#include <bits/stdc++.h>
using namespace std;
int N,M;
int dist[201][201];
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin >> N >> M;

    for(int i=1; i<=N; i++){
        for(int j=1; j<=N; j++) {
            cin >> dist[i][j];
            if(dist[i][j] == 0)
                dist[i][j] = INF;
        }
    }

    vector<int> travelPlan(M);
    for(int i=0; i<M; i++){
        cin >> travelPlan[i];
    }

    for(int k=1; k<=N; k++){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if( i == j ) dist[i][j] = 0;
                else if( dist[i][j] > dist[i][k] + dist[k][j] )
                    dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }

    bool canTraverse = true;
    for(int i=0; i<M-1; i++){
        int from = travelPlan[i];
        int to = travelPlan[i+1];
        if( dist[from][to] >= INF ){
            canTraverse = false;
            break;
        }
    }
    if( canTraverse ) cout << "YES";
    else cout << "NO";

    return 0;
}