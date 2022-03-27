#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<int> in[32001], out[32001];

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M ;

    for(int i=0; i<M; i++){
        int pre, post;
        cin >> pre >> post;
        in[post].push_back(pre);
        out[pre].push_back(post);
    }

    vector<int> answer;
    vector<bool> erased(N+1,false);

    int cnt = 0;
    while(cnt != N){
        int target = -1;
        for(int i=1; i<=N; i++){
            if( in[i].empty() && !erased[i] ){
                target = i ;
                erased[target] = true;
                cnt += 1;
                answer.push_back(target);
                break;
            }
        }
        // adjusting
        for(int i=0; i<out[target].size(); i++){
            // out[target][i]
            auto& cur = out[target][i];
            for(int j=0; j<in[cur].size(); j++){
                if( in[cur][j] == target ){
                    in[cur].erase(in[cur].begin() + j);
                }
            }
        }
    }
    for(auto c : answer)
        cout << c << ' ' ;

    return 0;
}
