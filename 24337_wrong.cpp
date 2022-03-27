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
    int N, a, b;
    cin >> N >> a >> b;

    int h = 1;
    vector<int> ans;
    while(a--){
        ans.push_back(h++);
    }

    h--;
    b--;

    while(b--){
        ans.push_back(--h);
    }

    if(ans.size() > N)
        cout << -1 << '\n';
    else{
        int diff = N - ans.size();
        reverse(ans.begin(), ans.end());
        while(diff) {
            ans.push_back(ans.back());
            diff--;
        }
        reverse(ans.begin(), ans.end());
        int dx = *min_element(ans.begin(), ans.end());
        for_each(ans.begin(), ans.end(), [dx](int &k){k -= dx, k++;});
        for(auto item : ans)
            cout << item << ' ';
    }

    return 0;
}
