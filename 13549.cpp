#include <bits/stdc++.h>
using namespace std;
int N, K;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> K ;

    int dist[200001];
    for(auto& it : dist) it = 1e9;

    dist[N] = 0;

    // { 현재 위치 까지 오는데 걸린 시간, 현재 위치 }
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;
    PQ.push({0, N});

    while(!PQ.empty()){
        auto top = PQ.top(); PQ.pop();
        int cost = top.first, pos = top.second;

        if( cost > dist[pos] ) continue;

        int nPos = pos + 1;
        if( 0 <= nPos && nPos <= 200000 && dist[nPos] > dist[pos] + 1 ){
            dist[nPos] = dist[pos] + 1;
            PQ.push({cost+1, nPos});
        }
        nPos = pos - 1;
        if( 0 <= nPos && nPos <= 200000 && dist[nPos] > dist[pos] + 1 ){
            dist[nPos] = dist[pos] + 1;
            PQ.push({cost+1, nPos});
        }
        nPos = pos * 2;
        if( 0 <= nPos && nPos <= 200000 && dist[nPos] > dist[pos]  ){
            dist[nPos] = dist[pos] ;
            PQ.push({cost, nPos});
        }
    }
    cout << dist[K] ;
    return 0;
}
