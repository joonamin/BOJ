#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<int> adj[32001] ;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M;
    vector<int> in(N+1,0);
    for(int i=0; i<M; i++){
        // a 번 문제는 b번 문제보다 먼저 푸는 것이 좋다.
        int a, b;
        cin >> a >> b;
        in[b] += 1;
        adj[a].push_back(b);
    }
    vector<int> answer;
    // in = 0개 인 것 중 번호가 가장 빠른 것
    // { in-edge , number }

    // 방향이 존재하고 사이클이 생기지 않는 그래프임이 보장
    priority_queue<int, vector<int>, greater<int>> PQ;
    for(int i=1; i<=N; i++){
        if( in[i] == 0 ){
            PQ.push(i);
        }
    }
    while(!PQ.empty()){
        int current = PQ.top(); PQ.pop();
        answer.push_back(current);
        for(auto next : adj[current]){
            in[next] -= 1;
            if( in[next] == 0 )
                PQ.push(next);
        }
    }
    for(auto item : answer)
        cout << item << ' ' ;
    return 0;
}
