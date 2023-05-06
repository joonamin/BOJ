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

    int N, K;
    cin >> N >> K;

    vector<int> v(N);
    for (auto &elem : v) {
        cin >> elem;
    }

    vector<int> permutation(N);
    generate(permutation.begin(), permutation.end(), [n = 0]() mutable { return n++; });
    int ans = 0;
    do {
        int cur = 500;
        bool canGo = true;
        for (auto order : permutation) {
            if (cur + v[order] - K < 500) canGo = false;
            cur = cur + v[order] - K;
        }
        ans += canGo;
    } while (next_permutation(permutation.begin(), permutation.end()));

    cout << ans;
    return 0;
}
