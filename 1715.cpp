#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int N;
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    cin >> N;
    vector<long long> v(N);
    for(auto &item : v)
        cin >> item;

    long long ans = 0;
    priority_queue<long long, vector<long long>, greater<long long>> PQ;
    for(const auto &item : v)
        PQ.push(item);
    while(PQ.size() > 1){
        long long a, b;
        a = PQ.top(), PQ.pop();
        b = PQ.top(), PQ.pop();
        ans += (a + b);
        PQ.push(a + b);
    }
    cout << ans ;
    return 0;
}