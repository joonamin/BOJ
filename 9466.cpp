#define NOT_VISITED 0
#define VISITED 1
#define HAS_TEAM 2
#include <bits/stdc++.h>
using namespace std;
vector<int> students;
vector<int> state;
vector<int> path;
void DFS(int cur){
    int next = students[cur];
    if( state[next] == NOT_VISITED ){
        state[next] = VISITED;
        path.push_back(next);
        DFS(next);
        path.pop_back();
    }
    else{
        int idx = -1;
        for(int i=0; i<path.size(); i++){
            if( path[i] == next ){
                idx = i;
                break;
            }
        }
        if( idx == -1 ) return;
        for(int i=idx; i<path.size(); i++){
            int j = path[i];
            state[j] = HAS_TEAM;
        }
    }
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);

    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n ;

        students.resize(n+1), state.resize(n+1);
        for(int i=1; i<=n; i++){
            state[i] = NOT_VISITED;
        }

        for(int i=1; i<=n; i++){
            cin >> students[i];
        }

        for(int i=1; i<=n; i++){
            // 한번 순회를 해서, cycle 확인이 안된 것은 다시 확인 할 필요가 없다.
            // 연결되어있는 그래프들은 전부 cycle 여부가 check된다.
            if( state[i] != NOT_VISITED )
                continue;
            path.push_back(i);
            state[i] = VISITED;
            DFS(i);
            path.pop_back();
        }

        int cnt = 0;
        for(int i=1; i<=n; i++){
            if( state[i] != HAS_TEAM )
                cnt += 1;
        }
        cout << cnt << '\n';
    }

    return 0;
}
