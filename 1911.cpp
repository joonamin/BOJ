#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;



int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int N, L;
    cin >> N >> L;

    priority_queue<pair<int, int>, vector<pair<int,int>>, greater<pair<int,int>>> PQ;
    for (int i = 0; i < N; i++) {
        int s, e;
        cin >> s >> e;
        e--; // [s, e]
        PQ.push({s, e});
    }

    int c = 0, ans = 0;
    while (!PQ.empty()) {
        auto[s, e] = PQ.top();
        PQ.pop();

        if (s <= c) s = c + 1;
        if (e < s || e <= c) continue;

        // 해당 구간을 덮기 위해서 얼마나 많은 타일이 필요?
        // factor = ceil((e - s + 1) / L) --> c = s + factor * L
        int factor = ceil((e - s + 1) / (double)L);
        //cout << "factor: " << factor << '\n';
        c = s + factor * L - 1;
        //cout << "c : " << c << '\n';
        ans += factor;
        if (c + 1 <= e) {
            PQ.push({c + 1, e});
        }
    }
    cout << ans;
    return 0;
}
