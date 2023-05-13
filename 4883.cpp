#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;

int N, dp[100001][3], v[100001][3];
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int tc = 1;
    while (cin >> N, N > 0) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                cin >> v[i][j];
            }
        }
        fill(&dp[0][0], &dp[1000][3], 0x3f3f3f3f);

        dp[0][1] = v[0][1];
        dp[0][2] = v[0][1] + v[0][2];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                int ac = 0x3f3f3f3f;
                if (j >= 1) {
                    ac = min(ac, dp[i - 1][j - 1]);
                    ac = min(ac, dp[i][j - 1]);
                }
                if (j + 1 < 3)
                    ac = min(ac, dp[i - 1][j + 1]);
                ac = min(ac, dp[i - 1][j]);
                dp[i][j] = ac + v[i][j];
            }
        }

        cout << tc++ << ". " << dp[N - 1][1] << '\n';
    }

    return 0;
}
