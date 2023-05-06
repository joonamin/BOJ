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

    vector<int> a(N);
    for (auto &elem : a) {
        cin >> elem;
    }

    int count[100001] = {0,};
    int left = 0, right = left, ans = 0;

    while (right < N) {
        bool canGo = true;
        while (right < N && canGo) {
            if (count[a[right]] + 1 > K)
                canGo = false;
            else {
                count[a[right]]++;
                right++;
            }
        }

        ans = max(right - left, ans);
        count[a[left]]--;
        left++;
    }


    cout << ans;
    return 0;
}
