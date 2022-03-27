#include <bits/stdc++.h>
using namespace std;
int N, M;
int in[1001];
vector<int> out[1001];

int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M;

    for(int i=0; i<M; i++){
        int C;
        cin >> C;
        vector<int> order(C) ;
        for(int j=0; j<C; j++){
            cin >> order[j];
        }
        for(int j=0; j<C-1; j++){
            // order[j] -> order[j+1]
            in[order[j+1]] += 1;
            out[order[j]].push_back(order[j+1]);
        }
    }

    queue<int> Q;
    for(int i=1; i<=N; i++){
        if( in[i] == 0 ){
            Q.push(i);
        }
    }

    vector<int> answer ;
    int loop = N;
    while(loop--){
        if( Q.empty() ){
            cout << 0 ;
            exit(0);
        }
        int target = Q.front(); Q.pop();
        answer.push_back(target);

        for(auto c : out[target]){
            if( --in[c] == 0 )
                Q.push(c);
        }
    }
    for(auto item : answer ){
        cout << item << '\n';
    }
    return 0;
}
