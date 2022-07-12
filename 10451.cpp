#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int T, N, adj[1001], p[1001];
bool visited[1001];
int counter = 1;
bool dfs(const int start, int cur) {
    if (visited[start] && start == cur) {
        return true;
    }
    bool ret = false;
    if (!visited[adj[cur]]) {
        visited[adj[cur]] = true;
        ret |= dfs(start, adj[cur]);
    }
    if (ret)
        p[cur] = counter;
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> T;
    while (T--) {
        counter = 1;
        cin >> N;
        for (int i = 1; i <= N; i++) {
            int temp;
            cin >> temp;
            adj[i] = temp;
        }
        set<int> s;
        memset(visited, false, sizeof(visited));
        memset(p, 0, sizeof(p));
        for (int i = 1; i <= N; i++) {
            if (p[i]) continue;
            if (dfs(i, i)) {
                p[i] = counter;
                s.insert(counter++);
            }
        }
        cout << s.size() << '\n';
    }

    return 0;
}
