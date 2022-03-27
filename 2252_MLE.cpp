#include <bits/stdc++.h>
using namespace std;
int N, M;

bool adj[32001][32001];
int in[32001], out[32001];
bool erased[32001];
//vector<vector<bool>> adj;
//vector<int> in,out;
//vector<bool> erased;
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    cin >> N >> M ;

    // Resizing
    in.resize(N+1), out.resize(N+1);
    erased.resize(N+1);
    adj.resize(N+1, vector<bool>(N+1,false));


    for(int i=0; i<M; i++){
        int pre, post;
        cin >> pre >> post;
        // unique하게 설정해주어야 한다.
        adj[pre][post] = true;
        in[post] += 1, out[pre] += 1;
    }

    vector<int> answer ;
    int cnt = 0 ;

    while( cnt != N ){
        // 연결 된 node 가 없으면 해당 노드를 정답에 추가
        int cursor = -1;
        for(int i=1; i<=N; i++){
            if( in[i] == 0 && !erased[i] ){
                answer.push_back(i);
                erased[i] = true;
                cnt += 1;
                cursor = i;
                break;
            }
        }
        // in-edge 가 0 인 노드를 제거하고 해당 노드와 연결 상태에 있는 모든 노드들을 adjusitng
//        if( cursor != -1 ){
//            cout << "ERROR \n";
//            break;
//        }
        for(int i=1; i<=N; i++){
            if( adj[cursor][i] == 1 ){
                adj[cursor][i] = 0;
                in[i] -= 1;
            }
        }
    }
    for(auto c : answer )
        cout << c << ' ';


    return 0;
}
