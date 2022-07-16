#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N, M, t[200001];
vector<int> adj[200001];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    cin >> N;
    for (int i = 1; i <= N; i++) {
        int in;
        while (cin >> in, in != 0) {
            adj[i].push_back(in);
            adj[in].push_back(i);
        }
    }

    memset(t, -1, sizeof(t));
    cin >> M;

    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;
    for (int i = 0; i < M; i++) {
        int in;
        cin >> in;
        t[in] = 0;
        PQ.push({0, in});
    }

    while (!PQ.empty()) {
        // 특정 정점이 루머를 알게 될 경우에만, update의 가능성이 생긴다.
        auto[curTime, cur] = PQ.top();
        PQ.pop();

        for (auto next: adj[cur]) {
            if (t[next] != -1)
                continue;
            // next의 모든 인접 노드들 중 1/2이상이 소문을 알고 있다면? Q에 추가
            int count = 0;
            for (auto nearBy: adj[next]) {
                if (t[nearBy] != -1 && t[nearBy] <= curTime)
                    count++;
            }
            if (count >= ceil(adj[next].size()/2.0)) {
                t[next] = curTime + 1;
                PQ.push({curTime + 1, next});
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << t[i] << ' ';
    }
    return 0;
}
