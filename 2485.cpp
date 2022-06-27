#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
int gcd(int a, int b){
    if(b == 0)
        return a;
    return gcd(b, a % b);
}
int findGcd(const vector<int>& v){
    int ret = v[0];
    for(int i=1; i<v.size(); i++){
        ret = gcd(ret, v[i]);
    }
    return ret;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    int N;
    cin >> N;
    vector<int> v(N), dist;
    for(auto &item : v)
        cin >> item;
    sort(v.begin(), v.end());
    for(int i=1; i<v.size(); i++){
        dist.push_back(v[i] - v[i-1]);
    }
    int k = findGcd(dist), ans = 0;

    for(const auto &item : dist)
        ans += (item/k)-1;
    cout << ans;

    return 0;
}
