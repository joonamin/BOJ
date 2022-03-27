#define FASTIO cin.tie(0)->sync_with_stdio(false), cout.tie(0)
#define FILEMODE freopen("input.txt","r",stdin), freopen("output.txt","w",stdout)
//////////////////////////////////////////////////////////////////
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
string convert(ll num, int digit){
    string ret = "";
    while(num){
        ret.push_back(num % digit + '0');
        num /= digit;
    }
    reverse(ret.begin(), ret.end());
    return ret;
}
int convert(string &num, int digit){
    int pr = 1, ret = 0;
    for(int i=num.size()-1; i>=0; i--){
        ret += pr * (num[i] - '0');
        pr *= digit;
    }
    return ret;
}
bool compare(string &a, string &b){
    // b가 더 큰가?
    if(a.size() != b.size()){
        return a.size() < b.size();
    }
    for(int i=0; i<a.size(); i++){
        if(a[i] != b[i])
            return a[i] < b[i];
    }
    return false;
}
int main(void){
#ifndef ONLINE_JUDGE
    FILEMODE;
#endif
    FASTIO;
//////////////////////////////////////////////////////////////////
    ll N, D;
    cin >> N >> D;

    string num = convert(N, D);

    string v;
    for(int i=0; i<D; i++){
        v.push_back(i + '0');
    }

    int ans = -1;
    do{
        if(v[0] == '0') continue;
        if(compare(num, v)){
            ans = convert(v, D);
            break;
        }
    }while(next_permutation(v.begin(), v.end()));
    cout << ans << '\n';
    return 0;
}
