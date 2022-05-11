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
    int K, N;
    cin >> K >> N;
    vector<string> v(K);

    for(auto &item : v)
        cin >> item;


    string mx = v[0];
    for (int i = 1; i < K; i++) {
        if(mx.size() < v[i].size() || (mx.size() == v[i].size() && mx < v[i]))
            mx = v[i];
    }
    for (int i = 0; i < N - K; i++) {
        v.push_back(mx);
    }
    sort(v.begin(), v.end(), [](const auto &a, const auto &b)->bool{
        return a + b > b + a;
    });

    for(auto s : v)
        cout << s;

    return 0;
}
