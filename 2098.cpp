#define INF 1e9
#include <bits/stdc++.h>
using namespace std;
int w[16][16];
int dp[16][1<<16] ; // j : 0100..  1번 도시 방문
int N;
int DFS(int current, int visited){
    if( visited == (1<<N)-1 ){
        if( w[current][0] == 0 )
            return INF;
        return w[current][0];
    }
    if( dp[current][visited] != 0 )
        return dp[current][visited];
    int ret = INF;
    for(int i=0; i<N; i++){
        if( w[current][i] == 0 || (visited & (1<<i)) )
            continue;
        ret = min(ret, w[current][i] + DFS(i, visited | (1<<i) ) );
    }
    return dp[current][visited] = ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);

    cin >> N;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++)
            cin >> w[i][j];
    }
    cout << DFS(0, 1);

    return 0;
}