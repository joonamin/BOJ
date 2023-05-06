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
    vector<int> v(N * N);
    for (int i = 0; i < N * N; i++) {
        cin >> v[i];
    }
    sort(v.begin(),  v.end(), greater<>());
    cout << v[N - 1] << '\n';

    return 0;
}
