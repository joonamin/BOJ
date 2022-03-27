#include <bits/stdc++.h>

using namespace std;
int N, K;
vector<int> dijkstra(int start){
    vector<int> d(100001, 1e9);
    priority_queue< pair<int,int>, vector< pair<int,int> >, greater< pair<int,int> > > PQ;
    PQ.push({0, start});
    d[start] = 0;
    while(!PQ.empty()){
        pair<int,int> top = PQ.top(); PQ.pop();
        int current = top.second, acCost = top.first;

        if( d[current] < acCost ) continue;

        if( current + 1 <= 100000 && d[current+1] > acCost + d[current] + 1 ){
            d[current + 1] = acCost + 1 ;
            PQ.push({acCost+1, current+1});
        }
        if ( current - 1 >= 0 && d[current-1] > acCost + d[current] + 1 ){
            d[current - 1] = acCost + 1;
            PQ.push( {acCost+1, current -1});
        }
        if( current * 2 <= 2*100000 && d[current*2] > acCost + d[current] ){
            d[current * 2] = acCost ;
            PQ.push( {acCost, current*2 });
        }
    }
    return d;
}
int main(void){
	ios_base::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);
	// algorithm 
    cin >> N >> K;
	vector<int> dist = dijkstra(N);

	cout << dist[K];

    return 0;
}
