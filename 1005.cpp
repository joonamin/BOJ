#include <bits/stdc++.h>
using namespace std;

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int T;
    cin >> T;

    while(T--){
        int N, K;
        cin >> N >> K;

        vector<int> time(N+1);
        vector<int> cost(N+1);
        vector<int> precedence[1001];
        vector<int> out[1001];

        for(int i=1; i<=N; i++)
            cin >> cost[i];

        for(int i=0; i<K; i++){
            int before, after;
            cin >> before >> after;
            out[before].push_back(after);
            precedence[after].push_back(before);
        }

        // topological sort
        // 처리 시간이 빠른 일 부터 처리한다.

        // { 처리 시간, 현재 노드 }
        priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;

        for(int i=1; i<=N; i++){
            if( precedence[i].empty() ){
                time[i] = cost[i];
                PQ.push({time[i], i});
            }
        }

        while(!PQ.empty()){
            pair<int,int> top = PQ.top(); PQ.pop();

            int current = top.second;
            // adjusting
            for(auto v : out[current]){
                for(int i=0; i<precedence[v].size(); i++){
                    if( precedence[v][i] == current ){
                        precedence[v].erase(precedence[v].begin() + i);
                        break;
                    }
                }
                if( precedence[v].empty() ){
                    time[v] = time[current] + cost[v];
                    PQ.push({time[v], v});
                }
            }
        }

        //
        int W;
        cin >> W;
        cout << time[W] << '\n';
    }

    return 0;
}
