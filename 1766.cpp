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

    int cnt = 0 ;
    vector<bool> erased(N+1, false);
    while( cnt < N ){
        for(int i=1; i<=N; i++){
            if( !erased[i] && in[i] == 0 ){
                erased[i] = true;
                // 후 처리
                answer.push_back(i);
                cnt += 1;
                for(auto it : adj[i])
                    in[it] -= 1;
                break;
            }
        }
    }
    for(auto it : answer)
        cout << it << ' ' ;

    return 0;
}
