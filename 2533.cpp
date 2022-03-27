#define NORMAL 0
#define EARLY_ADOPTER 1
#include <bits/stdc++.h>
using namespace std;
int N, dp[1000001][2];
vector<int> in[1000001], adj[1000001];
void makeAdj(int current, int prev){
    for(auto next : in[current]){
        if( next != prev ){
            adj[current].push_back(next);
            makeAdj(next, current);
        }
    }
}
int findAns(int current, int type){
    int& ret = dp[current][type];
    if( ret != -1 )
        return ret;
    if( adj[current].empty() ){
        // leaf 노드일 때
        return dp[current][type] = type;
    }
    if( type == NORMAL ){
        ret = 0;
        for(auto next : adj[current]){
            ret += findAns(next, EARLY_ADOPTER);
        }
    }
    else if( type == EARLY_ADOPTER ){
        ret = 1;
        for(auto next : adj[current]){
            ret += min( findAns(next, EARLY_ADOPTER), findAns(next, NORMAL) );
        }
    }
    return ret;
}
int main(void){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    for(int i=0; i<N-1; i++){
        int a, b;
        cin >> a >> b;
        in[a].push_back(b);
        in[b].push_back(a);
    }

    makeAdj(1, 0);
    memset(dp, -1, sizeof(dp));

    // top -> bottom
    cout << min( findAns(1, 0), findAns(1, 1) ) << '\n';
    return 0;
}
