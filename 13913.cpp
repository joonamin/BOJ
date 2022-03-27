#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, K, t[200005], pr[200005];
bool visited[200005] = {false, };
vector<int> route;
bool isValid(int val){
    return 0 <= val && val <= 200000;
}
void traceBack(int cur){
    route.push_back(cur);
    if(cur == N)
        return;
    traceBack(pr[cur]);
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N >> K;

    // {현재 위치, 누적 시간}
    queue<pair<int,int>> Q;
    fill(t, t+200005, 1e9);
    fill(pr, pr+200005, 1e9);

    Q.push({N, 0});
    t[N] = 0, pr[N] = N;
    while(!Q.empty()){
        auto [cur, acTime] = Q.front(); Q.pop();
        int dx[2] = {-1, 1};
        for(int i=0; i<2; i++) {
            int next = cur + dx[i];
            if (isValid(next) && t[next] > acTime && !visited[next]) {
                visited[next] = true;
                t[next] = acTime + 1;
                pr[next] = cur;
                Q.push({next, acTime + 1});
            }
        }
        int next = cur * 2;
        if(isValid(next) && t[next] > acTime && !visited[next]){
            visited[next] = true;
            t[next] = acTime + 1;
            pr[next] = cur;
            Q.push({next, acTime + 1});
        }
    }
    cout << t[K] << '\n';
    traceBack(K);
    reverse(route.begin(), route.end());
    for(auto p : route)
        cout << p << ' ';


    return 0;
}
