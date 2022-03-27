#define INF 1e9
#include <bits/stdc++.h>
using namespace std;
int n;
int w[16][16];
vector<bool> visited;
int answer = 1e9;
void DFS(const int initial, int current, int ac_cost, int cnt){
    if( cnt == n ){
        if( w[current][initial] != INF ){
            if( answer > ac_cost + w[current][initial] )
                answer = ac_cost + w[current][initial];
            return;
        }
        return;
    }
    for(int i=0; i<n; i++){
        if( w[current][i] != INF && !visited[i] ){
            visited[i] = true;
            DFS(initial, i, ac_cost + w[current][i], cnt + 1);
            visited[i] = false;
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> n;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++) {
            cin >> w[i][j];
            if( w[i][j] == 0 )
                w[i][j] = INF ;
        }
    }

    // Traveling sales man
    for(int i=0; i<n; i++){
        visited.resize(n, false);

        visited[i] = true;
        DFS(i,i,0,1);
        visited[i] = false;
    }
    cout << answer ;

    return 0;
}
