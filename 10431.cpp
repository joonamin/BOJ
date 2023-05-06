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

    int P;
    cin >> P;

    while (P--) {
        int t;
        vector<int> arr(20);
        cin >> t;

        int ans = 0;
        for (int i = 0; i < 20; i++) {
            int elem;
            cin >> elem;
            int j = 0;
            while (j < i && arr[j] < elem) j++;
            ans += (i - j);
            arr.insert(arr.begin() + j, elem);
        }
        cout << t << ' ' << ans << '\n';
    }


    return 0;
}
