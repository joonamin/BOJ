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

    int N, X;
    cin >> N >> X;
    vector<int> v(N);

    for (auto &elem: v) cin >> elem;

    int sum = 0;
    vector<int> ans; // {sum}
    for (int i = 0, j = 0; i < N; i++) {
        while (j < N && j - i < X) {
            sum += v[j];
            j++;
        }
        ans.push_back(sum);
        sum -= v[i];
    }

    sort(ans.begin(), ans.end(), greater<>());
    if (ans[0] == 0)
        cout << "SAD";
    else
        cout << ans[0] << '\n' << count_if(ans.begin(), ans.end(), [&ans](auto c){ return c == ans[0]; });
    return 0;
}
