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

    string s;
    cin >> s;
    int n = s.size();
    int a_count = count_if(s.begin(), s.end(), [](auto c) { return c == 'a'; });
    int ans = 1e9;

    for (int st = 0; st < n; st++) {
        // st, st + 1, ... , st + a_count - 1 까지 연속되었다고 가정 (modular 상)
        int res = 0;
        for (int i = st; i < st + a_count; i++) {
            if (s[i % n] != 'a')
                res++;
        }
        ans = min(ans, res);
    }

    cout << ans;
    return 0;
}
