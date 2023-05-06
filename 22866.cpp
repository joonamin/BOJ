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

    int N;
    cin >> N;

    vector<int> v(N);
    for (auto &elem : v) {
        cin >> elem;
    }

    stack<pair<int,int>> left, right; // {idx, height}
    vector<int> lookat(N, -1), counts(N, 0);
    // left -> desc, right -> asc

    // left side 고려
    for (int i = 0; i < N; i++) {
        while (!left.empty() && left.top().second <= v[i]) {
            left.pop();
        }
        if (!left.empty()) {
            auto[idx, height] = left.top();
            if (lookat[i] == -1 || abs(i - lookat[i]) >= abs(i - idx))
                lookat[i] = idx;
        }
        counts[i] += left.size();
        left.push({i, v[i]});
    }

    // right side 고려
    for (int i = N - 1; i >= 0; i--) {
        while (!right.empty() && right.top().second <= v[i]) {
            right.pop();
        }
        if (!right.empty()) {
            auto[idx, height] = right.top();
            if (lookat[i] == -1 || abs(i - lookat[i]) > abs(i - idx))
                lookat[i] = idx;
        }
        counts[i] += right.size();
        right.push({i, v[i]});
    }

    for (int i = 0; i < N; i++) {
        cout << counts[i];
        if (counts[i] != 0) {
            cout << ' ' << lookat[i] + 1;
        }
        cout << '\n';
    }

    return 0;
}
