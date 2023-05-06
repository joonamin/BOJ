#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>

using namespace std;


int main(void) {
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////

    int N, K;
    cin >> N >> K;

    vector<tuple<int, int, int, int>> v;
    for (int i = 0; i < N; i++) {
        int a, g, s, b;
        cin >> a >> g >> s >> b;
        v.emplace_back(g, s, b, a);
    }

    sort(v.begin(), v.end(), greater<>());

    map<int, int> rankings;
    int counter = 1;
    for (int i = 0; i < N;) {
        int j = i;
        while (j < N && get<0>(v[j]) == get<0>(v[i])
               && get<1>(v[j]) == get<1>(v[i])
               && get<2>(v[j]) == get<2>(v[i])) {
            rankings[get<3>(v[j])] = counter;
            j++;
        }
        counter += j - i;
        i = j;
    }

    cout << rankings[K];
    return 0;
}
