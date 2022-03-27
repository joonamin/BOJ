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
    map<string,int,less<>> mp;
    string s;
    while(getline(cin, s)){
        mp[s] += 1;
    }
    int sum = 0;
    for(const auto& item : mp)
        sum += item.second;

    cout.precision(4);
    cout << fixed;
    for(const auto& item : mp)
        cout << item.first << ' ' << (double)item.second*100/sum << '\n';


    return 0;
}
